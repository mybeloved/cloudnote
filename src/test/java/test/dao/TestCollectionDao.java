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
			// 将用户名笔记本拼成一个字符串
			String bookNames = "";
			for (Book book : user.getBooks()) {
				bookNames += book.getCn_notebook_name();
				bookNames += ",";
			}
			// 打印用户名，笔记本数量，笔记本名
			System.out.println(user.getCn_user_name() + " " + user.getBooks().size() + " " + bookNames);
		}
	}

	@Test
	public void test1() {
		CollectionDao collectionDao = ac.getBean("collectionDao", CollectionDao.class);
		User user = collectionDao.findById("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		System.out.println("用户名" + user.getCn_user_name());
		System.out.println("拥有的笔记本");
		for (Book book : user.getBooks()) {
			System.out.println("==" + book.getCn_notebook_name());
		}
	}
}
