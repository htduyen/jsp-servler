package com.thanhduyen.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.thanhduyen.dao.ICategoryDAO;
import com.thanhduyen.dao.INewDAO;
import com.thanhduyen.model.CategoryModel;
import com.thanhduyen.model.NewModel;
import com.thanhduyen.paging.Pageble;
import com.thanhduyen.service.INewService;

public class NewService implements INewService{

	@Inject
	private INewDAO newDao;

	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewModel> findByCategoryID(Long cateID) {
				
		return newDao.findByCategoryID(cateID);
	}

	@Override
	public NewModel save(NewModel newModel) {
		
		newModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
		//newModel.setCreateBy("Thanh Duyen");
		CategoryModel categoryModel = categoryDAO.findOneCode(newModel.getCategoryCode());
		newModel.setCategoryId(categoryModel.getId());
		Long newID = newDao.save(newModel);
	 	return newDao.findOne(newID);
	}

	@Override
	public NewModel update(NewModel updateNews) {
		NewModel oldNew = newDao.findOne(updateNews.getId());
		updateNews.setCreateDate(oldNew.getCreateDate());
		updateNews.setCreateBy(oldNew.getCreateBy());
		updateNews.setModifiedBy("Thanh Duyen");
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOneCode(updateNews.getCategoryCode());
		updateNews.setCategoryId(categoryModel.getId());
		newDao.update(updateNews);
		return newDao.findOne(updateNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			//delete comemt truoc
			//delete new sau
			newDao.delete(id);
		}
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		
		return newDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		
		return newDao.getTotalItem();
	}

	@Override
	public NewModel findOne(Long id) {
		NewModel model = newDao.findOne(id);
		CategoryModel cate = categoryDAO.findOne(model.getCategoryId());
		//Khi them moi thi categoryCode == null khi update thi đã có categoryCode anh huong toi view option
		model.setCategoryCode(cate.getCode());
		return model;
	}

}
