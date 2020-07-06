package com.thanhduyen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.thanhduyen.dao.INewDAO;
import com.thanhduyen.mapper.NewMapper;
import com.thanhduyen.model.NewModel;
import com.thanhduyen.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO{

	
	@Override
	public List<NewModel> findByCategoryID(Long cateID) {
		
		String sql = "Select * from news where categoryid = ?";
		return query(sql, new NewMapper(), cateID);
		 
	}

	@Override
	public Long save(NewModel newsModel) {
				
		//String sql = "INSERT INTO news(title, content, categoryid) values(?,?,?)";
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO news(title, content, categoryid, thumbnail, ");
		sql.append("shortdescription, createddate, createdby) ");
		sql.append(" values(?,?,?,?,?,?,?)");
		return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(), 
				newsModel.getThumbnail(), newsModel.getShortDescription(), newsModel.getCreateDate(), newsModel.getCreateBy());
		  
	}

	@Override
	public NewModel findOne(Long id) {

		String sql = "SELECT * FROM news where id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id); 
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {

		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, ");
		sql.append("shortdescription = ?, content = ?, categoryid = ?, ");
		sql.append("createddate = ?, createdby = ?, ");
		sql.append("modifieddate = ?, modifiedby = ? ");
		sql.append(" WHERE id = ?");
		System.out.println("SQL: " + sql.toString());
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), 
				updateNew.getShortDescription(),updateNew.getContent(), updateNew.getCategoryId(), 
				updateNew.getCreateDate(), updateNew.getCreateBy(), updateNew.getModifiedDate(), updateNew.getModifiedBy(), updateNew.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		
		//String sql = "Select * from news limit ?, ?";
		StringBuilder sql = new StringBuilder("Select * from news");
		if(pageble.getSorter() != null) {
			sql.append(" order by " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" limit " + pageble.getOffset() +", " + pageble.getLimit() +"");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}
}
