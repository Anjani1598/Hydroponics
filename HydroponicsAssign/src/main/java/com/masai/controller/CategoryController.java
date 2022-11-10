package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.model.Category;
import com.masai.service.iCategoryService.ICategoryService;


@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
	
	
	
	@Autowired
	private ICategoryService iCategoryService;
	
	
	@PostMapping("/category")
	public ResponseEntity<Category> addCategoryHandler(@RequestParam String categoryName,@RequestParam(required =  false) String key) throws CustomerException{
		
		
		Category category =  iCategoryService.addCategory(categoryName);
		return new ResponseEntity<Category>(category,HttpStatus.CREATED);
	}

}
