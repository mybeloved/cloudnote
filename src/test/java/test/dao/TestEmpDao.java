package test.dao;

import org.junit.Test;

import com.tedu.cloudnote.dao.EmpDao;
import com.tedu.cloudnote.entity.Emp;

import test.service.BaseTest;

public class TestEmpDao extends BaseTest {
	@Test
	public void test1() {
		EmpDao empDao = ac.getBean("empDao", EmpDao.class);
		Emp emp = new Emp();
		emp.setName("rose");
		empDao.save(emp);
		// ��ȡ��¼��noֵ
		int no = emp.getNo();
		System.out.println("����ֵ��" + no);
	}
}
