package cc.sion.core.sys.biz.impl;

import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysPermissionService;
import cc.sion.core.sys.domain.SysPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysPermissionServiceImpl extends BaseBizImpl<SysPermission,String> implements ISysPermissionService {
}
