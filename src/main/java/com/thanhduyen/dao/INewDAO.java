package com.thanhduyen.dao;

import java.util.List;

import com.thanhduyen.model.NewModel;
import com.thanhduyen.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{
	
	NewModel findOne(Long id);
	
	List<NewModel> findByCategoryID(Long cateID);
	
	Long save(NewModel newModel);
	
	void update(NewModel updateModel);
	
	void delete(long id);

	List<NewModel> findAll(Pageble pageble);
	
	int getTotalItem(); 
	
}
