package com.tedu.cloudnote.util;

import java.io.Serializable;

/**
 * �������
 * 
 * @author 10174
 *
 */
public class NoteResult implements Serializable {
	private int status;// 0��ʾ�ɹ���������ʾʧ��
	private String msg;// ��Ϣ
	private Object data;// ���ص�����

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

}
