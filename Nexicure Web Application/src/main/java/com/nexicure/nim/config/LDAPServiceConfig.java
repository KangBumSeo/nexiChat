package com.nexicure.nim.config;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate; 
import org.springframework.ldap.core.support.LdapContextSource; 
import org.springframework.ldap.pool.factory.PoolingContextSource;
import org.springframework.ldap.pool.validation.DefaultDirContextValidator;
import org.springframework.ldap.transaction.compensating.manager.TransactionAwareContextSourceProxy;

import com.nexicure.es.services.crypt.seed.Seed; 

/* <Sample>
 * query().where("objectclass").is("person"),
 * .and(query().where("cn").is("Doe").or("cn").is("Doo));
 * -------------------------------------------------------------
 * @ is - specifies an equals condition (=).
 * 
 * @ gte - specifies a greater than or equals condition (>=).
 * 
 * @ lte - specifies a less than or equals condition (< =).
 * 
 * @ like - specifies a "like" condition where wildcards can be included in the
 * query, e.g. where("cn").like("J*hn Doe") will result int the filter (cn=J*hn
 * Doe).
 * 
 * @ whitespaceWildcardsLike - specifies a condition where all whitespace is
 * replaced with wildcards, e.g. where("cn").whitespaceWildcardsLike("John Doe")
 * will result in the filter (cn=John*Doe).
 * 
 * @ isPresent - specifies condition that checks for the presence of an attribute,
 * e.g. where("cn").isPresent() will result in the filter (cn=*).
 * 
 * @ not - specifies that the current condition should be negated, e.g.
 * where("sn").not().is("Doe) will result in the filter (!(sn=Doe))
 */

@Configuration
@PropertySource("classpath:NexicureConfig.properties")
@ComponentScan(basePackages  = {"com.nexicure.nim.services.biz","com.nexicure.nim.services.service"} )
public class LDAPServiceConfig {
	private static Logger logger = LogManager.getLogger(LDAPServiceConfig.class);

	@Value("${sys.system.ldap.url}")
    private String url;
	
	@Value("${sys.system.ldap.user.basedn}")
	private String basedn;
	
	@Value("${sys.system.ldap.adminid}")
	private String adminid;

	@Value("${sys.system.ldap.adminpw}")
	private String adminpw;

	@Bean
	public LdapContextSource ldapContextSource() {
		
		logger.info("LDAPServiceConfigInfo : " + url);
		logger.info("LDAPServiceConfigInfo : " + adminid);
		logger.info("LDAPServiceConfigInfo : " + adminpw);

		LdapContextSource contextSource = new LdapContextSource();
	    
		contextSource.setUrl(url); 
		contextSource.setUserDn(adminid); 
		contextSource.setPassword(new Seed().decrypt(adminpw)); 
		
	    return contextSource;
	}

	@Bean
	public ContextSource poolingLdapContextSource() {
	    PoolingContextSource poolingContextSource = new PoolingContextSource();
	    poolingContextSource.setDirContextValidator(new DefaultDirContextValidator());
	    poolingContextSource.setContextSource(ldapContextSource());
	    poolingContextSource.setTestOnBorrow(true);
	    poolingContextSource.setTestWhileIdle(true);

	    TransactionAwareContextSourceProxy proxy = new TransactionAwareContextSourceProxy(poolingContextSource);
	    return proxy;
	}


	@Bean
	public LdapTemplate ldapTemplate() {
	    return new LdapTemplate(poolingLdapContextSource());
	}
}
