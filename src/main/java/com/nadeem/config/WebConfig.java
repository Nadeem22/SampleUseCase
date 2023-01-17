package com.nadeem.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.nadeem" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	@Bean
	public MultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(50000000);
	    return multipartResolver;
	}
	
	/*
	 * @Bean public MultipartConfigElement multipartConfigElement() {
	 * MultipartConfigFactory factory = new MultipartConfigFactory();
	 * factory.setMaxFileSize("128KB"); factory.setMaxRequestSize("128KB"); return
	 * factory.createMultipartConfig(); }
	 * 
	 * @Bean public ServletRegistrationBean<DispatcherServlet>
	 * multipartDispatcherServlet() { DispatcherServlet servlet = new
	 * DispatcherServlet(); ServletRegistrationBean<DispatcherServlet> registration
	 * = new ServletRegistrationBean<>(servlet);
	 * registration.setMultipartConfig(multipartConfigElement()); return
	 * registration; }
	 */
}