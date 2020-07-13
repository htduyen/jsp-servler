package com.thanhduyen.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thanhduyen.dao.GenericDAO;
import com.thanhduyen.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{

	ResourceBundle resourcebundle = ResourceBundle.getBundle("db");

	public Connection getConnection() {
		try {
			Class.forName(resourcebundle.getString("driverName"));
			String url = resourcebundle.getString("url");
			String user = resourcebundle.getString("user");
			String password = resourcebundle.getString("password");

//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/jsp_servlet";
//			String user = "root";
//			String password = "";
			return DriverManager.getConnection(url,user,password);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			return null;
		}
		
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		
		List<T> results = new ArrayList<T>();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Connection connection = null;
		try {
			 connection = getConnection();
			 preparedStatement = connection.prepareStatement(sql);
			 setParameter(preparedStatement, parameters);
			 resultset = preparedStatement.executeQuery();
			 while(resultset.next()) {
				 results.add(rowMapper.mapRow(resultset));
			 }
			 return results;
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultset != null) {
					resultset.close();
				}
			}catch(SQLException e3){
				return null;
			}
			
		}
		
		
	}

	private void setParameter(PreparedStatement preparedStatement, Object ... parameters) {
		
		try {
			for(int i = 0; i < parameters.length; i++) {
				
				 Object parameter = parameters[i];
				 int index = i +1;
				 if(parameter instanceof Long) {					 
						preparedStatement.setLong(index, (Long) parameter);				
				 }else if(parameter instanceof String) {
					 preparedStatement.setString(index, (String) parameter);
					 
				 }else if(parameter instanceof Integer) {
					 preparedStatement.setInt(index,(Integer) parameter);
				 }else if(parameter instanceof Timestamp) {
					 preparedStatement.setTimestamp(index,(Timestamp) parameter);
				 } else if(parameter == null) {
					 preparedStatement.setNull(index,Types.NULL);
				 }
				 
				 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(String sql, Object... parameters) {
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		try {
			 connection = getConnection();
			 connection.setAutoCommit(false);
			 preparedStatement = connection.prepareStatement(sql);
			 setParameter(preparedStatement, parameters);
			 
			 preparedStatement.executeUpdate();
			 connection.commit();
			
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				}catch (SQLException  e2) {
					e2.printStackTrace();
				}
			}
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				
			}catch(SQLException e3){
				e3.printStackTrace();
			}
			
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		ResultSet resultset = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Long id= null;
			 connection = getConnection();
			 connection.setAutoCommit(false);
			 preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			 setParameter(preparedStatement, parameters);
			 
			 preparedStatement.executeUpdate();
			 resultset = preparedStatement.getGeneratedKeys();
			 if(resultset.next()) {
					id =  resultset.getLong(1);
				}
			 connection.commit();
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				}catch (SQLException  e2) {
					e2.printStackTrace();
				}
			}
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultset != null) {
					resultset.close();
				}
			}catch(SQLException e3){
				e3.printStackTrace();
			}
			
		}
		return null;   
	}

	@Override
	public int count(String sql, Object... parameters) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Connection connection = null;
		try {
			int count = 0;
			 connection = getConnection();
			 preparedStatement = connection.prepareStatement(sql);
			 setParameter(preparedStatement, parameters);
			 resultset = preparedStatement.executeQuery();
			 while(resultset.next()) {
				 count = resultset.getInt(1); //count(*)
			 }
			 return count;
		} catch (SQLException e) {
			return 0;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultset != null) {
					resultset.close();
				}
			}catch(SQLException e3){
				return 0;
			}
			
		}
		
	}
}
