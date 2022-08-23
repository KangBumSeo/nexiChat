package com.nexicure.nim.config;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.nexicure.nim.ConfigConstants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:NexicureConfig.properties")
@EnableJpaRepositories(basePackages = {"com.nexicure.nim.repo"})
@ComponentScan(basePackages  = {"com.nexicure.nim"} )
public class DataServiceConfig {

	private static Logger logger = LogManager.getLogger(DataServiceConfig.class);

	@Value("${sys.system.db.jndi.namespace}")
	private String namespace;
	
	@Bean
	public DataSource dataSource() {
		try {
			Context context = new InitialContext();
			ConfigConstants.DATASOURCE = (DataSource)context.lookup(namespace);
			return ConfigConstants.DATASOURCE;
			/*
			 * EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder(); return
			 * dbBuilder.setType(EmbeddedDatabaseType.H2).build();
			 */
		} catch (Exception e) {
			logger.error("Embedded DataSource bean cannot be created!", e);
			return null;
		}
	}
	
	
	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		//hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
		//hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProp.put("hibernate.show_sql", true);
		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.jdbc.batch_size", 10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
		return hibernateProp;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}


	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.nexicure.nim.entities");
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.afterPropertiesSet();
		return factoryBean.getNativeEntityManagerFactory();
	}
}
