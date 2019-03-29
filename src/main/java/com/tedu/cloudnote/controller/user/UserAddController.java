package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller // ɨ��
public class UserAddController {
	@Resource // ע��
	private UserService userService;

	@RequestMapping("/user/add.do")
	@ResponseBody
	public NoteResult execute(String name, String nick, String password) {
		return userService.addUser(name, nick, password);
	}
}
