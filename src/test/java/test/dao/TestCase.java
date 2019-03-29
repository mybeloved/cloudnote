package test.dao;

import org.junit.Test;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;

import test.service.BaseTest;

public class TestCase extends BaseTest {
	@Test
	public void testUser() {
		UserDao dao = ac.getBean("userDao", UserDao.class);
		User user = dao.findByName("demo");
		if (user == null) {
			System.out.println("用户不存在");
		} else {
			System.out.println(user.getCn_user_password());
		}
	}
}
