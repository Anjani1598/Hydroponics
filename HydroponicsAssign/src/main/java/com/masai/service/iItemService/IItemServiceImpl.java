package com.masai.service.iItemService;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.ItemException;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.CustomerItem;
import com.masai.model.Item;
import com.masai.repositories.CartDao;
import com.masai.repositories.CategoryDao;
import com.masai.repositories.CustomerItemDao;
import com.masai.repositories.ItemDao;
import com.masai.repositories.SessionDao;

@Service
public class IItemServiceImpl implements IItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CustomerItemDao customerItemDao;
	
	@Autowired
	private CartDao cartDao;

	@Override
	public Item addItem(Integer catId,String itemName,Double cost,String thumbnail) throws ItemException {
		
		
		
				Optional<Category> opt = categoryDao.findById(catId);
				
				if(opt.isPresent()) {
					
					Category cat = opt.get();
					
					if(cat.getCatId() == catId) {
						
						Item item =  new Item();
						item.setItemName(itemName);
						item.setCategory(cat);
						item.setCost(cost);
						item.setItemThumbnail(thumbnail);
						cat.getItems().add(item);
						
						
						return itemDao.save(item);
						
					}
				
				throw new ItemException("Category not found");

					
				}
				throw new ItemException("No Category Found");
				
				

		
		
	}
	@Override
	public List<Item> viewAllItemsInCategory(Integer catId) throws ItemException {
		
		
		Optional<Category> opt = categoryDao.findById(catId);
		
		if(opt.isPresent()) {
			return itemDao.findByCategory(opt.get());
		}
		throw new ItemException("No Category Found");
	}


}
