package com.thanhduyen.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.thanhduyen.dao.ICategoryDAO;
import com.thanhduyen.dao.impl.CategoryDAO;
import com.thanhduyen.model.CategoryModel;
import com.thanhduyen.service.ICategoryService;

public class CategoryService implements ICategoryService{

	
	/*
	 * private ICategoryDAO categoryDAO;
	 * 
	 * public CategoryService() { categoryDAO = new CategoryDAO(); }
	 */
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		
		return categoryDAO.findAll();
	}
	
	
}
