package com.example.catheimage1.command;

/**
 * 回调接口
 * <hr/>
 * 
 * @author www.TheWk.cn.vc
 */
public interface ICallBack<T> {

	/**
	 * 回调方法
	 */
	public void callBack(AbstractCommand<T> command, T result, Exception ex);
}
