package com.aialec.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationSecurity.class);
	

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	
//	@Autowired
//    private CustomAuthenticationProvider authProvider;
// 
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//  
//        auth.authenticationProvider(authProvider);
//    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("here AuthConfig");
        
    	http.authorizeRequests().antMatchers("/rest/**").hasRole("USER");
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.httpBasic();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    	builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
		.password("admin").roles("ADMIN");
    }
}
