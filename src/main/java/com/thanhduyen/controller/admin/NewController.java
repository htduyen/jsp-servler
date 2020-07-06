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
import com.thanhduyen.service.INewService;
import com.thanhduyen.sort.Sorter;
import com.thanhduyen.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{

	@Inject
	private INewService newService;
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Map giá trị name trong input hiden với field trong model
		//
		NewModel model = FormUtil.toModel(NewModel.class, request);
		//Phân trang
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
		
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int)Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		request.setAttribute(SystemConstant.MODEL, model);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list_news.jsp");
		rd.forward(request, response);
	}

	
}
