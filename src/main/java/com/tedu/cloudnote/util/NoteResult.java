package com.tedu.cloudnote.util;

import java.io.Serializable;

/**
 * 结果对象
 * 
 * @author 10174
 *
 */
public class NoteResult implements Serializable {
	private int status;// 0表示成功，其它表示失败
	private String msg;// 消息
	private Object data;// 返回的数据

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
