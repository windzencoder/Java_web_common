package com.wz.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class HitCounterFilter implements Filter {

	private FilterConfig config;
	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		context = config.getServletContext();
		context.setAttribute("hitCount", new Integer(0));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.print("Within HitCounter Filter...");
		System.out.println("HitCounter Filtering the Request...");
		
		int i = ((Integer) context.getAttribute("hitCount")).intValue();
		i++;
		context.setAttribute("hitCount", new Integer(i));

		DummyResponse dummyResponse = new DummyResponse((HttpServletResponse) response);
		chain.doFilter(request, dummyResponse); // pass the request,dummy response along the filter chain
		// chain.doFilter(request, response);

		PrintWriter out = response.getWriter();
		out.println(dummyResponse.toString());
		out.println("<b>Your are the " + i + " Visitor</b>");
		out.close();
		

		System.out.print("Within HitCounter Filter...");
		System.out.println("HitCounter Filtering the Response...");

	}

	@Override
	public void destroy() {

	}

}
