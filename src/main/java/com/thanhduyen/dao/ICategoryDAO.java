package com.thanhduyen.dao;

import java.util.List;

import com.thanhduyen.dao.impl.CategoryDAO;
import com.thanhduyen.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	
	List<CategoryModel> findAll();
}
