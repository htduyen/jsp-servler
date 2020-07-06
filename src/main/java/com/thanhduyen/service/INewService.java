package com.thanhduyen.service;

import java.util.List;

import com.thanhduyen.model.NewModel;
import com.thanhduyen.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryID(Long cateID);
	
	NewModel save(NewModel news);
	
	NewModel update(NewModel news);
	
	void delete(long[] ids);
	
	List<NewModel> findAll(Pageble pageble);
	
	int getTotalItem();
	
}
