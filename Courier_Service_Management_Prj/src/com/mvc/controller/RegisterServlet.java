package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mvc.bean.RegisterBean;
import com.mvc.dao.RegisterDao;
import com.core.Mailer.Generate_OTP;
import com.core.Mailer.JavaMail;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNo = request.getParameter("phoneNo");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String re_password = request.getParameter("re_password");
		String pincode = request.getParameter("pincode");
		
		RegisterBean registerBean = new RegisterBean();
		
		registerBean.setName(name);
		registerBean.setEmail(email);
		registerBean.setPhoneNo(phoneNo);
		registerBean.setPassword(password); 
		registerBean.setAddress(address);
		registerBean.setCity(city);
		registerBean.setRePassword(re_password);
		registerBean.setPincode(pincode);
		
		String otp=new String(Generate_OTP.OTP());
		try {
			JavaMail.mail(email, otp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String userRegistered = RegisterDao.registerUser(registerBean);
		 
		if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
		{
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
		else   //On Failure, display a meaningful message to the User.
		{
			request.setAttribute("errMessage", userRegistered);
		 	request.getRequestDispatcher("/Register.jsp").forward(request, response);
		}
	}

}
