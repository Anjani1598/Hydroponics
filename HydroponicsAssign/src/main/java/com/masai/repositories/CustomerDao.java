package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Cart;
import com.masai.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
	
	public Customer findByMobileNumber(String mobileNumber);
	
	public Customer findByCart(Cart cart);
	
	

}
