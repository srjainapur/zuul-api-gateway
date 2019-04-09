package com.java.addr.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
	
	@RequestMapping(value="/address", method=RequestMethod.GET)
	public String address() {
		return "Employee is based out of Bangalore";
	}
}
