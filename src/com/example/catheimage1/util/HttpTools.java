package com.example.catheimage1.util;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.catheimage1.excaption.CntvException;

import android.util.Log;

/**
 * <hr/>
 * 
 * @author www.TheWk.cn.vc
 */
public abstract class HttpTools {

	static String TAG = "HttpTools";

	private static final HttpClient client;
	static {
		HttpParams params = new BasicHttpParams();
		// 设置请求超时 10秒
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		// 设置响应超时 10秒
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		client = new DefaultHttpClient(params);
	}

	private static final String Encoding = "UTF-8";

	private HttpTools() throws IllegalAccessException {
		throw new IllegalAccessException("工具类无法实例化!");
	}

	public static String post(String path, List<NameValuePair> params)
			throws CntvException {
		try {
			String result = null;
			HttpPost request = new HttpPost(path);
			request.setEntity(new UrlEncodedFormEntity(params, Encoding));
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), Encoding);
			}
			return result;
		} catch (IOException e) {
			throw new CntvException("服务器连接失败或超时！", e);
		}

	}

	public static String post(String path, Map<String, String> params)
			throws CntvException {
		List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				BasicNameValuePair param = new BasicNameValuePair(
						entry.getKey(), entry.getValue());
				paramPairs.add(param);
			}
		}
		return post(path, paramPairs);

	}

	public static String post(String path) throws CntvException {
		try {
			path=path.replaceAll(" ", "");
			String result = null;
//			Logs.e("jsx=path=", path+"");
			HttpPost request = new HttpPost(path);
			HttpResponse response = client.execute(request);
//			Logs.e("jsx=getStatusCode()==", response.getStatusLine().getStatusCode()+"");
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), Encoding);
//				Logs.e("jsx=httptools=result==", result+"");
			}
			return result;
		} catch (Exception e) {
			throw new CntvException("服务器连接失败或超时！", e);
		}
	}
	
	
	public static String get(String path) throws CntvException {
		try {
			path=path.replaceAll(" ", "");
			String result = null;
			HttpGet request = new HttpGet(path);
			HttpResponse response = client.execute(request);
//			Logs.e("getStatusCode()==", response.getStatusLine().getStatusCode()+"");
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), Encoding);
			}
			return result;
		} catch (Exception e) {
			throw new CntvException("服务器连接失败或超时！", e);
		}
	}
	
	
	public static long getTime(String path) throws CntvException, ParseException {
		try {
			long result = 0;
			String value = null;
			HttpPost request = new HttpPost(path);
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				Header[] headers=response.getHeaders("date");
				for (Header header : headers) {
						
						//Date date=sdf.parse("Mon, 15 Apr 2013 01:18:00 GMT");
						value=header.getValue();
						
				}
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.UK);
				Date date=sdf.parse(value);
				result=date.getTime()/1000;
			}
			return result;
		} catch (IOException e) {
			throw new CntvException("服务器连接失败或超时！", e);
		}
	}

	/**
	 * 提交返回成功与否结果公共方法
	 * 
	 * @Description
	 * @param httpResult
	 * @return
	 * @throws CntvException
	 * @author 刘国山 liugs@tydic.com
	 * @version 1.0 2012-8-15
	 */
	public static JsonResult convertSubmitFromJsonObject(String httpResult)
			throws CntvException {
		try {
			JSONObject jsonObject = new JSONObject(httpResult);
			if (jsonObject == null) {
				return null;
			}
			JsonResult jsonResult = new JsonResult();
			if (jsonObject.has(JsonResult.Key_Status)) {
				jsonResult.setStatus(jsonObject.getInt(JsonResult.Key_Status));
			}
			if (jsonObject.has(JsonResult.Key_Message)) {
				jsonResult.setMessage(jsonObject
						.getString(JsonResult.Key_Message));
			}
			return jsonResult;
		} catch (JSONException e) {
			throw new CntvException("接口数据转换失败", e);
		}
	}
	
	public static String getVarientPlaylist(String mainUrl, String subUrl)
	{
		if(mainUrl == null || "".equals((mainUrl = mainUrl.trim()))) {
			return null;
		}
		
		if (!mainUrl.startsWith("http://") && !mainUrl.startsWith("https://")) {
			return null;
		}
		
		String protocol = mainUrl.substring(0, mainUrl.indexOf("/") - 1);
		String strtoken = mainUrl.substring(mainUrl.indexOf("/") + 2); 
		String domainName = "";
		
		int domainIdx = strtoken.indexOf("/");
		if(domainIdx != -1) {
		
			domainName = strtoken.substring(0, domainIdx);
		}
		else
			return null;
			
		
		
		String realUrl = "";
		if (subUrl.startsWith("http://") || subUrl.startsWith("https://"))
		{
			//contain whole real url
			realUrl = subUrl;
		}
		else if (subUrl.startsWith("/"))
		{
			//domain + subUrl;
			String domainUrl= protocol + "://" + domainName;
			realUrl = domainUrl + subUrl;
		}
		else 
		{   
			//mainUrl + subUrl;
			int index = mainUrl.lastIndexOf("/");
			realUrl = mainUrl.substring(0, index) + "/" + subUrl;
		}
		return realUrl;
	}
}
