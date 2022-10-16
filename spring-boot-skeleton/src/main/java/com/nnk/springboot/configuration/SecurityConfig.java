package com.nnk.springboot.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Bean
	public JdbcUserDetailsManager userDetailsService() {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@SuppressWarnings("deprecation")
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    //return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select username,password,'true' as enabled from Users where username=?")//TODO : revoir requêtes
		.authoritiesByUsernameQuery("select username, role from Users where username=?");
	}
	
	//TODO : ajouter contraintes	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        //.antMatchers("/").hasAnyRole("ADMIN","USER")
        .antMatchers("/login*","/home").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()//.loginPage("/login.html")
        .defaultSuccessUrl("/bidList/list",true)
        .and()
        .logout();
	}
	
}
