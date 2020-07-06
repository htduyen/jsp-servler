package com.thanhduyen.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil(String value) {
		  this.value = value;
	}
	
	// chuyển chuỗi String value mapper với cái filed trong model
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static HttpUtil of(BufferedReader reader) {
		
		StringBuilder sb = new StringBuilder();
		try {
			String line ;
			while((line = reader.readLine()) != null) {
				sb.append(line); // ==> trả về 1 chuổi string
				
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		return new HttpUtil(sb.toString());
	}
}
