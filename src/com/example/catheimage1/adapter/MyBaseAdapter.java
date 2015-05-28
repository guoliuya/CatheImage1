package com.example.catheimage1.adapter;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.net.ConnectivityManager;
import android.widget.BaseAdapter;
import android.widget.Toast;

/**
 * 数据适配器基础类
 * 
 * @Description
 * @author Cheng Yong
 * @version 1.0 2012-7-15
 * @class MyBaseAdapter
 */
public abstract class MyBaseAdapter extends BaseAdapter {
	@SuppressWarnings("rawtypes")
	protected List items;
	protected Activity activity;

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
	    if (observer != null) {
	        super.unregisterDataSetObserver(observer);
	    }
	}
	
	@Override
	public int getCount() {
		if (items == null) {
			return 0;
		} else {
			return items.size();
		}
	}

	@Override
	public Object getItem(int position) {
		if (items == null) {
			return null;
		} else {
			return items.get(position);
		}
	}

	/**
	 * 覆盖填充
	 * 
	 * @Description
	 * @param items
	 * @author Cheng Yong
	 * @version 1.0 2012-7-10
	 */
	public void setItems(List items) {
		this.items = items;
	}

	/**
	 * 添加集合
	 * 
	 * @Description
	 * @param items
	 * @author Cheng Yong
	 * @version 1.0 2012-7-10
	 */
	public void addItems(List items) {
		this.items.addAll(items);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	/**
	 * 获取集合
	 * 
	 * @Description
	 * @return
	 * @author Cheng Yong
	 * @version 1.0 2012-7-10
	 */
	public List getItems() {
		return items;
	}

	/**
	 * changeData()
	 * 
	 * @description 当数据发送改变时，调用此方法，更新界面
	 * @param null
	 * @return void
	 * @Exception 异常对象
	 * @author Mr Cheng
	 * @date 2012-7-23
	 */
	public void changeData(List items)
	{
		if (items != null)
		{
			this.items = items;
			this.notifyDataSetChanged();
		}
	}

	/**
	 * 检测网络是否可用
	 * 
	 * @Description
	 * @return
	 * @author Cheng Yong
	 * @version 1.0 2012-7-25
	 */
	protected boolean checkNetWork()
	{
		boolean newWorkOK = false;
		ConnectivityManager connectManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectManager.getActiveNetworkInfo() != null)
		{
			newWorkOK = true;
		}
		return newWorkOK;
	}

	/**
	 * 长提示
	 * 
	 * @Description
	 * @param activity
	 *            activity实例
	 * @param resId
	 *            文本资源ID
	 * @author Cheng Yong
	 * @version 1.0 2012-7-19
	 */
	protected void toastLong(int resId)
	{
		Toast.makeText(activity, activity.getResources().getString(resId), Toast.LENGTH_LONG).show();
	}

	/**
	 * 创建进度条
	 * 
	 * @Description
	 * @param activity
	 * @param resId
	 * @return
	 * @author Cheng Yong
	 * @version 1.0 2012-7-19
	 */
	public ProgressDialog createProgress(int resId)
	{
		ProgressDialog dialog = new ProgressDialog(activity);
		dialog.setMessage(activity.getResources().getString(resId));
		dialog.setIndeterminate(false);
		dialog.setCancelable(true);
		return dialog;
	}

	/**
	 * 长提示
	 * 
	 * @Description
	 * @param context
	 * @param msg
	 * @author Cheng Yong
	 * @version 1.0 2012-7-18
	 */
	protected void toastLong(String msg)
	{
		Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
	}

}
