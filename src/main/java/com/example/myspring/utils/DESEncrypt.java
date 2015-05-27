package com.example.myspring.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Encoder;

public class DESEncrypt {
	private final static String PRIVATE_KEY = "ABC";

	private static byte[] encryptByte(byte[] bytes) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		Key key = generateKey(PRIVATE_KEY);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(bytes);
	}

	private static Key generateKey(String key) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(new SecureRandom(key.getBytes()));
		return keyGenerator.generateKey();
	}

	private static String BASE64Encode(byte[] bytes) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeBuffer(bytes);
	}

	public static String encrypt(String str) throws Exception {
		byte[] bytes = encryptByte(str.getBytes("UTF-8"));
		return BASE64Encode(bytes);
	}

	public static void main(String[] args) throws Exception {
		String str = "MSJIOS";
		System.out.println(DESEncrypt.encrypt(str));
	}
}
