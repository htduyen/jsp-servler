package com.thanhduyen.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;import org.jboss.weld.manager.InjectionTargetValidator;

public class FormUtil {
	
	
	// chuyển chuỗi String value mapper với cái filed trong model
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
		
		T object = null;  //model
		try {
			
			object = tClass.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
			
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}catch (InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
		return object;
	}
}
