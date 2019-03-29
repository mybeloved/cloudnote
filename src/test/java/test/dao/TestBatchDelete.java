package test.dao;

import org.junit.Test;

import com.tedu.cloudnote.dao.NoteDao;

import test.service.BaseTest;

public class TestBatchDelete extends BaseTest {
	@Test
	public void test() {
		NoteDao noteDao = ac.getBean("noteDao", NoteDao.class);
		String[] ids = { "12119052-874c-4341-b85d-6529e171ed83", "12119052-874c-4341-b85d-6529e171ed83",
				"W12119052-874c-4341-b85d-6529e171ed83" };
		int rows = noteDao.batchDelete(ids);
		System.out.println("删除的记录行数：" + rows);
	}
}
