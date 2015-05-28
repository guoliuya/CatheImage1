package com.example.catheimage1.bean;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.catheimage1.excaption.CntvException;
import com.example.catheimage1.util.JsonUtils;

import android.util.Log;

public class LanmuDetailBean extends BaseBean {
	public static class LanmuDetailHeadPartBean {
		private String title;
		private String brief;
		private String imgUrl;
		private String bigImgUrl;
		private String vsetType;
		private String vsetId;
		private String shareUrl;
		private String isShow;
		private String columnSo;
		private String vsetPageid;
		private String order;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBrief() {
			return brief;
		}
		public void setBrief(String brief) {
			this.brief = brief;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getBigImgUrl() {
			return bigImgUrl;
		}
		public void setBigImgUrl(String bigImgUrl) {
			this.bigImgUrl = bigImgUrl;
		}
		public String getVsetType() {
			return vsetType;
		}
		public void setVsetType(String vsetType) {
			this.vsetType = vsetType;
		}
		public String getVsetId() {
			return vsetId;
		}
		public void setVsetId(String vsetId) {
			this.vsetId = vsetId;
		}
		public String getShareUrl() {
			return shareUrl;
		}
		public void setShareUrl(String shareUrl) {
			this.shareUrl = shareUrl;
		}
		public String getIsShow() {
			return isShow;
		}
		public void setIsShow(String isShow) {
			this.isShow = isShow;
		}
		public String getColumnSo() {
			return columnSo;
		}
		public void setColumnSo(String columnSo) {
			this.columnSo = columnSo;
		}
		public String getVsetPageid() {
			return vsetPageid;
		}
		public void setVsetPageid(String vsetPageid) {
			this.vsetPageid = vsetPageid;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
	}
	
	public static class LanmuDetailNormalPartBean {
		private boolean mIsAllDataReady = false;
		
		private String title;
		private String lastVideo;
		private String brief;
		private String imgUrl;
		private String bigImgUrl;
		private String vsetType;
		private String vsetId;
		private String shareUrl;
		private String isShow;
		private String columnSo;
		private String vsetPageid;
		private String order;
		
		private String mModifiedBrief;
		private String mModiFiedImgUrl;
		
		public String getmModifiedBrief() {
			return mModifiedBrief;
		}

		public void setmModifiedBrief(String modifiedBrief) {
			this.mModifiedBrief = modifiedBrief;
		}

		public String getmModiFiedImgUrl() {
			return mModiFiedImgUrl;
		}

		public void setmModiFiedImgUrl(String mModiFiedImgUrl) {
			this.mModiFiedImgUrl = mModiFiedImgUrl;
		}

		public boolean ismIsAllDataReady() {
			return mIsAllDataReady;
		}

		public void setmIsAllDataReady(boolean mIsAllDataReady) {
			this.mIsAllDataReady = mIsAllDataReady;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getLastVideo() {
			return lastVideo;
		}
		public void setLastVideo(String lastVideo) {
			this.lastVideo = lastVideo;
		}
		public String getBrief() {
			return brief;
		}
		public void setBrief(String brief) {
			this.brief = brief;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getBigImgUrl() {
			return bigImgUrl;
		}
		public void setBigImgUrl(String bigImgUrl) {
			this.bigImgUrl = bigImgUrl;
		}
		public String getVsetType() {
			return vsetType;
		}
		public void setVsetType(String vsetType) {
			this.vsetType = vsetType;
		}
		public String getVsetId() {
			return vsetId;
		}
		public void setVsetId(String vsetId) {
			this.vsetId = vsetId;
		}
		public String getShareUrl() {
			return shareUrl;
		}
		public void setShareUrl(String shareUrl) {
			this.shareUrl = shareUrl;
		}
		public String getIsShow() {
			return isShow;
		}
		public void setIsShow(String isShow) {
			this.isShow = isShow;
		}
		public String getColumnSo() {
			return columnSo;
		}
		public void setColumnSo(String columnSo) {
			this.columnSo = columnSo;
		}
		public String getVsetPageid() {
			return vsetPageid;
		}
		public void setVsetPageid(String vsetPageid) {
			this.vsetPageid = vsetPageid;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		
	}
	
	private String title;
	private String filterUrl;
	private List<LanmuDetailHeadPartBean> bigImg;
	private List<LanmuDetailNormalPartBean> itemList;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilterUrl() {
		return filterUrl;
	}

	public void setFilterUrl(String filterUrl) {
		this.filterUrl = filterUrl;
	}

	public List<LanmuDetailHeadPartBean> getBigImg() {
		return bigImg;
	}

	public void setBigImg(List<LanmuDetailHeadPartBean> bigImg) {
		this.bigImg = bigImg;
	}

	public List<LanmuDetailNormalPartBean> getItemList() {
		return itemList;
	}

	public void setItemList(List<LanmuDetailNormalPartBean> itemList) {
		this.itemList = itemList;
	}

	public static LanmuDetailBean convertFromJsonObject(String httpResult) throws CntvException {
		LanmuDetailBean lanmuDetailBean = new LanmuDetailBean();
		try {
			JSONObject json = new JSONObject(httpResult);
			if (json == null || !JsonUtils.isJsonDataEffective(json, "data")) {
				return null;
			}
			
			JSONObject data = json.getJSONObject("data");
			if (JsonUtils.isJsonDataEffective(data, "title")) {
				lanmuDetailBean.setTitle(data.getString("title"));
			}
			if (JsonUtils.isJsonDataEffective(data, "filterUrl")) {
				lanmuDetailBean.setFilterUrl(data.getString("filterUrl"));
			}
			if (JsonUtils.isJsonArrayDataEffective(data, "bigImg")) {
				List<LanmuDetailHeadPartBean> lanmuDetailHeadPartBeans = new ArrayList<LanmuDetailHeadPartBean>();
				JSONArray bigImgs = data.getJSONArray("bigImg");
				for (int index = 0, count = bigImgs.length(); index < count; index++) {
					LanmuDetailHeadPartBean lanmuDetailHeadPartBean = new LanmuDetailHeadPartBean();
					JSONObject bigImg = bigImgs.getJSONObject(index);
					if (JsonUtils.isJsonDataEffective(bigImg, "title")) {
						lanmuDetailHeadPartBean.setTitle(bigImg.getString("title"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "brief")) {
						lanmuDetailHeadPartBean.setBrief(bigImg.getString("brief"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "imgUrl")) {
						lanmuDetailHeadPartBean.setImgUrl(bigImg.getString("imgUrl"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "bigImgUrl")) {
						lanmuDetailHeadPartBean.setBigImgUrl(bigImg.getString("bigImgUrl"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "vsetType")) {
						lanmuDetailHeadPartBean.setVsetType(bigImg.getString("vsetType"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "vsetId")) {
						lanmuDetailHeadPartBean.setVsetId(bigImg.getString("vsetId"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "shareUrl")) {
						lanmuDetailHeadPartBean.setShareUrl(bigImg.getString("shareUrl"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "isShow")) {
						lanmuDetailHeadPartBean.setIsShow(bigImg.getString("isShow"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "columnSo")) {
						lanmuDetailHeadPartBean.setColumnSo(bigImg.getString("columnSo"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "vsetPageid")) {
						lanmuDetailHeadPartBean.setVsetPageid(bigImg.getString("vsetPageid"));
					}
					if (JsonUtils.isJsonDataEffective(bigImg, "order")) {
						lanmuDetailHeadPartBean.setOrder(bigImg.getString("order"));
					}
					lanmuDetailHeadPartBeans.add(lanmuDetailHeadPartBean);
					break;
				}
				lanmuDetailBean.setBigImg(lanmuDetailHeadPartBeans);
			}
			
			if (JsonUtils.isJsonArrayDataEffective(data, "itemList")) {
				List<LanmuDetailNormalPartBean> lanmuDetailNormalPartBeans = new ArrayList<LanmuDetailNormalPartBean>();
				JSONArray itemList = data.getJSONArray("itemList");
				for (int index = 0, count = itemList.length(); index < count; index++) {
					LanmuDetailNormalPartBean lanmuDetailNormalPartBean = new LanmuDetailNormalPartBean();
					JSONObject itemListItem = itemList.getJSONObject(index);
					if (JsonUtils.isJsonDataEffective(itemListItem, "title")) {
						lanmuDetailNormalPartBean.setTitle(itemListItem.getString("title"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "lastVideo")) {
						lanmuDetailNormalPartBean.setLastVideo(itemListItem.getString("lastVideo"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "brief")) {
						lanmuDetailNormalPartBean.setBrief(itemListItem.getString("brief"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "imgUrl")) {
						lanmuDetailNormalPartBean.setImgUrl(itemListItem.getString("imgUrl"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "bigImgUrl")) {
						lanmuDetailNormalPartBean.setBigImgUrl(itemListItem.getString("bigImgUrl"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "vsetType")) {
						lanmuDetailNormalPartBean.setVsetType(itemListItem.getString("vsetType"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "vsetId")) {
						lanmuDetailNormalPartBean.setVsetId(itemListItem.getString("vsetId"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "shareUrl")) {
						lanmuDetailNormalPartBean.setShareUrl(itemListItem.getString("shareUrl"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "isShow")) {
						lanmuDetailNormalPartBean.setIsShow(itemListItem.getString("isShow"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "columnSo")) {
						lanmuDetailNormalPartBean.setColumnSo(itemListItem.getString("columnSo"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "vsetPageid")) {
						lanmuDetailNormalPartBean.setVsetPageid(itemListItem.getString("vsetPageid"));
					}
					if (JsonUtils.isJsonDataEffective(itemListItem, "order")) {
						lanmuDetailNormalPartBean.setOrder(itemListItem.getString("order"));
					}
					lanmuDetailNormalPartBeans.add(lanmuDetailNormalPartBean);
				}
				lanmuDetailBean.setItemList(lanmuDetailNormalPartBeans);
			}
			
			return lanmuDetailBean;
		} catch (JSONException e) {
			throw new CntvException("接口数据转换失败", e);
		}
	}
}
