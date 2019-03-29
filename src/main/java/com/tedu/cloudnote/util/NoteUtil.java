package com.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.util.UUID;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class NoteUtil {
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 将传入的src加密处理
	 * 
	 * @param src
	 *            明文字符串
	 * @return 加密后的字符串结果
	 * @throws Exception
	 */
	public static String md5(String src) throws Exception {
		// 将字符串信息采用MD5处理
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] output = md.digest(src.getBytes());
		// 将MD5处理结果利用Base64转成字符串
		String s = Base64.encode(output);
		return s;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(md5("123456"));
		System.out.println(md5("1231232"));
		System.out.println(createId());
	}
}
