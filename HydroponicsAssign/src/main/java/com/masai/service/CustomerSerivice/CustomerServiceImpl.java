package com.masai.service.CustomerSerivice;


import java.util.List;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repositories.CustomerDao;
import com.masai.repositories.SessionDao;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	@Autowired
	private CustomerDao CustomerDao; 
	
	@Autowired
	private SessionDao sessionDao;
	

	
	
	
	@Override
	public Customer addCustomer(Customer res) throws CustomerException {
		
		 Customer existingCustomer= CustomerDao.findByMobileNumber(res.getMobileNumber());
		 
		 if(existingCustomer != null) 
				throw new CustomerException("Customer Already Registered with Mobile number");
		 		
		 return CustomerDao.save(res);	
	}

	
}
