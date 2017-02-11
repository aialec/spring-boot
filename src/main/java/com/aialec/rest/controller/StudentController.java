package com.aialec.rest.controller;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aialec.Application;
import com.aialec.model.Student;

@RestController
@RequestMapping(value="/rest")
public class StudentController {
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	   public HashMap<Long,Student> getAllStudents(Principal principal){
		principal.getName();
	      return Application.hmStudent;
	   }
}
