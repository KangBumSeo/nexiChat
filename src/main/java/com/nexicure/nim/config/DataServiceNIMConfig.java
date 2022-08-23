package com.nexicure.nim.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.nexicure.nim.ConfigConstants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:NexicureConfig.properties")
@MapperScan(basePackages = {"com.nexicure.nim.dao"})
@ComponentScan(basePackages  = {"com.nexicure.nim.services.*"} )
public class DataServiceNIMConfig {

	private static Logger logger = LogManager.getLogger(DataServiceNIMConfig.class);

	@Value("${sys.system.db.jndi.namespace}")
	private String namespace;
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		try {
			//logger.info(namespace);
			Context context = new InitialContext();
			ConfigConstants.DATASOURCE = (DataSource)context.lookup(namespace);
			//logger.info(ConfigConstants.DATASOURCE);
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
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		//factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mappers/*.xml"));
        
        logger.info("SqlSessionFactoryBean    : " + factoryBean.toString());
        logger.info("DefaultSqlSessionFactory : " + factoryBean.getObject().toString());
        logger.info("Configuration            : " + factoryBean.getObject().getConfiguration().toString());
     //   logger.info("Configuration hasMapper  : " + factoryBean.getObject().getConfiguration().hasMapper(McodeDDAO.class));
		return factoryBean.getObject();
	}
	
}
