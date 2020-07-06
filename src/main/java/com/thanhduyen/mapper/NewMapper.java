 package com.thanhduyen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thanhduyen.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet rs) {
		
		try {
			
			NewModel newModel = new NewModel();
			newModel.setId(rs.getLong("id"));
			newModel.setTitle(rs.getString("title"));
			newModel.setContent(rs.getString("content"));
			newModel.setCategoryId(rs.getLong("categoryid"));
			newModel.setThumbnail(rs.getString("thumbnail"));
			newModel.setShortDescription(rs.getString("shortdescription"));
			newModel.setCreateDate(rs.getTimestamp("createddate"));
			newModel.setCreateBy(rs.getString("createdby"));
			if(rs.getTimestamp("modifieddate") != null) {
				newModel.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			if(rs.getTimestamp("modifiedby") != null) {
				newModel.setModifiedBy(rs.getString("modifiedby"));
			}
			return newModel;
			
		} catch (SQLException e) {
			return null;
		}
		
	}

}
