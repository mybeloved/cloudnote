package com.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;

	public NoteResult loadUserBooks(String userId) {
		// ���û�ID��ѯ�ʼǱ���Ϣ
		List<Book> list = bookDao.findByUserId(userId);
		// �������ؽ��
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("��ѯ���");
		result.setData(list);
		return result;
	}

	public NoteResult addBook(String userId, String name) {
		Book book = new Book();
		book.setCn_user_id(userId);// �����û�ID
		book.setCn_notebook_name(name);// ���ñʼǱ�����
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);// ���ñʼǱ�ID
		Timestamp time = new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);// ���ñʼǱ�����ʱ��
		book.setCn_notebook_type_id("5");// ���ñʼǱ�����
		bookDao.save(book);// ����ʼǱ�
		// �������ؽ��
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�����ʼǱ��ɹ�");
		result.setData(book);// ������ӵıʼǱ���Ϣ
		return result;
	}

}
