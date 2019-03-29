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
	 * �������src���ܴ���
	 * 
	 * @param src
	 *            �����ַ���
	 * @return ���ܺ���ַ������
	 * @throws Exception
	 */
	public static String md5(String src) throws Exception {
		// ���ַ�����Ϣ����MD5����
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] output = md.digest(src.getBytes());
		// ��MD5����������Base64ת���ַ���
		String s = Base64.encode(output);
		return s;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(md5("123456"));
		System.out.println(md5("1231232"));
		System.out.println(createId());
	}
}
