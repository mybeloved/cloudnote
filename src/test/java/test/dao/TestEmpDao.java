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
		// 获取记录的no值
		int no = emp.getNo();
		System.out.println("主键值：" + no);
	}
}
