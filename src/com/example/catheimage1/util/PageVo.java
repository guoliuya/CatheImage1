/*
 * 作者		刘国山 liugs@tydic.com
 * 开发环境	Win7 Eclipse3.5 JDK1.6
 * 开发日期	2012-7-20
 */
package com.example.catheimage1.util;

import java.io.Serializable;
/**
 * 分页实体
 * @Description 
 * @author 刘国山 liugs@tydic.com
 * @version 1.0 2012-7-20
 * @class com.tydic.takeout.utils.PageVo
 */
public class PageVo implements Serializable {
	
	/**
	 * @Description 
	 * @author 刘国山 liugs@tydic.com
	 * @version 1.0 2012-7-20
	 */
	private static final long serialVersionUID = -570254046592256580L;
	
	//每页条数
	private int pageSize;
	//当前页码
	private int currentPage;
	//总数
	private int countNum;
	//总共多少页
	private int pageCount;
	//是否有上一页
	private boolean hasLeft;
	//是否有下一页
	private boolean hasRight;
	//是否有数据
	private boolean hasData;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isHasLeft() {
		return hasLeft;
	}
	public void setHasLeft(boolean hasLeft) {
		this.hasLeft = hasLeft;
	}
	public boolean isHasRight() {
		return hasRight;
	}
	public void setHasRight(boolean hasRight) {
		this.hasRight = hasRight;
	}
	public boolean isHasData() {
		return hasData;
	}
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}

}
