package com.driverschool.model;

import java.util.Date;

public class subscribe {
	
	private String id;
	private String userId;
	private String username;
	private String subscribeStr;
	private String enrolStr;
	private Date subscribeDate;//学车时间
	private Date enrolDate;//报名时间
	private String isSubscribe;
	private String isConfirm;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubscribeStr() {
		return subscribeStr;
	}
	public void setSubscribeStr(String subscribeStr) {
		this.subscribeStr = subscribeStr;
	}
	public Date getSubscribeDate() {
		return subscribeDate;
	}
	public void setSubscribeDate(Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getEnrolStr() {
		return enrolStr;
	}
	public void setEnrolStr(String enrolStr) {
		this.enrolStr = enrolStr;
	}
	public Date getEnrolDate() {
		return enrolDate;
	}
	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	public String getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

}
