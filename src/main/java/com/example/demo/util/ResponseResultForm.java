package com.example.demo.util;

import java.io.Serializable;

public class ResponseResultForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	private boolean success;
	
	private String msg;
	
	private Object remark;


	public ResponseResultForm(int code, boolean success, String msg, Object remark) {
		// TODO Auto-generated constructor stub
		this.code=code;
		this.success=success;
		this.msg=msg;
		this.remark=remark;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRemark() {
		return remark;
	}

	public void setRemark(Object remark) {
		this.remark = remark;
	}

}
