package com.tedu.cloudnote.aop;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ���棺���쳣��Ϣд���ļ�
 * 
 * @author 10174
 *
 */
@Component
@Aspect
public class ExceptionBean {
	// ָ���쳣֪ͨ���������ʽ
	@AfterThrowing(throwing = "e", pointcut = "within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e) {
		// e����Ŀ�귽���׳����쳣����
		try {
			// ��e������Ϣд��note_error.log�ļ�
			FileWriter fw = new FileWriter("D:\\workspaces\\Workspace\\cloudnote\\note_error.log", true);
			PrintWriter pw = new PrintWriter(fw);
			// ��ȡ�쳣����ʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			// ��ӡ�쳣ͷ������
			pw.println("****************************************************************");
			pw.println("*����ʱ�䣺" + time);
			pw.println("*�쳣���ͣ�" + e);
			pw.println("***************************�쳣����****************************");
			// ���쳣ջ��Ϣ��ӡ
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
