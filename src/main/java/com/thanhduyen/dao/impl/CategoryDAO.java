package com.thanhduyen.dao.impl;

import java.util.List;

import com.thanhduyen.dao.ICategoryDAO;
import com.thanhduyen.mapper.CategoryMapper;
import com.thanhduyen.mapper.NewMapper;
import com.thanhduyen.model.CategoryModel;
import com.thanhduyen.model.NewModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{
	 
	
	
	@Override
	public List<CategoryModel> findAll() {

		String sql = "Select * from category";
		return query(sql,new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category where id = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public CategoryModel findOneCode(String code) {
		String sql = "SELECT * FROM category where code = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty() ? null : category.get(0);
	}
}
				
