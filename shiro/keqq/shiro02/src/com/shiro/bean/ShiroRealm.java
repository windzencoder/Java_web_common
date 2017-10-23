package com.shiro.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

public class ShiroRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		SimpleAuthenticationInfo info = null;
		
		// 1.将token转换为username 和 password
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 2.获取用户名即可
		String username = upToken.getUsername();
		// 3.查询数据库是否存在指定用户名和密码的用户
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shiro", "root", "");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// 4.如果查询到了，封装查询结果，返回给我们调用
				Object principal = username;
				Object credentials = rs.getString(3);//password
				String realmName = this.getName();
				/*
				 * 将合法的用户名密码封装在SimpleAuthenticationInfo中
				 * shiro会自动判断用户输入的密码，是否与封装的密码一致
				 */
				info = new SimpleAuthenticationInfo(principal, credentials, realmName);
			}else{
				// 5.如果没有查询到，抛出一个异常
				throw new AuthenticationException();//抛出异常
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

		return info;
	}

}
