package com.sample.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

@Configurable
public class PropertyPlaceHolderConfig {

	
	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceObj() {
		PropertyPlaceholderConfigurer property = new PropertyPlaceholderConfigurer();
		property.setLocation(new ClassPathResource("employee.properties"));
		return property;
	}
}
