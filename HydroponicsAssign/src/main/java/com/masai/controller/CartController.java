package com.masai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.CustomerItem;
import com.masai.model.Cart;
import com.masai.model.Item;
import com.masai.repositories.CustomerDao;
import com.masai.repositories.SessionDao;
import com.masai.service.iCartService.ICartService;

@RestController
@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	private ICartService iCartService;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
	
	@PostMapping("/cart")
	public  ResponseEntity<Cart> addItemHandler(@RequestParam Integer itemId, @RequestParam(required = false) String key ) throws CustomerException, CartException, ItemException {
		
		
				
		Cart Cart = iCartService.addItemToCart(itemId, key);
		
		return new ResponseEntity<Cart>(Cart,HttpStatus.OK);
		
	}
	
	@GetMapping("/cart")
	public  ResponseEntity<List<CustomerItem>> getAllCartItem(@RequestParam(required = false) String key ) throws CustomerException, CartException, ItemException {
		
		
				List<CustomerItem> cartItems =  iCartService.getAllCartItems(key);
		
		return new ResponseEntity<List<CustomerItem>>(cartItems,HttpStatus.OK);
		
	}
	
	
	
	@PutMapping("/cart/increaseqty")
	public  ResponseEntity<Cart> increaseQuantityHandler(@RequestParam Integer customerItemId, @RequestParam(required = false) String key ) throws CustomerException, CartException, ItemException {
		
		
				
		Cart Cart = iCartService.increaseQuantity(customerItemId, key);
		
		return new ResponseEntity<Cart>(Cart,HttpStatus.OK);
		
	}
	
	@PutMapping("/cart/reduceqty")
	public  ResponseEntity<Cart> reduceQuantityHandler(@RequestParam Integer itemId,@RequestParam(required = false) Integer qty, @RequestParam(required = false) String key ) throws CustomerException, CartException, ItemException {
		
		
				
		Cart Cart = iCartService.reduceQuantity(itemId,qty, key);
		
		return new ResponseEntity<Cart>(Cart,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/cart/removeItem")
	public  ResponseEntity<Cart> removeItemHandler(@RequestParam Integer customerItemId, @RequestParam(required = false) String key ) throws CustomerException, CartException, ItemException {
		
		
				
		Cart Cart = iCartService.removeItem(customerItemId,key);
		
		return new ResponseEntity<Cart>(Cart,HttpStatus.OK);
		
	}
	

	

}
