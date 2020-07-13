package com.thanhduyen.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thanhduyen.constant.SystemConstant;
import com.thanhduyen.model.NewModel;
import com.thanhduyen.paging.PageRequest;
import com.thanhduyen.paging.Pageble;
import com.thanhduyen.service.ICategoryService;
import com.thanhduyen.service.INewService;
import com.thanhduyen.sort.Sorter;
import com.thanhduyen.utils.FormUtil;
import com.thanhduyen.utils.MessageUtils;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{

	@Inject
	private INewService newService;

	@Inject
	private ICategoryService categoryService;
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Map giá trị name trong input hiden với field trong model
		//
		NewModel model = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if(model.getType().equals(SystemConstant.LIST)){
			//Phân trang
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));

			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int)Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			//Tra gia tri cho view jsp su dung
			view = "/views/admin/new/list_news.jsp";

		}else if (model.getType().equals(SystemConstant.EDIT)){
			if(model.getId() != null){
				model = newService.findOne(model.getId());
			}else {

			}
			//Truyen categories cho view
			request.setAttribute("categories", categoryService.findAll());
			//Tra gia tri cho view jsp su dung
			view = "/views/admin/new/edit.jsp";

		}
		MessageUtils.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);

	}

	
}
