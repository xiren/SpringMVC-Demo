package com.example.myspring.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;

public class DESDecrypt {

	private static final String PRIVATE_KEY = "ABC";

	private static byte[] BASE64Decode(String str) throws Exception {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		return base64Decoder.decodeBuffer(str);
	}

	private static byte[] decryptByte(byte[] bytes) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		Key key = generatorKey(PRIVATE_KEY);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(bytes);
	}

	private static Key generatorKey(String key) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(new SecureRandom(key.getBytes()));
		return keyGenerator.generateKey();
	}

	public static String decrypt(String str) throws Exception {
		byte[] bytes = BASE64Decode(str);
		String original = new String(decryptByte(bytes),"UTF-8");
		return original;
	}

	public static void main(String[] args) throws Exception {
		String str = "kPDT4N9RmpA=";
		System.out.println(DESDecrypt.decrypt(str));
	}
}
