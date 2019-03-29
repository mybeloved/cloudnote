package test.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;

public class TestUserDao {
	@Test
	public void test1() throws Exception {
		// ����Spring����
		String[] conf = { "conf/spring-mvc.xml", "conf/spring-mybatis.xml" };
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		// ��ȡDataSource
		DataSource ds = ac.getBean("dbcp", DataSource.class);
		Connection conn = ds.getConnection();
		System.out.println(conn);
		conn.close();
		// ��ȡSqlSessionFactory
		SqlSessionFactory factory = ac.getBean("ssfb", SqlSessionFactory.class);
		System.out.println(factory.openSession());
		// ��ȡUserDAO
		UserDao dao = ac.getBean("userDao", UserDao.class);
		User user = dao.findByName("demo");
		if (user == null) {
			System.out.println("�û�������");
		} else {
			System.out.println(user.getCn_user_password());
		}
	}

}
