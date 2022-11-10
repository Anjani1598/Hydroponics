package com.masai.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.ItemException;

import com.masai.model.Item;
import com.masai.service.iItemService.IItemService;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

	
	@Autowired
	private IItemService iItemService;
	
	
	@PostMapping("/item")
	public  ResponseEntity<Item> addItemHandler(@RequestParam Integer catId,@RequestParam String itemName,@RequestParam Double cost, @RequestParam String thumbnail, @RequestParam(required = false) String key ) throws CustomerException,  ItemException{
		
		
		
		Item res_item = iItemService.addItem(catId,itemName,cost,thumbnail);
		
		
		return new ResponseEntity<Item>(res_item,HttpStatus.OK);
		
	}
	
	@GetMapping("/allitemInCategory")
	public  ResponseEntity<List<Item>> viewAllItemsInCategoryHandler(@RequestParam Integer catId, @RequestParam(required = false) String key ) throws CustomerException, ItemException {
		
		
		
		List<Item> res_item = iItemService.viewAllItemsInCategory(catId);
		
		
		return new ResponseEntity<List<Item>>(res_item,HttpStatus.OK);
		
	}
	
}
