package com.driverschool.dao;

import java.util.List;

import com.driverschool.model.subscribe;

public interface subscribeDAO {
	
	public subscribe findSubscribeBySubscribeStr(String subscribeStr);
	public subscribe findSubscribeByUserId(String userId);
	//教练安排练车时间
	public int updateSubscribeIsSubscribe(subscribe ss);
	public List<subscribe> findSubscribeIsSubscribe();
	//用户报名
	public int addSubscribeIsNotSubscribe(subscribe ss);
	public List<subscribe> findSubscribeIsNotSubscribe();
	//学员确认练车时间
	public int updateSubscribeIsConfirm(subscribe ss);

}
