package com.tedu.cloudnote.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * ���棺��װ��׮�����߼�
 * 
 * @author 10174
 *
 */
@Component // ɨ��
@Aspect // ָ��Ϊ����
public class LoggerBean {
	// ָ��֪ͨ����/�������ʽ
	@Before("within(com.tedu.cloudnote.controller..*)")
	public void loggerController() {
		System.out.println("����Controller�������");
	}
}
