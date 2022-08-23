package com.nexicure.nim.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;

import com.nexicure.es.services.crypt.seed.Seed;
import com.nexicure.nim.ConfigConstants;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static Logger logger = LogManager.getLogger(WebInitializer.class);
    
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class // DataServiceNIMConfig.class 
				, WebServiceConfig.class };	//DataServiceNIMConfig.class, , LDAPServiceConfig.class
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class     };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
   
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		servletContext.addListener(new WebCleanupListener());
		ConfigManager.propConfigure(servletContext);
		/*
		LDAPConnection lc = null;
		ITIMCodeManager itimCodeManager = new ITIMCodeManager();
		try {
			lc = new LDAPConnection();
			lc.connect(ConfigConstants.LDAP_SVR, Integer.parseInt(ConfigConstants.LDAP_PORT), ConfigConstants.LDAP_ID, new Seed().decrypt(ConfigConstants.LDAP_PWD));
			if (!itimCodeManager.isExistCodeDn(lc)) {
				logger.info("Make Code Entry.. Start");
				itimCodeManager.insertBaseCodeEntry(lc);
				itimCodeManager.insertCodeEntry(lc);
				logger.info("Make Code Entry.. End");
			}else if(false) {
				logger.info("Make Code Additional Entry.. Start");
				itimCodeManager.insertCodeEntry_Additional(lc);
				logger.info("Make Code Additional Entry.. End");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		} finally {
			if (lc != null)	try { lc.disconnect(); } catch (LDAPException e) { e.printStackTrace(); }
		}
		*/
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		return new Filter[]{new HiddenHttpMethodFilter(), cef};
	}
}
