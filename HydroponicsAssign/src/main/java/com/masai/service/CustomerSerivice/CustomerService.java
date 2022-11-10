package com.masai.service.CustomerSerivice;

import java.util.List;


import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;

public interface CustomerService {
	
	
	public Customer addCustomer(Customer res) throws CustomerException;
	
	
	
}

