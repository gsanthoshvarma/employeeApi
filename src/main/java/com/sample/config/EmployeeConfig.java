package com.sample.config;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.sample.*"})
@EnableWebMvc
public class EmployeeConfig {
	
	
	@Bean
	public DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/employeeDB");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() throws NamingException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		return sessionFactoryBean;
	}
	
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.current_session_context_class", "thread");
		return prop;
	}
}
