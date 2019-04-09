package com.java.employee.controller;

import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@RequestMapping(value="/employee/{name}", method=RequestMethod.GET)
	public String employee(@PathVariable("name") String name) {
		return "Welcome - " + name + " to Netflixx OSS"; 
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String employeeQParam(@QueryParam("name") String name, @RequestHeader("isLogEnabled") boolean isLogEnabled) {
		System.out.println("Executing Employee Controller is isLogEnabled :- " + isLogEnabled);
		return "Welcome " + name + " to zuul filter isLogEnabled " + isLogEnabled;
	}
}
