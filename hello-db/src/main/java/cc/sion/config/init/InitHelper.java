package cc.sion.config.init;

import cc.sion.core.sys.biz.*;
import cc.sion.core.sys.domain.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class InitHelper implements InitializingBean,DisposableBean {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysResourceService resourceService;
    @Autowired
    private ISysPermissionService permissionService;
    @Autowired
    private ISysAuthService authService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysGroupService groupService;
    @Autowired
    private ISysGroupRelationService groupRelationService;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init......");
        log.info("init resource......");
        //city相关资源，hotel相关资源，rating&review相关资源
        SysResource city = new SysResource();
        city.setName("City");
        city.setUrl("/sys/city");
        city.setIdentity("sys:city");
        city.setParentId("0");
        city.setShow(true);
        SysResource hotel = new SysResource();
        hotel.setName("Hotel");
        hotel.setUrl("/sys/hotel");
        hotel.setIdentity("sys:hotel");
        hotel.setParentId("0");
        hotel.setShow(true);
        SysResource rating = new SysResource();
        rating.setName("Rating");
        rating.setUrl("/sys/rating");
        rating.setIdentity("sys:rating");
        rating.setParentId("0");
        rating.setShow(true);
        SysResource review = new SysResource();
        review.setName("Review");
        review.setUrl("/sys/review");
        review.setIdentity("sys:review");
        review.setParentId("0");
        review.setShow(true);

        resourceService.save(city);
        resourceService.save(hotel);
        resourceService.save(rating);
        resourceService.save(review);

        log.info("init permission......");
        SysPermission c_p0 = new SysPermission();
        c_p0.setName("City所有权限");
        c_p0.setPermission("sys:city:*");
        c_p0.setShow(true);
        SysPermission c_p1 = new SysPermission();
        c_p1.setName("City新增");
        c_p1.setPermission("sys:city:create");
        c_p1.setShow(true);
        SysPermission c_p2 = new SysPermission();
        c_p2.setName("City修改");
        c_p2.setPermission("sys:city:update");
        c_p2.setShow(true);
        SysPermission c_p3 = new SysPermission();
        c_p3.setName("City删除");
        c_p3.setPermission("sys:city:delete");
        c_p3.setShow(true);
        SysPermission c_p4 = new SysPermission();
        c_p4.setName("City查看");
        c_p4.setPermission("sys:city:view");
        c_p4.setShow(true);

        SysPermission h_p0 = new SysPermission();
        h_p0.setName("Hotel所有权限");
        h_p0.setPermission("sys:hotel:*");
        h_p0.setShow(true);
        SysPermission h_p1 = new SysPermission();
        h_p1.setName("Hotel新增");
        h_p1.setPermission("sys:hotel:create");
        h_p1.setShow(true);
        SysPermission h_p2 = new SysPermission();
        h_p2.setName("Hotel修改");
        h_p2.setPermission("sys:hotel:update");
        h_p2.setShow(true);
        SysPermission h_p3 = new SysPermission();
        h_p3.setName("Hotel删除");
        h_p3.setPermission("sys:hotel:delete");
        h_p3.setShow(true);
        SysPermission h_p4 = new SysPermission();
        h_p4.setName("Hotel查看");
        h_p4.setPermission("sys:hotel:view");
        h_p4.setShow(true);

        SysPermission rating_p0 = new SysPermission();
        rating_p0.setName("Rating所有权限");
        rating_p0.setPermission("sys:rating:*");
        rating_p0.setShow(true);
        SysPermission rating_p1 = new SysPermission();
        rating_p1.setName("Rating新增");
        rating_p1.setPermission("sys:rating:create");
        rating_p1.setShow(true);
        SysPermission rating_p2 = new SysPermission();
        rating_p2.setName("Rating修改");
        rating_p2.setPermission("sys:rating:update");
        rating_p2.setShow(true);
        SysPermission rating_p3 = new SysPermission();
        rating_p3.setName("Rating删除");
        rating_p3.setPermission("sys:rating:delete");
        rating_p3.setShow(true);
        SysPermission rating_p4 = new SysPermission();
        rating_p4.setName("Rating查看");
        rating_p4.setPermission("sys:rating:view");
        rating_p4.setShow(true);

        SysPermission review_p0 = new SysPermission();
        review_p0.setName("Review所有权限");
        review_p0.setPermission("sys:review:*");
        review_p0.setShow(true);
        SysPermission review_p1 = new SysPermission();
        review_p1.setName("Review新增");
        review_p1.setPermission("sys:review:create");
        review_p1.setShow(true);
        SysPermission review_p2 = new SysPermission();
        review_p2.setName("Review修改");
        review_p2.setPermission("sys:review:update");
        review_p2.setShow(true);
        SysPermission review_p3 = new SysPermission();
        review_p3.setName("Review删除");
        review_p3.setPermission("sys:review:delete");
        review_p3.setShow(true);
        SysPermission review_p4 = new SysPermission();
        review_p4.setName("Review查看");
        review_p4.setPermission("sys:review:view");
        review_p4.setShow(true);

        permissionService.save(c_p0);
        permissionService.save(c_p1);
        permissionService.save(c_p2);
        permissionService.save(c_p3);
        permissionService.save(c_p4);

        permissionService.save(h_p0);
        permissionService.save(h_p1);
        permissionService.save(h_p2);
        permissionService.save(h_p3);
        permissionService.save(h_p4);

        permissionService.save(rating_p0);
        permissionService.save(rating_p1);
        permissionService.save(rating_p2);
        permissionService.save(rating_p3);
        permissionService.save(rating_p4);

        permissionService.save(review_p0);
        permissionService.save(review_p1);
        permissionService.save(review_p2);
        permissionService.save(review_p3);
        permissionService.save(review_p4);

        log.info("init role......");
        SysRole admin = new SysRole();
        admin.setName("系统管理员");//所有角色
        admin.setRole("admin");
        admin.setShow(true);
        SysRoleResourcePermission c = new SysRoleResourcePermission();
        c.setRole(admin);
        c.setResourceId(city.getId());
        c.getPermissionIds().add(c_p0.getId());
        admin.addResourcePermission(c);
        SysRoleResourcePermission h = new SysRoleResourcePermission();
        h.setRole(admin);
        h.setResourceId(hotel.getId());
        h.getPermissionIds().add(h_p0.getId());
        admin.addResourcePermission(h);
        SysRoleResourcePermission ra = new SysRoleResourcePermission();
        ra.setRole(admin);
        ra.setResourceId(rating.getId());
        ra.getPermissionIds().add(rating_p0.getId());
        admin.addResourcePermission(ra);
        SysRoleResourcePermission re = new SysRoleResourcePermission();
        re.setRole(admin);
        re.setResourceId(review.getId());
        re.getPermissionIds().add(review_p0.getId());
        admin.addResourcePermission(re);
        roleService.save(admin);

        SysRole agent = new SysRole();
        agent.setName("代理商");//添加，修改，删除信息
        agent.setRole("agent");
        agent.setShow(true);
        SysRoleResourcePermission c1 = new SysRoleResourcePermission();
        c1.setResourceId(city.getId());
        c.getPermissionIds().add(c_p0.getId());
        agent.addResourcePermission(c1);
        SysRoleResourcePermission h1 = new SysRoleResourcePermission();
        h1.setResourceId(hotel.getId());
        h1.getPermissionIds().add(h_p0.getId());
        agent.addResourcePermission(h1);
        roleService.save(agent);

        SysRole customer = new SysRole();
        customer.setName("用户");//查看，投票
        customer.setRole("customer");
        customer.setShow(true);
        SysRoleResourcePermission c_v = new SysRoleResourcePermission();
        c_v.setResourceId(city.getId());
        c_v.getPermissionIds().add(c_p4.getId());
        customer.addResourcePermission(c_v);
        SysRoleResourcePermission h_v = new SysRoleResourcePermission();
        h_v.setResourceId(hotel.getId());
        h_v.getPermissionIds().add(h_p4.getId());
        customer.addResourcePermission(c_v);
        SysRoleResourcePermission ra1 = new SysRoleResourcePermission();
        ra1.setResourceId(rating.getId());
        ra1.getPermissionIds().add(rating_p0.getId());
        customer.addResourcePermission(ra1);
        SysRoleResourcePermission re1 = new SysRoleResourcePermission();
        re1.setResourceId(review.getId());
        re1.getPermissionIds().add(review_p0.getId());
        customer.addResourcePermission(re1);
        roleService.save(customer);

        log.info("init user......");
        SysUser adminUser = new SysUser();
        adminUser.setAdmin(true);
        adminUser.setUserName("admin");
        adminUser.setEmail("admin@admin.com");
        adminUser.setMobilePhoneNumber("13200000000");
        adminUser.setPlainPassword("admin");

        SysUser agentUser1 = new SysUser();
        agentUser1.setUserName("aaaaa");
        agentUser1.setEmail("aa@aa.com");
        agentUser1.setMobilePhoneNumber("13200000001");
        agentUser1.setPlainPassword("aaaaa");
        SysUser agentUser2 = new SysUser();
        agentUser2.setUserName("bbbbb");
        agentUser2.setEmail("bb@bb.com");
        agentUser2.setMobilePhoneNumber("13200000002");
        agentUser2.setPlainPassword("bbbbb");

        SysUser customerUser1 = new SysUser();
        customerUser1.setUserName("c111");
        customerUser1.setEmail("111@111.com");
        customerUser1.setMobilePhoneNumber("13200000003");
        customerUser1.setPlainPassword("11111");
        SysUser customerUser2 = new SysUser();
        customerUser2.setUserName("c222");
        customerUser2.setEmail("222@222.com");
        customerUser2.setMobilePhoneNumber("13200000004");
        customerUser2.setPlainPassword("22222");

        userService.saveUser(adminUser);
        userService.saveUser(agentUser1);
        userService.saveUser(agentUser2);
        userService.saveUser(customerUser1);
        userService.saveUser(customerUser2);

        log.debug("init group...");
        SysGroup a_group = new SysGroup();
        a_group.setName("管理员组");
        a_group.setType(GroupType.user);
        a_group.setShow(true);
        SysGroup b_group = new SysGroup();
        b_group.setName("商户组");
        b_group.setType(GroupType.user);
        b_group.setShow(true);
        SysGroup c_group = new SysGroup();
        c_group.setName("客户组");
        c_group.setType(GroupType.user);
        c_group.setShow(true);
        groupService.save(a_group);
        groupService.save(b_group);
        groupService.save(c_group);

        log.debug("init group relation...");
        SysGroupRelation a_gr = new SysGroupRelation();
        a_gr.setUserId(adminUser.getId());
        a_gr.setGroupId(a_group.getId());
        groupRelationService.save(a_gr);
        SysGroupRelation b1_gr = new SysGroupRelation();
        b1_gr.setUserId(customerUser1.getId());
        b1_gr.setGroupId(b_group.getId());
        groupRelationService.save(b1_gr);
        SysGroupRelation b2_gr = new SysGroupRelation();
        b2_gr.setUserId(customerUser2.getId());
        b2_gr.setGroupId(b_group.getId());
        groupRelationService.save(b2_gr);
        SysGroupRelation c1_gr = new SysGroupRelation();
        c1_gr.setUserId(agentUser1.getId());
        c1_gr.setGroupId(c_group.getId());
        groupRelationService.save(c1_gr);
        SysGroupRelation c2_gr = new SysGroupRelation();
        c2_gr.setUserId(agentUser1.getId());
        c2_gr.setGroupId(c_group.getId());
        groupRelationService.save(c2_gr);

        log.debug("init auth...");
//        SysAuth auth1 = new SysAuth();
//        auth1.setGroupId(a_group.getId());
//        auth1.getRoleIds().add(admin.getId());
//        auth1.setUserId(adminUser.getId());
//
//        SysAuth auth21 = new SysAuth();
//        auth21.setGroupId(b_group.getId());
//        auth21.getRoleIds().add(agent.getId());
//        auth21.setUserId(agentUser1.getId());
//        SysAuth auth22 = new SysAuth();
//        auth22.setGroupId(b_group.getId());
//        auth22.getRoleIds().add(agent.getId());
//        auth22.setUserId(agentUser2.getId());
//
//        SysAuth auth31 = new SysAuth();
//        auth31.setGroupId(c_group.getId());
//        auth31.getRoleIds().add(customer.getId());
//        auth31.setUserId(customerUser1.getId());
//        SysAuth auth32 = new SysAuth();
//        auth32.setGroupId(c_group.getId());
//        auth32.getRoleIds().add(customer.getId());
//        auth32.setUserId(customerUser2.getId());
//
//        authService.save(auth1);
//        authService.save(auth21);
//        authService.save(auth22);
//        authService.save(auth31);
//        authService.save(auth32);

        SysAuth auth1 = new SysAuth();
        auth1.setGroupId(a_group.getId());
        auth1.getRoleIds().add(admin.getId());
        auth1.setType(AuthType.user_group);

        SysAuth auth2 = new SysAuth();
        auth2.setGroupId(b_group.getId());
        auth2.getRoleIds().add(agent.getId());
        auth2.setType(AuthType.user_group);

        SysAuth auth3 = new SysAuth();
        auth3.setGroupId(c_group.getId());
        auth3.getRoleIds().add(customer.getId());
        auth3.setType(AuthType.user_group);


        authService.save(auth1);
        authService.save(auth2);
        authService.save(auth3);

    }
    @Override
    public void destroy() throws Exception {
        log.info("destroy......");
    }

}
