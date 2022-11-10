package com.masai.repositories;

import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Cart;
import com.masai.model.CustomerItem;

public interface CartDao extends JpaRepository<Cart, String> {
	
	public Cart findByCustomerItems(CustomerItem customerItem);

}
