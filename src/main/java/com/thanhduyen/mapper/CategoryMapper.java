package com.thanhduyen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thanhduyen.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet rs) {
		
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(rs.getLong("id"));
			categoryModel.setName(rs.getString("name"));
			categoryModel.setCode(rs.getString("code"));
			return categoryModel;
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	
}
