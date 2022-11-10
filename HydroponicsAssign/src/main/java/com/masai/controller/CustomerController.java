package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerSerivice.CustomerService;
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
	
	

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer")
	
	public ResponseEntity<Customer> addCustomerHandler(@RequestBody Customer res) throws CustomerException{
		
		Customer addedRestaurant = customerService.addCustomer(res);
		
		return new ResponseEntity<Customer>(addedRestaurant,HttpStatus.CREATED);
	}

}
