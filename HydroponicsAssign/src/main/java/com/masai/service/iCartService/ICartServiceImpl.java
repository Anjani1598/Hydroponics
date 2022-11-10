package com.masai.service.iCartService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.CustomerItem;
import com.masai.model.Cart;
import com.masai.model.Item;
import com.masai.repositories.CustomerDao;
import com.masai.repositories.CustomerItemDao;
import com.masai.repositories.CartDao;
import com.masai.repositories.ItemDao;
import com.masai.repositories.SessionDao;

@Service
public class ICartServiceImpl implements ICartService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CartDao CartDao;
	
	@Autowired
	private SessionDao sessionDao;
	

	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private CustomerItemDao customerItemDao;
	

	
	

	@Override
	public Cart addItemToCart(Integer itemId,String key) throws CartException, ItemException, CustomerException {
		
		CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new CustomerException("Please provide valid key");
		}
		
		Optional<Customer> opt = customerDao.findById(loggedInUser.getUserId());
		CustomerItem customerItem = new CustomerItem();

		if(opt.get().getCart() == null) {
			
			Cart cart = new Cart();
			
			cart.setCustomer(opt.get());
			
			opt.get().setCart(cart);
	
			
			
				Optional<Item> itemopt = itemDao.findById(itemId);
				
				if(itemopt.isPresent()) {
					
					Item i = itemopt.get();
					customerItem.setCart(cart);
					customerItem.setItem(i);
					customerItem.setQuantity(1);
					i.getCustomerItems().add(customerItem);
					cart.getCustomerItems().add(customerItem);
					return CartDao.save(cart);
					
				}
				throw new CartException("item Does not exist");
					
					
				
			
			
			
		
		}else {
			
			Cart cart = opt.get().getCart();
			
				
			Optional<Item> itemopt = itemDao.findById(itemId);
			
			if(itemopt.isPresent()) {
				
				Item i = itemopt.get();
				customerItem.setCart(cart);
				customerItem.setItem(i);
				customerItem.setQuantity(1);
				i.getCustomerItems().add(customerItem);
				cart.getCustomerItems().add(customerItem);
				return CartDao.save(cart);
				
			}
			throw new CartException("item Does not exist");

			
					
				
			

		}

		
	}

	@Override
	public Cart increaseQuantity(Integer customerItemId,String key)throws CartException, ItemException, CustomerException {
		
		CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new CustomerException("Please provide valid key");
		}
		
		Optional<Customer> opt = customerDao.findById(loggedInUser.getUserId());
		CustomerItem customerItem = customerItemDao.findById(customerItemId).get();
		
		if(opt.get().getCart() == null) {
			
			throw new CartException("Your Cart is Empty");
			
		}else {
			
			Cart cart = opt.get().getCart();
			if(cart.getCustomerItems().contains(customerItem)) {

				customerItem.setQuantity(customerItem.getQuantity()+1);
				
				customerItemDao.save(customerItem);
				return cart;
				
				
				
			}
			
				throw new ItemException("No Item Found");
			
			
		}

		
	}





	@Override
	public Cart reduceQuantity(Integer customerItemId, Integer quantity,String key)throws CartException, ItemException, CustomerException {
		
CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new CustomerException("Please provide valid key");
		}
		
		Optional<Customer> opt = customerDao.findById(loggedInUser.getUserId());
		CustomerItem customerItem = customerItemDao.findById(customerItemId).get();
		
		if(opt.get().getCart() == null) {
			
			throw new CartException("Your Cart is Empty");
			
		}else {
			
			Cart cart = opt.get().getCart();
			System.out.println("hai");
			if(cart.getCustomerItems().contains(customerItem)) {
				
				customerItem.setQuantity(customerItem.getQuantity()-1);
				customerItemDao.save(customerItem);
				return cart;
				
				
				
			}
			
				throw new ItemException("No Item Found");
			
			
		}

		
		
	}



	@Override
	public Cart  removeItem(Integer customerItemId,String key) throws CartException, ItemException {

		
		CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new CartException("Please provide valid key");
		}
		
		Optional<Customer> opt = customerDao.findById(loggedInUser.getUserId());
		CustomerItem customerItem = customerItemDao.findById(customerItemId).get();
		System.out.println("Hi");

		if(opt.get().getCart() == null) {
			
			throw new CartException("Your Cart is Empty");
			
		}else {
			
			Cart cart = opt.get().getCart();
			System.out.println("Hi");
			System.out.println(customerItem);
			System.out.println(cart.getCustomerItems());
			
			if(cart.getCustomerItems().contains(customerItem)) {
				
				Item item = customerItem.getItem();
				
				
				cart.getCustomerItems().remove(customerItem);
				customerItem.setCart(null);
				customerItem.setItem(null);
				item.getCustomerItems().remove(customerItem);
				customerItemDao.delete(customerItem);

				
				return CartDao.save(cart);
				
			}
			
			throw new ItemException("Item Does Not Exist");
			
		}
		
		
	}
	
	@Override
	public List<CustomerItem> getAllCartItems(String key) throws CartException, CustomerException {
		CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		

		if(loggedInUser==null) {
			throw new CartException("Please provide valid key");
			
		}
		
		Optional<Customer> opt = customerDao.findById(loggedInUser.getUserId());
		
		if(opt.isPresent()) {
			Cart cart = opt.get().getCart();
			
			List<CustomerItem> cartItems = customerItemDao.findByCart(cart);
			
			if(cartItems.size()>0) {
				
				return customerItemDao.findByCart(cart);
				
			}else {
				throw new CartException("Your cart is empty");
			}
			
		}
		
		throw new CartException("invali details");
		
		
			
			
			

		
		

	}

	

}
