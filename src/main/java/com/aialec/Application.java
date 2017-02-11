package com.aialec;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.aialec.model.Student;
import com.aialec.security.ApplicationSecurity;

@ComponentScan
@SpringBootApplication
public class Application {

	@Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new ApplicationSecurity();
    }
	
	public static HashMap<Long,Student> hmStudent;
	
	public static void main(String[] args) {
		hmStudent=new HashMap<Long,Student>();
		
		Student one=new Student("John","math");
	      hmStudent.put(new Long(one.getId()),one);
	 
	      SpringApplication application = new SpringApplication(Application.class);
	      application.run(args);
//	      SpringApplication.run(Application.class, args);
	 
	      Student two=new Student("Jane","history");
	      hmStudent.put(new Long(two.getId()),two);
	}
}
