package com.shiro.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogonAction {

	@RequestMapping("/logon")
	public String logon(@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		
		if (username.equals("tom") && password.equals("123")) {
			return "success";
		}
		return "abc";
	}
	
}
