package com.poscoict.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encoding;

	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getInitParameter("encoding");
		if(encoding == null) {//default encoding
			encoding="UTF-8";
		}
	}
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*request 처리*/
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		
		/*request 처리*/

	}



}
