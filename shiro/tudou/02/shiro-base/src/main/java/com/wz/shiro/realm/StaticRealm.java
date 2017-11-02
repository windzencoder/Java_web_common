package com.wz.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wz.shiro.permission.MyPermission;

public class StaticRealm extends AuthorizingRealm{

	/**
	 * 用来判断授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
		info.addRole("r1");
		info.addRole("r2");
		info.addStringPermission("+user+*");
		info.addObjectPermission(new MyPermission("+topic+create"));
		info.addObjectPermission(new MyPermission("+topic+delete+1"));
		return info;
	}

	/**
	 * 用来判断认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = token.getPrincipal().toString();
		String password = new String((char[])(token.getCredentials()));
		
		System.out.println("username: "  + username + " password: "+password);
		
		if (!"kh".equals(username)) {
			throw new UnknownAccountException("用户名出错");
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException("密码出错");
		}
		
		AuthenticationInfo info = new SimpleAuthenticationInfo("kh@123.com", password, getName());		
		return info;

	}



}
