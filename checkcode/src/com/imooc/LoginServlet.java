package com.imooc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录Servlet
 * @author Miller
 *
 */
public class LoginServlet extends HttpServlet{
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

		String piccode = (String) req.getSession().getAttribute("piccode");
		
		String checkcode = req.getParameter("checkcode").toUpperCase();
		
		resp.setContentType("text/html;charset=utf8");
		
		try {
			PrintWriter out = resp.getWriter();
			if (checkcode.equals(piccode)) {
				out.println("验证码输入正确");
			}else{
				out.println("验证码输入错误");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
