package com.sample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.sample.*"})
@EnableWebMvc
@Import(value= {EmployeeDataConfig.class,EmployeeDevDataConfig.class})
public class EmployeeConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("*")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("http://localhost:4200");
	}
	
	
}
