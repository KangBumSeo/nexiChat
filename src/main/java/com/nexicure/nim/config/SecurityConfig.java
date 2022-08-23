package com.nexicure.nim.config;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import com.nexicure.nim.ConfigConstants;
import com.nexicure.nim.services.UserDetailsServiceImpl;

//import com.nexicure.nim.service.UserDetailsServiceImp;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private DataSource dataSource;
	
	/*
	 * @ Autowired public void setDataSource(DataSource dataSource) {
	 * this.dataSource = dataSource; }
	 */
	
	
	@Bean 
	public UserDetailsService userDetailsService() { 
		return new UserDetailsServiceImpl(); 
	}
	
	  
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			auth
					.inMemoryAuthentication()
					.passwordEncoder(passwordEncoder)
					.withUser("ID").password(passwordEncoder.encode("ID")).roles("USER");
			
	//		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
			
//			auth.ldapAuthentication().contextSource().url("");
			
	//		auth.jdbcAuthentication().dataSource(dataSource)
	//		.authoritiesByUsernameQuery("SELECT company_code AS username,  'USER' as authority FROM mcode_d WHERE company_code = ?")
	//		.usersByUsernameQuery("SELECT company_code AS username, code_class AS password , 'true' as enabled FROM mcode_d WHERE company_code = ?")
		//	.withUser("user").password(passwordEncoder.encode("user")).roles("USER")
		//	;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/*","/base/*").permitAll()
				.and()
				.formLogin()
				.usernameParameter("uid")
				.passwordParameter("uid")
				.loginPage("/security/login")
				.loginProcessingUrl("/security/proc/login")
				.successForwardUrl("/security/proc/binding")
				.failureUrl("/security/loginfail")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.and()
				.csrf().disable();
		//csrfTokenRepository(repo());
	}

	@Bean
	public MultipartResolver filterMultipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setDefaultEncoding("UTF-8");
	    multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
	    return multipartResolver;
	}
	
	//@ Bean
	public CsrfTokenRepository repo() {
		HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
		repo.setParameterName("_csrf");
		repo.setHeaderName("X-CSRF-TOKEN");
		return repo;
	}
}