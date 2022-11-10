package com.masai.service.iCartService;

import com.masai.model.CustomerItem;
import com.masai.model.Cart;
import com.masai.model.Item;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;

public interface ICartService {
	
	public Cart addItemToCart(Integer itemId,String key)throws CartException, ItemException, CustomerException;
	
	public Cart increaseQuantity(Integer itemId,String key) throws CartException,ItemException,CustomerException;
	
	public Cart reduceQuantity(Integer itemId, Integer quantity,String key)throws CartException,ItemException, CustomerException;

	public Cart removeItem(Integer itemId, String key)throws CartException,ItemException;
	
	
	public List<CustomerItem> getAllCartItems(String key)throws CartException,CustomerException;

}
