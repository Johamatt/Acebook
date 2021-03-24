package com.online.aceBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
    @Autowired
    public void condifureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
    @Autowired
    private UserDetailServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        http.csrf().disable(); // poistetaan csrf-tarkistus käytöstä h2-konsolin vuoksi       
        http.headers().frameOptions().sameOrigin(); // emt
		
		http
		.authorizeRequests().antMatchers("/css/**").permitAll()
		.antMatchers("/register").permitAll()
		.and()
		.authorizeRequests().anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/booklist")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
}