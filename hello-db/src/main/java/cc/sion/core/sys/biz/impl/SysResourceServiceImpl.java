package cc.sion.core.sys.biz.impl;

import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysResourceService;
import cc.sion.core.sys.biz.IUserAuthService;
import cc.sion.core.sys.dao.SysResourceDAO;
import cc.sion.core.sys.domain.SysResource;
import cc.sion.core.sys.domain.SysUser;
import cc.sion.core.sys.dto.Menu;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SysResourceServiceImpl extends BaseBizImpl<SysResource,String> implements ISysResourceService {

    @Autowired
    private IUserAuthService userAuthService;
    @Autowired
    private SysResourceDAO sysResourceDAO;


    public String findActualResourceIdentity(SysResource resource) {
        if(resource == null) {
            return null;
        }
        StringBuilder s = new StringBuilder(resource.getIdentity());

        boolean hasResourceIdentity = !StringUtils.isEmpty(resource.getIdentity());

        SysResource parent = getObj(resource.getParentId());
        while(parent != null) {
            if(!StringUtils.isEmpty(parent.getIdentity())) {
                s.insert(0, parent.getIdentity() + ":");
                hasResourceIdentity = true;
            }
            parent = getObj(parent.getParentId());
        }
        //如果用户没有声明 资源标识  且父也没有，那么就为空
        if(!hasResourceIdentity) {
            return "";
        }
        //如果最后一个字符是: 因为不需要，所以删除之
        int length = s.length();
        if(length > 0 && s.lastIndexOf(":") == length - 1) {
            s.deleteCharAt(length - 1);
        }

        //如果有儿子 最后拼一个*
        boolean hasChildren = false;
        for(SysResource r : findAll()) {
            if(resource.getId().equals(r.getParentId())) {
                hasChildren = true;
                break;
            }
        }
        if(hasChildren) {
            s.append(":*");
        }

        return s.toString();
    }

    public List<Menu> findMenus(SysUser user) {
        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("EQ_show",true);
        List<SysResource> resources = findAll(searchParams,"parentId", "weight");

        Set<String> userPermissions = userAuthService.findStringPermissions(user);

        Iterator<SysResource> iter = resources.iterator();
        while (iter.hasNext()) {
            if (!hasPermission(iter.next(), userPermissions)) {
                iter.remove();
            }
        }

        return convertToMenus(resources);
    }

    private boolean hasPermission(SysResource resource, Set<String> userPermissions) {
        String actualResourceIdentity = findActualResourceIdentity(resource);
        if (StringUtils.isEmpty(actualResourceIdentity)) {
            return true;
        }
        for (String permission : userPermissions) {
            if (hasPermission(permission, actualResourceIdentity)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermission(String permission, String actualResourceIdentity) {
        //得到权限字符串中的 资源部分，如a:b:create --->资源是a:b
        String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));
        //如果权限字符串中的资源 是 以资源为前缀 则有权限 如a:b 具有a:b的权限
        if(permissionResourceIdentity.startsWith(actualResourceIdentity)) {
            return true;
        }


        //模式匹配
        WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
        WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

        return p1.implies(p2) || p2.implies(p1);
    }


    @SuppressWarnings("unchecked")
    public static List<Menu> convertToMenus(List<SysResource> resources) {
        if (resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        Menu root = convertToMenu(resources.remove(resources.size() - 1));

        recursiveMenu(root, resources);
        List<Menu> menus = root.getChildren();
        removeNoLeafMenu(menus);

        return menus;
    }

    private static void removeNoLeafMenu(List<Menu> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

    private static void recursiveMenu(Menu menu, List<SysResource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
            SysResource resource = resources.get(i);
            if (resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (Menu subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }

    private static Menu convertToMenu(SysResource resource) {
        return new Menu(resource.getId(), resource.getName(), resource.getIcon(), resource.getUrl());
    }
}
