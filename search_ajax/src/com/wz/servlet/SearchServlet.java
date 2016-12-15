package com.wz.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static List<String> data = new ArrayList<>();
	
	static {
		
		data.add("ajax");
		data.add("ajax get");
		data.add("ajax post");
		data.add("baby");
		data.add("box");
		data.add("car");
		data.add("cat");
	}

    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		keyword = URLDecoder.decode(keyword, "UTF-8");
		
		System.out.println("doGet : " + keyword);
		
		List<String> listData = getData(keyword);
		String json = new Gson().toJson(listData);
		
		System.out.println("json : " + json);
		
		response.getWriter().write(json);
		
		
	}


	public List<String> getData(String keyword) {
		
		List<String> list = new ArrayList<>();
		for (String string : data) {
			if (string.contains(keyword)) {
				list.add(string);
			}
		}
		return list;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
