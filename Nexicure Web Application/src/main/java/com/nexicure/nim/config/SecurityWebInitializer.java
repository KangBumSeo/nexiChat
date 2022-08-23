package com.nexicure.nim.config;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import com.nexicure.nim.config.filter.CrossSiteScriptingFilter;

public class SecurityWebInitializer extends
		AbstractSecurityWebApplicationInitializer {

	/*
	 * public SecurityWebApplicationInitializer() { super(SecurityConfig.class); }
	 */
	
	@Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    //    insertFilters(servletContext, new MultipartFilter());
    //    insertFilters(servletContext, new CrossSiteScriptingFilter());
    }
}
