package com.aialec.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.aialec.model.User;
import com.aialec.model.UserRepository;
import com.aialec.model.UserRolesRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;
	
	@Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }
	
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername " + username);
		User user=userRepository.findByUserName(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=userRolesRepository.findRoleByUserName(username);
			for (String string : userRoles) {
				logger.info(username + " has role: " + string);
			}
			return new CustomUserDetails(user,userRoles);
		}
	}
		
}
