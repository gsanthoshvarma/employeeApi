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

import com.sample.hr.po.CountryPO;
import com.sample.hr.po.DepartmentPO;
import com.sample.hr.po.EmployeePO;
import com.sample.hr.po.JobPO;
import com.sample.hr.po.LocationPO;
import com.sample.hr.po.RegionPO;

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
		sessionFactoryBean.setAnnotatedClasses(
				EmployeePO.class,
				DepartmentPO.class,
				CountryPO.class,
				JobPO.class,
				LocationPO.class,
				RegionPO.class);;
		return sessionFactoryBean;
	}
	
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.current_session_context_class", "thread");
		//prop.setProperty("hibernate.generate_statistics", "true");
		prop.setProperty("hibernate.format_sql", "true");
		prop.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		prop.setProperty("hibernate.cache.use_second_level_cache", "true");
		prop.setProperty("hibernate.cache.use_query_cache", "true");
		prop.setProperty("hibernate.use_sql_comments","true");
		prop.setProperty("hibernate.max_fetch_depth","3"); //Instruction to hibernate fetch max 3 level of childs.
		return prop;
	}
	
	
}
