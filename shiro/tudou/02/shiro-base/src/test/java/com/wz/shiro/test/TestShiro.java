package com.wz.shiro.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import ch.qos.logback.core.net.LoginAuthenticator;

public class TestShiro {

	@Test
	public void testBase() {

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("kh", "123");
		try {
			subject.login(token);
			System.out.println("Principal: " + subject.getPrincipal());
		} catch (UnknownAccountException e) {
			System.out.println("用户不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码存在");
		} catch (AuthenticationException e) {
			System.out.println("认证异常");
		}

	}

	public Subject login(String username, String password) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			return subject;
		} catch (UnknownAccountException e) {
			System.out.println("用户不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不存在");
		} catch (AuthenticationException e) {
			System.out.println("认证异常");
		}
		return null;
	}

	@Test
	public void testRole() {
		Subject subject = login("kh", "123");
		System.out.println(subject.hasRole("r1"));
		System.out.println(subject.hasAllRoles(Arrays.asList("r1", "r2", "r3")));
		System.out.println(subject.hasRoles(Arrays.asList("r1", "r2", "r3"))[2]);
		subject.checkRole("r1");
	}

	@Test
	public void testPermission() {
		Subject subject = login("kh", "123");
		// 是否具有user的view权限 false
		System.out.println(subject.isPermitted("user:view"));
		// 是否具有user的delete权限 true
		System.out.println(subject.isPermitted("user:delete"));
		// 是否具有topic的create权限 true
		System.out.println(subject.isPermitted("topic:create"));
		
		System.out.println(subject.isPermitted("classroom:create"));
	}

	@Test
	public void testMyPermission() {
		Subject subject = login("kh", "123");
		System.out.println("+user+delete "+subject.isPermitted("+user+delete"));
		System.out.println("+user+create "+subject.isPermitted("+user+create"));
		System.out.println("+topic+update "+subject.isPermitted("+topic+update"));
	}
	
	@Test
	public void testMyRolePermission() {
		Subject subject = login("kh", "123");
		System.out.println("classroom:add "+subject.isPermitted("classroom:add"));
		
	}
}
