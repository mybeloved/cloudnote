package com.tedu.cloudnote.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteException;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("userService") // 扫描
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;// 注入

	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		// 判断用户名
		User user = userDao.findByName(name);
		if (user == null) {
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		// 判断密码
		// 将用户输入的明文加密
		String md5_pwd;
		try {
			md5_pwd = NoteUtil.md5(password);
			if (!user.getCn_user_password().equals(md5_pwd)) {
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
		// 登录成功
		result.setStatus(0);
		result.setMsg("登录成功");
		user.setCn_user_password("");// 把密码屏蔽
		result.setData(user);// 返回user信息
		return result;
	}

	public NoteResult addUser(String name, String nick, String password) {
		NoteResult result = new NoteResult();
		try {
			// 检测是否重名
			User has_user = userDao.findByName(name);
			if (has_user != null) {
				result.setStatus(1);
				result.setMsg("用户名已被占用");
				return result;
			}
			// 执行用户注册
			User user = new User();
			user.setCn_user_id(NoteUtil.createId());// 设置用户ID
			user.setCn_user_name(name);// 设置用户名
			user.setCn_user_desc(nick);// 设置昵称
			user.setCn_user_password(NoteUtil.md5(password));// 设置密码
			userDao.save(user);
			// 创建返回结果
			result.setStatus(0);
			result.setMsg("注册成功");
			// 模拟异常
			String s = null;
			s.length();// 抛一个空指针异常
			return result;
		} catch (Exception e) {
			throw new NoteException("用户注册异常", e);
		}
	}

}
