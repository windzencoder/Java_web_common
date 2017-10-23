package com.shiro.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogonAction {

	@RequestMapping("/logon")
	public String logon(@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == false) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				System.out.println("认证失败");
				return "error";
			}
		}
		return "success";
	}
	
	
}
