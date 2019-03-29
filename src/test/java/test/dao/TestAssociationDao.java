package test.dao;

import java.util.List;

import org.junit.Test;

import com.tedu.cloudnote.dao.AssociationDao;
import com.tedu.cloudnote.entity.Book;

import test.service.BaseTest;

public class TestAssociationDao extends BaseTest {
	@Test
	public void test2() {
		AssociationDao associationDao = ac.getBean("associationDao", AssociationDao.class);
		List<Book> list = associationDao.findAllBook();
		for (Book book : list) {
			if (book.getUser() != null) {
				System.out.println(book.getCn_notebook_name() + " " + book.getCn_notebook_createtime() + " "
						+ book.getUser().getCn_user_name());
			}
		}
	}

	@Test
	public void test1() {
		AssociationDao associationDao = ac.getBean("associationDao", AssociationDao.class);
		Book book = associationDao.findById("12119052-874c-4341-b85d-6529e171ed83");
		System.out.println("�ʼǱ�����" + book.getCn_notebook_name());
		System.out.println("����ʱ�䣺" + book.getCn_notebook_createtime());
		System.out.println("�����û�����" + book.getUser().getCn_user_name());
	}
}
