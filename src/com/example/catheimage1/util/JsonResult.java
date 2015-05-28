package com.example.catheimage1.util;

import java.io.Serializable;

/**
* @ClassName: JsonResult
* @Description: TODO 结果解析
* @author Cheng Yong
* @date  2013-4-11
*
* @param <T>
*/
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = -8029713524697047468L;

	public static final String Key_Status = "status";
	public static final String Key_Message = "message";
	public static final String Key_Result = "result";
	public static final String Key_Entities = "entities";
	public static final String Key_Page = "page";
	
	/**
	 * 操作执行成功
	 */
	public static final int Status_Success = 1;
	public static final int Status_Error = -1;
	/**
	 * 没有登录
	 */
	public static final int Status_Not_Login = -10;

	private int status = Status_Success;
	private T result;
	private String message;
	private PageVo pageVo;
	

	public void setStatusSuccess() {
		this.status = Status_Success;
	}

	public void setStatusNotLogin() {
		this.status = Status_Not_Login;
	}

	public void setStatusError() {
		this.status = Status_Error;
	}

	public JsonResult() {
		super();
	}

	public JsonResult(T result) {
		this.result = result;
	}

	public int getStatus() {
		return this.status;
	}

	public T getResult() {
		return this.result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
