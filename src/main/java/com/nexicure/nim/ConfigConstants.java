package com.nexicure.nim;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
//import org.apache.struts.util.MessageResources;

/**
 * @author ����
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@PropertySource("classpath:NexicureConfig.properties")
public class ConfigConstants {
	public static ServletContext SERVLETCONTEXT;
	public static String SYSTEM_BASE_DIR;
	public static String UPLOAD_EVENTS_DIR;

	public static Configuration CONFIG = null;
	public static Configuration CLUB_CONFIG = null;
//	public static MessageResources MESSAGEREOUSRCE = null;
	public static Locale SYSTEM_LOCALE = null;
	public static String BASEURL = null;
	public static String CONTENT_BASEURL = null;
	public static String APP_BASEURL = null;
	
	@Value("${sys.system.ldap.user.basedn}")
	public static String USER_BASEDN = null;

	public static DataSource DATASOURCE = null;
	public static String DB_SCHEME = null;
	public static String DBWRAP = null;

	public static String LDAP_SVR = null;
	public static String LDAP_PORT = null;
	public static String LDAP_ID = null;
	public static String LDAP_PWD = null;
	
	public static boolean CACHE_REQUIRED;
	public static long CACHE_TIME;
	
	public static String TEMP_UPLOAD_DIR = null;
	public static String USER_UPLOAD_DIR = null;

	public static String SUB_ACTION_KEY = "sub";
	public static String ITIM_GLOBALID_BASE = null;
}