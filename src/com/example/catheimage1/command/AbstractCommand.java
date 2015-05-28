package com.example.catheimage1.command;

import java.util.LinkedHashMap;

/**
 * 任务基类
 * @Description 
 * @param <T>
 * @author Cheng Yong
 * @version 1.0 2012-8-15
 * @class AbstractCommand
 */
public abstract class AbstractCommand<T> {

	private LinkedHashMap<String, ICallBack<T>> callBacks = null;

	public AbstractCommand() {
		callBacks = new LinkedHashMap<String, ICallBack<T>>();
	}

	/**
	 * 增加回调类
	 */
	public ICallBack<T> addCallBack(String callBackName, ICallBack<T> callBack) {
		return callBacks.put(callBackName, callBack);
	}

	/**
	 * 删除回调类
	 */
	public ICallBack<T> removeCallBack(String callBackName) {
		return callBacks.remove(callBackName);
	}

	/**
	 * 根据名称,查询回调类
	 */
	public ICallBack<T> getCallBack(String callBackName) {
		return callBacks.get(callBackName);
	}

	/**
	 * 清空回调类
	 */
	public void clearAllCallBacks() {
		if (callBacks != null) {
			callBacks.clear();
//			callBacks=null;
		}
		
	}

	/**
	 * 是否有回调类存在
	 */
	public boolean haveCallBacks() {
		return callBacks != null && callBacks.size() > 0;
	}

	public boolean newThread() {
		return false;
	}

	public LinkedHashMap<String, ICallBack<T>> getCallBacks() {
		return callBacks;
	}

	public abstract T execute() throws Exception;
}
