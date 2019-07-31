package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mvc.bean.RegisterBean;
import com.mvc.util.DBConnection;

public class RegisterDao {
	public static String registerUser(RegisterBean registerBean)
	 {
	 String name = registerBean.getName();
	 String email = registerBean.getEmail();
	 String password = registerBean.getPassword();
	 String phoneNo = registerBean.getPhoneNo();
	 String address = registerBean.getAddress();
	 String pincode = registerBean.getPincode();
	 String rePassword = registerBean.getRePassword();
	 String city = registerBean.getCity();
	 
	 Connection con = null;
	 PreparedStatement preparedStatement = null;
	 
	 try
	 {
	 con = DBConnection.createConnection();
	 String query = "insert into users(SlNo,name,email,password,phoneNo,address,pincode,rePassword,city) values (NULL,?,?,?,?,?,?,?,?)"; //Insert user details into the table 'USERS'
	 preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
	 preparedStatement.setString(1, name);
	 preparedStatement.setString(2, email);
	 preparedStatement.setString(3, phoneNo);
	 preparedStatement.setString(4, password);
	 preparedStatement.setString(5, address);
	 preparedStatement.setString(6, pincode);
	 preparedStatement.setString(7, rePassword);
	 preparedStatement.setString(8, city);
	 
	 int i= preparedStatement.executeUpdate();
	 
	 if (i!=0)  //Just to ensure data has been inserted into the database
	 return "SUCCESS"; 
	 }
	 catch(SQLException e)
	 {
	 e.printStackTrace();
	 }
	 
	 return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
	 }
}
