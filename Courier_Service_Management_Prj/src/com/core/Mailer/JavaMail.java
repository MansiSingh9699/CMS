package com.core.Mailer;

import com.core.Mailer.JavaMailUtil;

public class JavaMail {

	public static void mail(String email,String otp) throws Exception {
		JavaMailUtil.sendMail(email,otp);

	}

}
