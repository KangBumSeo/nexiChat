package com.nexicure.nim.config;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.PropertyConfigurator;
import org.apache.logging.log4j.core.LoggerContext;

import com.nexicure.commons.IGlobalsConstants;
import com.nexicure.commons.SpitConstants;
import com.nexicure.nim.ConfigConstants;

public class ConfigManager {
	
	
	public static void propConfigure(ServletContext servletContext) {
		
		try {
			LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);

			ConfigConstants.SYSTEM_BASE_DIR = servletContext.getRealPath(SpitConstants.DELIMITER.SLASH);
			if (!ConfigConstants.SYSTEM_BASE_DIR.endsWith(SpitConstants.DELIMITER.SLASH))
				ConfigConstants.SYSTEM_BASE_DIR += SpitConstants.DELIMITER.SLASH;
			System.out.println("SYSTEM_BASE_DIR : " + ConfigConstants.SYSTEM_BASE_DIR);
//			PropertyConfigurator.configure(ConfigConstants.SYSTEM_BASE_DIR+IGlobalsConstants.SYSTEM.LOGGER_PROP);
			context.setConfigLocation(new File(ConfigConstants.SYSTEM_BASE_DIR+"WEB-INF/config/log4j2.xml").toURI());
			ConfigConstants.CONFIG = new PropertiesConfiguration(ConfigConstants.SYSTEM_BASE_DIR+IGlobalsConstants.SYSTEM.CONFIG_PROP);

			ConfigConstants.SYSTEM_LOCALE = new Locale(ConfigConstants.CONFIG.getString(IGlobalsConstants.CONFIG.LOCALE));
			ConfigConstants.BASEURL = ConfigConstants.CONFIG.getString(IGlobalsConstants.CONFIG.BASEURL);
			ConfigConstants.CONTENT_BASEURL = ConfigConstants.CONFIG.getString(IGlobalsConstants.CONFIG.CONTENT_BASEURL);
			ConfigConstants.APP_BASEURL = ConfigConstants.CONFIG.getString(IGlobalsConstants.CONFIG.APP_BASEURL);
	
			ConfigConstants.UPLOAD_EVENTS_DIR = ConfigConstants.CONFIG.getString("sys.events.upload.dir");
			ConfigConstants.DBWRAP = ConfigConstants.CONFIG.getString(IGlobalsConstants.CONFIG.DBWRAP);
			
			
			System.setProperty("java.security.auth.login.config", ConfigConstants.SYSTEM_BASE_DIR+ConfigConstants.CONFIG.getString("sys.system.jaas.login.conf"));
			
			PropertiesConfiguration LdapConfigProp = new PropertiesConfiguration("classpath:/NexicureConfig.properties");
			ConfigConstants.LDAP_SVR = LdapConfigProp.getString("sys.system.ldap.ldap_svr");
			ConfigConstants.LDAP_PORT = LdapConfigProp.getString("sys.system.ldap.ldap_port");
			ConfigConstants.LDAP_ID = LdapConfigProp.getString("sys.system.ldap.adminid");
			ConfigConstants.LDAP_PWD = LdapConfigProp.getString("sys.system.ldap.adminpw");
			
			ConfigConstants.USER_BASEDN = LdapConfigProp.getString("sys.system.ldap.user.basedn");

			ConfigConstants.ITIM_GLOBALID_BASE = ConfigConstants.CONFIG.getString("itim.globalid")+","+ConfigConstants.CONFIG.getString("itim.default.org")+","+ConfigConstants.USER_BASEDN;
			
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
