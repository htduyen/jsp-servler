package com.thanhduyen.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thanhduyen.model.NewModel;
import com.thanhduyen.model.UserModel;
import com.thanhduyen.service.ICategoryService;
import com.thanhduyen.service.INewService;


@WebServlet(urlPatterns =  {"/trang-chu", "/login"} )
public class HomeController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICategoryService cateoryService;
	
	@Inject 
	private INewService newService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = "Bài viết 44";
		String content = "Bài viết 44 content";
		Long cateId = 1L;
		
		//req.setAttribute("categories", cateoryService.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(req, resp);
			
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
