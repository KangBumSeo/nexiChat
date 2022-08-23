package com.nexicure.nim.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CrossSiteScriptingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(request.getMethod().equals("GET") || request.getMethod().equals("POST")){ 
			filterChain.doFilter(new RequestWrapperForXssFiltering(request), response); 
		} else { 
			filterChain.doFilter(request, response); 
		}


	}

}
