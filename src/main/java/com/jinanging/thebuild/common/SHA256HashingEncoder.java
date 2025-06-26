package com.jinanging.thebuild.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashingEncoder {
	
	public static String encode(String message) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("sha256");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			String result = "";
			for(int i = 0; i < digest.length; i++) {
				// byte 연산
				result += Integer.toHexString(digest[i] & 0xff);
				
			}
			
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
