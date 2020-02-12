package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sample.security.EmployeeBasicAuthenticationEntryPoint;
import com.sample.security.MyUserDetailsService;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({ "com.sample.*" })
public class EmployeeSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] PUBLIC_MATCHERS = {
			//"/employees/",
			"/department/"	
			 };

	@Autowired
	EmployeeBasicAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	MyUserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
		  	.authorizeRequests()
		  	.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS).permitAll().antMatchers("/employees/**").authenticated()
			.and().httpBasic().realmName("employeeAPI").authenticationEntryPoint(authenticationEntryPoint);
	}
	
	/*@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new ShaPasswordEncoder("SHA256").encodePassword("admin", null);
	}*/
	
}
