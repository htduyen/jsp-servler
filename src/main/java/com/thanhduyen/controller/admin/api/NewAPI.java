package com.thanhduyen.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhduyen.model.NewModel;
import com.thanhduyen.service.INewService;
import com.thanhduyen.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService newService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper(); 
		//dữ liệu gửi lên
		request.setCharacterEncoding("UTF-8");
		//dữ liệu trả về
		response.setContentType("application/json");
		
		// HttpUtil.of(request.getReader()) ==> String
		// .toModel(NewModel.class) map	
		//Map json trả về với các field trong model su dung jackson-databind 
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
//		
		newModel = newService.save(newModel);
		System.out.println("NewModel: " + newModel);
		
	//tra response cho client dang json
		mapper.writeValue(response.getOutputStream(), newModel);
	}
	
	
	//Cap nhật
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper(); 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		NewModel updateNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		updateNew = newService.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);

	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper(); 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
	
	
}
