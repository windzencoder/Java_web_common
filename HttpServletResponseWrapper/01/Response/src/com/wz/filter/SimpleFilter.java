package com.wz.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SimpleFilter implements Filter {

	private FilterConfig config;
	private ServletContext context;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.print("Within Simple Filter...");
		System.out.println("Simple Filtering the Request...");

		HttpServletRequest req = (HttpServletRequest) request;
		String requestURL = req.getRequestURL().toString();

		context.log("Requesting URL: " + requestURL + " Time:" + new Date());

		// pass the request along the filter chain
		chain.doFilter(request, response);

		System.out.print("Within Simple Filter...");
		System.out.println("Simple Filtering the Response...");

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		context = config.getServletContext();
	}

	@Override
	public void destroy() {
	}

}
