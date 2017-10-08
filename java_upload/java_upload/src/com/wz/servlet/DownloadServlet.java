package com.wz.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取文件下载路径
		String path = getServletContext().getRealPath("/") + "images/";
		String filename = req.getParameter("filename");
		File file = new File(path + filename);
		if (file.exists()) {
			//设置相应类型 或者application/octet-stream
            resp.setContentType("application/x-msdownlaod");
            //设置头信息
            resp.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = resp.getOutputStream();
            byte b[] = new byte[1024];
            int n;
            while((n = inputStream.read(b)) != -1){
                outputStream.write(b, 0, n);
            }
            //关闭流、释放资源
            outputStream.close();
            inputStream.close();
			
		}else{
			req.setAttribute("errorResult", "文件不存在");
			RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/01.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
