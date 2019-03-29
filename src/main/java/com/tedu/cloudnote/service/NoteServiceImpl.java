package com.tedu.cloudnote.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;

	public NoteResult loadBookNotes(String bookId) {
		// ���ʼǱ�ID��ѯ�ʼ���Ϣ
		List<Map> list = noteDao.findByBookId(bookId);
		// �������ؽ��
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("��ѯ���");
		result.setData(list);
		return result;
	}

	public NoteResult loadNote(String noteId) {
		// ���ʼ�ID��ѯ�ʼ���Ϣ
		Note note = noteDao.findById(noteId);
		// �������ؽ��
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("��ѯ���");
		result.setData(note);
		return result;
	}

	public NoteResult updateNote(String noteId, String title, String body) {
		Note note = new Note();
		note.setCn_note_id(noteId);// ���ñʼ�ID
		note.setCn_note_title(title);// ���ñ���
		note.setCn_note_body(body);// ��������
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);// �����޸�ʱ��
		// int rows = noteDao.updateNote(note);// ����
		int rows = noteDao.dynamicUpdate(note);// ����
		// �������ؽ��
		NoteResult result = new NoteResult();
		if (rows == 1) {// �ɹ�
			result.setStatus(0);
			result.setMsg("����ʼǳɹ�");
		} else {// ʧ��
			result.setStatus(1);
			result.setMsg("����ʼ�ʧ��");
		}
		return result;
	}

	public NoteResult addNote(String userId, String noteTitle, String bookId) {
		// ����note��������
		Note note = new Note();
		note.setCn_user_id(userId);// �����û�ID
		note.setCn_note_title(noteTitle);// ���ñʼ�����
		note.setCn_notebook_id(bookId);// ���ñʼǱ�ID
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);// �ʼ�ID
		Long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);// ����ʱ��
		note.setCn_note_last_modify_time(time);// ����޸�ʱ��
		noteDao.save(note);// ����ʼ�
		// �������ؽ��
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(noteId);// ���رʼ�ID
		return result;
	}

	public NoteResult deleteNote(String noteId) {
		// int rows = noteDao.updateStatus(noteId);
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		int rows = noteDao.dynamicUpdate(note);
		// �������ؽ��
		NoteResult result = new NoteResult();
		if (rows >= 1) {// �ɹ�
			result.setStatus(0);
			result.setMsg("ɾ���ʼǳɹ�");
		} else {
			result.setStatus(1);
			result.setMsg("ɾ���ʼ�ʧ��");
		}
		return result;
	}

	public NoteResult moveNote(String noteId, String bookId) {
		Note note = new Note();
		note.setCn_note_id(noteId);// ���ñʼ�ID
		note.setCn_notebook_id(bookId);// ���ñʼǱ�ID
		// int rows = noteDao.updateBookId(note);// ����
		int rows = noteDao.dynamicUpdate(note);// ����
		// �������ؽ��
		NoteResult result = new NoteResult();
		if (rows >= 1) {// �ɹ�
			result.setStatus(0);
			result.setMsg("ת�Ʊʼǳɹ�");
		} else {
			result.setStatus(1);
			result.setMsg("ת�Ʊʼ�ʧ��");
		}
		return result;
	}

	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		// ��ȡ�ʼ���Ϣ
		Note note = noteDao.findById(noteId);
		// ���cn_note_type_id�Ƿ�Ϊ����״̬������ѷ���ִ�������߼�
		if ("2".equals(note.getCn_note_type_id())) {
			result.setStatus(1);
			result.setMsg("�ñʼ��ѷ����");
			return result;
		}
		// ���±ʼǵ�cn_note_type_idΪ����״̬'2'
		// noteDao.updateTypeId(noteId);
		Note note1 = new Note();
		note1.setCn_note_id(noteId);
		note1.setCn_note_type_id("2");
		noteDao.dynamicUpdate(note1);
		// ��ӵ������
		Share share = new Share();
		share.setCn_note_id(noteId);// �ʼ�ID
		share.setCn_share_id(NoteUtil.createId());// ����ID
		share.setCn_share_title(note.getCn_note_title());// �������
		share.setCn_share_body(note.getCn_note_body());// ��������
		shareDao.save(share);
		// �������ؽ��
		result.setStatus(0);
		result.setMsg("����ʼǳɹ�");
		return result;
	}

	public NoteResult searchShareNote(String keyword, int page) {
		// �����ѯ����ֵ
		String title = "%";
		if (keyword != null && !"".equals(keyword)) {
			title += keyword + "%";
		}
		// ����ץȡ���
		if (page < 1) {
			page = 1;
		}
		int begin = (page - 1) * 5;
		// ��װ��Map����
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", begin);// ��Ӧ#{begin}
		params.put("keyword", title);// ��Ӧ#{title}
		List<Share> list = shareDao.findLikeTitle(params);
		// ��װNoteResult���
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�������");
		result.setData(list);
		return result;
	}

	public NoteResult loadShareNote(String shareId) {
		Share share = shareDao.findById(shareId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("���رʼǳɹ�");
		result.setData(share);
		return result;
	}

	public NoteResult searchNotes(String title, String status, String beginStr, String endStr) {
		// ������ѯ����
		Map<String, Object> params = new HashMap<String, Object>();
		// ����
		if (title != null && !"".equals(title)) {
			// ��ӦSQL�е�#{title}
			params.put("title", "%" + title + "%");
		}
		// ״̬���������ȫ��ѡ��"0"
		if (!"0".equals(status)) {
			// ��ӦSQL�е�#{status}
			params.put("status", status);
		}
		// ��ʼ����
		if (beginStr != null && !"".equals(beginStr)) {
			Date beginDate = Date.valueOf(beginStr);
			// ��ӦSQL�е�#{begin}
			params.put("begin", beginDate.getTime());
		}
		System.out.println(beginStr);
		// ��������
		if (endStr != null && !"".equals(endStr)) {
			Date endDate = Date.valueOf(endStr);
			// ��ӦSQL�е�#{end}
			params.put("end", endDate.getTime());
		}
		System.out.println(endStr);
		// ���ݲ�����ѯ�ʼ���Ϣ
		List<Note> list = noteDao.findNotes(params);
		// ��װNoteResult���
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�������");
		result.setData(list);
		return result;
	}

}
