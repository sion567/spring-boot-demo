/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package cc.sion.core.security.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	protected IAccountService accountService;

	/*
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser user = accountService.findUserByLoginName(token.getUsername());
		if (user != null) {
			log.debug("---user name:{}", user.getName());
			return new SimpleAuthenticationInfo(
					token.getUsername(),//用户名
					user.getPassword(),//密码
					ByteSource.Util.bytes(user.getCredentialSalt()),//salt=username+salt
					getName());//realm name
		} else {
			return null;
		}
	}

//	/**
//	 * 真正的校验
//	 */
//	@Override
//	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {}

	/*
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = principals.getPrimaryPrincipal().toString();//得到主要的身份
		log.debug("===user name:{}", loginName);
		SysUser user = accountService.findUserByLoginName(loginName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(user.getRoleList());
		return info;
	}

}
