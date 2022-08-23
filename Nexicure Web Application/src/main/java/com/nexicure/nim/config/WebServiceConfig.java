package com.nexicure.nim.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.nexicure.nim.ConfigConstants;


@Configuration
@ComponentScan(basePackages  = {"com.nexicure.nim.services" } )
@MapperScan(basePackages = {"com.nexicure.nim.dao"})
public class WebServiceConfig {
	private static Logger logger = LogManager.getLogger(WebServiceConfig.class);
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	
	
	@Bean
	public DataSource dataSource() {
		try {
			Context context = new InitialContext();
			ConfigConstants.DATASOURCE = (DataSource)context.lookup("java:comp/env/jdbc/nexicure");
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
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		org.apache.ibatis.logging.LogFactory.useLog4J2Logging();
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mappers/*.xml"));
        
        logger.info("SqlSessionFactoryBean    : " + factoryBean.toString());
        logger.info("DefaultSqlSessionFactory : " + factoryBean.getObject().toString());
        logger.info("Configuration            : " + factoryBean.getObject().getConfiguration().toString());
     //   logger.info("Configuration hasMapper  : " + factoryBean.getObject().getConfiguration().hasMapper(McodeDDAO.class));
		return factoryBean.getObject();
	}
	
}
