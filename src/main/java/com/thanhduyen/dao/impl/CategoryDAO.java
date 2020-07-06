package com.thanhduyen.dao.impl;

import java.util.List;

import com.thanhduyen.dao.ICategoryDAO;
import com.thanhduyen.mapper.CategoryMapper;
import com.thanhduyen.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{
	 
	
	
	@Override
	public List<CategoryModel> findAll() {

		String sql = "Select * from category";
		return query(sql,new CategoryMapper());
	}
}
				
