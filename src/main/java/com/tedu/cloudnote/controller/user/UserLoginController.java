package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller // ɨ�赽Spring����
@RequestMapping("/user")
public class UserLoginController {
	@Resource // ע��Service����
	private UserService userService;

	@RequestMapping("/login.do")
	@ResponseBody // JSON���
	public NoteResult execute(String name, String password) {
		// ����UserService�����¼
		return userService.checkLogin(name, password);
	}
}
