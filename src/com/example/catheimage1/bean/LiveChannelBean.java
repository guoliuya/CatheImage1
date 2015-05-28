package com.example.catheimage1.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.catheimage1.bitmap2.LogoImageLoader;
import com.example.catheimage1.excaption.CntvException;


public class LiveChannelBean extends BaseBean {
	private static final String NO_NEED_TO_SHOW_THE_ITEM = "0";
	
	private long collect_id;
	private String channelImg;
	private String bigImgUrl;
	private String imgUrl;
	private String title;
	private String initial;
	private String channelId;
	private String p2pUrl;
	private String shareUrl;
	private String liveUrl;
	private String autoImg;
	private String isShow;
	private String channelListUrl;
	private String channelCat;
	private String order;
	private String newChannelImg;
	
	
	private boolean deleteFlag = false;
	
	private boolean mIsAllDataReady = false;
	private String mPlayTitle;
	private int mProgressBarInt;
	
	
	public String getNewChannelImg() {
		return newChannelImg;
	}

	public void setNewChannelImg(String newChannelImg) {
		this.newChannelImg = newChannelImg;
	}

	public long getCollect_id() {
		return collect_id;
	}

	public void setCollect_id(long collect_id) {
		this.collect_id = collect_id;
	}

	public String getChannelCat() {
		return channelCat;
	}

	public void setChannelCat(String channelCat) {
		this.channelCat = channelCat;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public boolean ismIsAllDataReady() {
		return mIsAllDataReady;
	}

	public void setmIsAllDataReady(boolean mIsAllDataReady) {
		this.mIsAllDataReady = mIsAllDataReady;
	}

	public String getmPlayTitle() {
		return mPlayTitle;
	}

	public void setmPlayTitle(String mPlayTitle) {
		this.mPlayTitle = mPlayTitle;
	}

	public int getmProgressBarInt() {
		return mProgressBarInt;
	}

	public void setmProgressBarInt(int mProgressBarInt) {
		this.mProgressBarInt = mProgressBarInt;
	}

	public String getChannelListUrl() {
		return channelListUrl;
	}

	public void setChannelListUrl(String channelListUrl) {
		this.channelListUrl = channelListUrl;
	}

	public String toString() {
		return collect_id + ", " +channelImg + ", " + bigImgUrl + ", " + imgUrl + ", " + title + ", " + initial
				+ ", " + channelId + ", " + p2pUrl + ", " + shareUrl + ", " + liveUrl + ", " + autoImg + ", " + order;
	}
	
	public String getChannelImg() {
		return channelImg;
	}


	public void setChannelImg(String channelImg) {
		this.channelImg = channelImg;
	}


	public String getBigImgUrl() {
		return bigImgUrl;
	}


	public void setBigImgUrl(String bigImgUrl) {
		this.bigImgUrl = bigImgUrl;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getInitial() {
		return initial;
	}


	public void setInitial(String initial) {
		this.initial = initial;
	}


	public String getChannelId() {
		return channelId;
	}


	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}


	public String getP2pUrl() {
		return p2pUrl;
	}


	public void setP2pUrl(String p2pUrl) {
		this.p2pUrl = p2pUrl;
	}


	public String getShareUrl() {
		return shareUrl;
	}


	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}


	public String getLiveUrl() {
		return liveUrl;
	}


	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}


	public String getAutoImg() {
		return autoImg;
	}


	public void setAutoImg(String autoImg) {
		this.autoImg = autoImg;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	//TODO need to modified
	public static List<LiveChannelBean> convertFromJsonObject(String httpResult) 
			throws CntvException {
		Log.e("------------->convert", "--->"+httpResult);
		List<LiveChannelBean> jsonResult = new ArrayList<LiveChannelBean>();
		try {
			JSONObject json = new JSONObject(httpResult);
			if ("".equals(json)) {
				return null;
			}
			
			if (json.has("data") && json.get("data") != null && !"".equals(json.getString("data"))) {
				JSONObject jsonData = json.getJSONObject("data");
				
				if (jsonData.has("items") && jsonData.get("items") != null
						&& !"".equals(jsonData.getString("items"))) {
					JSONArray itemsJsonArray = jsonData.getJSONArray("items");
					if (itemsJsonArray.length() > 0) { //no need to judge categoryList == null
						for (int index = 0, length = itemsJsonArray.length(); index < length; index++) {
							JSONObject jsonObject = itemsJsonArray.getJSONObject(index);
							LiveChannelBean liveCctvChannelBean = new LiveChannelBean();
							
							/**
							 * if isShow is "", both showing in PC and Mobile;
							 * if isShow is "0", no need to show the item, 
							 * if to be "1", need to show the item
							 */
							if (jsonObject.has("isShow")) {
								if (NO_NEED_TO_SHOW_THE_ITEM.equals(jsonObject.getString("isShow"))) {
									continue;
								} else {
									liveCctvChannelBean.setIsShow(jsonObject.getString("isShow"));
								}
							}
							
							if (jsonObject.has("channelImg")) {
								liveCctvChannelBean.setChannelImg(jsonObject.getString("channelImg"));
							}
							if (jsonObject.has("bigImgUrl")) {
								liveCctvChannelBean.setBigImgUrl(jsonObject.getString("bigImgUrl"));
							}
							if (jsonObject.has("imgUrl")) {
								liveCctvChannelBean.setImgUrl(jsonObject.getString("imgUrl"));
							}
							if (jsonObject.has("title")) {
								liveCctvChannelBean.setTitle(jsonObject.getString("title"));
							}
							if (jsonObject.has("initial")) {
								liveCctvChannelBean.setInitial(jsonObject.getString("initial"));
							}
							if (jsonObject.has("channelId")) {
								liveCctvChannelBean.setChannelId(jsonObject.getString("channelId"));
							}
							if (jsonObject.has("p2pUrl")) {
								liveCctvChannelBean.setP2pUrl(jsonObject.getString("p2pUrl"));
							}
							if (jsonObject.has("shareUrl")) {
								liveCctvChannelBean.setShareUrl(jsonObject.getString("shareUrl"));
							}
							if (jsonObject.has("liveUrl")) {
								liveCctvChannelBean.setLiveUrl(jsonObject.getString("liveUrl"));
							}
							if (jsonObject.has("autoImg")) {
								liveCctvChannelBean.setAutoImg(jsonObject.getString("autoImg"));
							}
							if (jsonObject.has("order")) {
								liveCctvChannelBean.setOrder(jsonObject.getString("order"));
							}
							if (jsonObject.has("newChannelImg")) {
								liveCctvChannelBean.setNewChannelImg(jsonObject.getString("newChannelImg"));
							}
							jsonResult.add(liveCctvChannelBean);
						}
					}
				}
			} //end of if json object has data
			return jsonResult;
		} catch (JSONException e) {
			throw new CntvException("接口数据转换失败", e);
		}
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
