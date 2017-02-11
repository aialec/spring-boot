package com.aialec.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
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
	
	 @Autowired 
	 private UserDetailsService userDetailsService;
	 
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	 } 
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("here AuthConfig");
        
    	http.authorizeRequests().antMatchers("/rest/**").hasRole("USER");
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.httpBasic();
		
		http.sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    	builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
		.password("admin").roles("ADMIN");
    }
    
    
    @Bean(name="passwordEncoder")
       public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
       }
}
