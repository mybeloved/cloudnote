package test.dao;

import java.util.List;

import org.junit.Test;

import com.tedu.cloudnote.dao.CollectionDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.User;

import test.service.BaseTest;

public class TestCollectionDao extends BaseTest {

	@Test
	public void test2() {
		CollectionDao collectionDao = ac.getBean("collectionDao", CollectionDao.class);
		List<User> list = collectionDao.findAllUser();
		for (User user : list) {
			// ���û����ʼǱ�ƴ��һ���ַ���
			String bookNames = "";
			for (Book book : user.getBooks()) {
				bookNames += book.getCn_notebook_name();
				bookNames += ",";
			}
			// ��ӡ�û������ʼǱ��������ʼǱ���
			System.out.println(user.getCn_user_name() + " " + user.getBooks().size() + " " + bookNames);
		}
	}

	@Test
	public void test1() {
		CollectionDao collectionDao = ac.getBean("collectionDao", CollectionDao.class);
		User user = collectionDao.findById("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		System.out.println("�û���" + user.getCn_user_name());
		System.out.println("ӵ�еıʼǱ�");
		for (Book book : user.getBooks()) {
			System.out.println("==" + book.getCn_notebook_name());
		}
	}
}
