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

@Service("userService") // ɨ��
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;// ע��

	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		// �ж��û���
		User user = userDao.findByName(name);
		if (user == null) {
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		// �ж�����
		// ���û���������ļ���
		String md5_pwd;
		try {
			md5_pwd = NoteUtil.md5(password);
			if (!user.getCn_user_password().equals(md5_pwd)) {
				result.setStatus(2);
				result.setMsg("�������");
				return result;
			}
		} catch (Exception e) {
			throw new NoteException("��������쳣", e);
		}
		// ��¼�ɹ�
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		user.setCn_user_password("");// ����������
		result.setData(user);// ����user��Ϣ
		return result;
	}

	public NoteResult addUser(String name, String nick, String password) {
		NoteResult result = new NoteResult();
		try {
			// ����Ƿ�����
			User has_user = userDao.findByName(name);
			if (has_user != null) {
				result.setStatus(1);
				result.setMsg("�û����ѱ�ռ��");
				return result;
			}
			// ִ���û�ע��
			User user = new User();
			user.setCn_user_id(NoteUtil.createId());// �����û�ID
			user.setCn_user_name(name);// �����û���
			user.setCn_user_desc(nick);// �����ǳ�
			user.setCn_user_password(NoteUtil.md5(password));// ��������
			userDao.save(user);
			// �������ؽ��
			result.setStatus(0);
			result.setMsg("ע��ɹ�");
			// ģ���쳣
			String s = null;
			s.length();// ��һ����ָ���쳣
			return result;
		} catch (Exception e) {
			throw new NoteException("�û�ע���쳣", e);
		}
	}

}
