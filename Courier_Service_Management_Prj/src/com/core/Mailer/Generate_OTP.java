package com.core.Mailer;

import java.util.*;

public class Generate_OTP {
	public static char[] OTP() {
		String numbers="0123456789";
		Random randm = new Random();
		char[] otp= new char[4];
		for(int i=0;i<4;i++) {
			otp[i] = numbers.charAt(randm.nextInt(numbers.length()));
		}
		return otp;
	}
}
