package com.sample.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.sample.hr.po.CountryPO;
import com.sample.hr.po.DepartmentPO;
import com.sample.hr.po.EmployeePO;
import com.sample.hr.po.JobPO;
import com.sample.hr.po.LocationPO;
import com.sample.hr.po.RegionPO;

@Profile({"default","h2"})
public class EmployeeDevDataConfig {
	
	@Bean
	public DataSource getH2DataSource() throws NamingException {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(false)
				.setName("testdb")
				.setScriptEncoding("UTF-8")
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("classpath:migration/employee-creation.sql","classpath:migration/employee-insert.sql")
				.ignoreFailedDrops(true)
				.build();
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) throws NamingException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		sessionFactoryBean.setAnnotatedClasses(
				EmployeePO.class,
				DepartmentPO.class,
				CountryPO.class,
				JobPO.class,
				LocationPO.class,
				RegionPO.class);
		//sessionFactoryBean.setPackagesToScan("com.sample.hr.po.*"); // No Need to add all the persistence classes, leveraging api methods
		return sessionFactoryBean;
	}
	
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
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
