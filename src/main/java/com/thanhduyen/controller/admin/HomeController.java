package com.thanhduyen.controller.admin;

import com.thanhduyen.utils.SessionUtil;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns =  {"/admin-home"} )
public class HomeController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	ResourceBundle resourcebundle = ResourceBundle.getBundle("message");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


			//Apply view cho trang admin tại body tag
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
			rd.forward(request, response);



	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
