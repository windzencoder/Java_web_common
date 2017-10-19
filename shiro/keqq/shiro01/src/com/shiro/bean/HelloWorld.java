package com.shiro.bean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

	private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);
	
	public static void main(String[] args) {
		
		log.info("正在测试log4j......");
		
		//1.获取安全管理器
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		//2.设置安全管理器
		SecurityUtils.setSecurityManager(securityManager);
		
		//3.获取Subject对象，为即将登录的用户
		Subject currentUser = SecurityUtils.getSubject();
		//4.获取session
		Session session = currentUser.getSession();
		session.setAttribute("name", "wz");
		
		String value = (String) session.getAttribute("name");
		if (value != null ) {
			log.info("Shiro获取session会话对象当中指定的值：" + value);
		}
		
		if (currentUser.isAuthenticated() == false) {
			
			UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
			token.setRememberMe(true);//记住我
			try {
				currentUser.login(token);
				log.info("用户名和密码正确");
			} catch (UnknownAccountException e) {
				log.info("账户不存在");
			}catch (IncorrectCredentialsException e) {
				log.info("密码错误");
			}catch (LockedAccountException e) {
				log.info("用户已经锁死");
			}catch (AuthenticationException e) {
				log.info("认证异常");
			}
			
			
		}
		
	}
	
	
}
