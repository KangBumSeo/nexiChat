package com.nexicure.nim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;


@Configuration
@EnableWebMvc
@ComponentScan("com.nexicure.nim.controller")
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Bean
	WebContentInterceptor webChangeInterceptor() {
		WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
		webContentInterceptor.setCacheSeconds(0);
		webContentInterceptor.setSupportedMethods("GET", "POST", "PUT", "DELETE");
		return webContentInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	//	registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(themeChangeInterceptor());
	//	registry.addInterceptor(webChangeInterceptor());
	}
   
	@Bean
	ThemeChangeInterceptor themeChangeInterceptor() {
		return new ThemeChangeInterceptor();
	}
	
	@Bean 
	ResourceBundleThemeSource themeSource() {
		return new ResourceBundleThemeSource();
	}
	
	@Bean
	CookieThemeResolver themeResolver() {
		CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
		cookieThemeResolver.setDefaultThemeName("standard");
		cookieThemeResolver.setCookieMaxAge(3600);
		cookieThemeResolver.setCookieName("theme");
		return cookieThemeResolver;
	}
   
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/resources/**").addResourceLocations("/")
	   		.setCachePeriod(31556926);
	}
	
	//@ Bean StandardServletMultipartResolver multipartResolver() {
	//	return new StandardServletMultipartResolver();
	//}
	//@ Bean
	//public MultipartResolver filterMultipartResolver() {
	//    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	//    multipartResolver.setDefaultEncoding("UTF-8");
	//    multipartResolver.setMaxUploadSize(100000);
	//    return multipartResolver;
	//}
	
	@Bean
	public Validator validator() {
		final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	
	@Bean
	ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/messages_nim");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setFallbackToSystemLocale(true);
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}
	
	
//	@ Bean
//	LocaleChangeInterceptor localeChangeInterceptor() {
//		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//		interceptor.setParamName("lang");
//		return interceptor;
//	}

//	@ Bean
//	CookieLocaleResolver localeResolver() {
//		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//		cookieLocaleResolver.setCookieMaxAge(3600);
//		cookieLocaleResolver.setCookieName("locale");
//		return cookieLocaleResolver;
//	}
	
	// <=> <mvc:annotation-driven validator="validator"/>
	@Override
	public Validator getValidator() {
		return validator();
	}
	
	// <=> <mvc:default-servlet-handler/>
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// <=> <mvc:view-controller .../>
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
