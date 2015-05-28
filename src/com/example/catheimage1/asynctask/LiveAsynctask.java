package com.example.catheimage1.asynctask;

import java.util.List;

import com.example.catheimage1.bean.LiveChannelBean;
import com.example.catheimage1.excaption.CntvException;
import com.example.catheimage1.util.HttpTools;

import android.R.integer;
import android.os.AsyncTask;
import android.util.Log;

public class LiveAsynctask extends AsyncTask<String, integer, String> {
private InterfaceListener mListener;
public LiveAsynctask(InterfaceListener listener){
	mListener=listener;
}
	@Override
	protected String doInBackground(String... url) {
		// TODO Auto-generated method stub
		try {
			String httpResult = HttpTools.post("http://serv.cbox.cntv.cn/json/zhibo/yangshipindao/ysmc/index.json");
			//List<LiveChannelBean> list = LiveChannelBean.convertFromJsonObject(httpResult);
			return httpResult;
		} catch (CntvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		
		try {
			Log.e("-------------------->onpost", "--->"+result);
			List<LiveChannelBean> list = LiveChannelBean.convertFromJsonObject(result);
			if (null!=mListener) {
				
				mListener.onGetData(list);
			}
		} catch (CntvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
