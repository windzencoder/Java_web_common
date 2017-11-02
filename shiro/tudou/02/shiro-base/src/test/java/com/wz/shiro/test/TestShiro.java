package com.wz.shiro.test;

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

public class TestShiro {

	@Test
	public void testBase(){
		
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("kh", "123");
        try {
			subject.login(token);
			System.out.println("Principal: "+ subject.getPrincipal());
		} catch (UnknownAccountException e) {
			System.out.println("用户不存在");
		}catch (IncorrectCredentialsException e) {
			System.out.println("密码存在");
		}catch (AuthenticationException e) {
			System.out.println("认证异常");
		}
		
	}
	
}
