package com.online.aceBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void condifureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/css/**").permitAll().
		antMatchers("/h2-console/**").permitAll()
		.antMatchers("/register").permitAll().
		antMatchers("/images/**").permitAll().
		antMatchers("/video/**").permitAll().
		antMatchers("/viewfile/{id}").permitAll().
		antMatchers("/api/**").permitAll().
		and().authorizeRequests().anyRequest().authenticated().
		and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true).permitAll().
		and().logout().permitAll().
		and().csrf().disable().headers().frameOptions().disable(); 
	}
}
