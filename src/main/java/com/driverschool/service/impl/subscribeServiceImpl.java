package com.driverschool.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.driverschool.dao.subscribeDAO;
import com.driverschool.model.subscribe;
import com.driverschool.service.subscribeService;

@Service
public class subscribeServiceImpl implements subscribeService {
	
	@Resource
	private subscribeDAO dao;

	public subscribe findSubscribeByUserId(String userId) {
		subscribe ss = dao.findSubscribeByUserId(userId);
		if(ss != null) {
			String subscribeStr = null;
			Date subscribeDate = ss.getSubscribeDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H点");
			subscribeStr = sdf.format(subscribeDate);
			ss.setSubscribeStr(subscribeStr);
		}
		return ss;
	}

	public subscribe findSubscribeBySubscribeStr(String subscribeStr) {
		subscribe ss = dao.findSubscribeBySubscribeStr(subscribeStr);
		return ss;
	}
	
	@Transactional
	public int updateSubscribeIsSubscribe(subscribe ss) {
		Date subscribeDate = new Date();
		String subscribeStr = ss.getSubscribeStr();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.setLenient(false);
			subscribeDate = sdf.parse(subscribeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ss.setSubscribeDate(subscribeDate);
		ss.setIsSubscribe("1");
		ss.setIsConfirm("0");
		int i = dao.updateSubscribeIsSubscribe(ss);
		return i;
	}

	public List<subscribe> findSubscribeIsSubscribe() {
		List<subscribe> result = dao.findSubscribeIsSubscribe();
		if(result != null && !result.isEmpty() ) {
			for(subscribe ss : result) {
				String subscribeStr = null;
				Date subscribeDate = ss.getSubscribeDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H点");
				subscribeStr = sdf.format(subscribeDate);
				ss.setSubscribeStr(subscribeStr);
			}
		}
		return result;
	}
	
	@Transactional
	public int addSubscribeIsNotSubscribe(subscribe ss) {
		Calendar today = Calendar.getInstance();
		Date enrolDate = today.getTime();
		ss.setEnrolDate(enrolDate);;
		int i = dao.addSubscribeIsNotSubscribe(ss);
		return i;
	}

	public List<subscribe> findSubscribeIsNotSubscribe() {
		List<subscribe> result = dao.findSubscribeIsNotSubscribe();
		if(result != null && !result.isEmpty() ) {
			for(subscribe ss : result) {
				String enrolStr = null;
				Date enrolDate = ss.getEnrolDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				enrolStr = sdf.format(enrolDate);
				ss.setEnrolStr(enrolStr);
			}
		}
		return result;
	}
	
	@Transactional
	public int updateSubscribeIsConfirm(subscribe ss) {
		int i = dao.updateSubscribeIsConfirm(ss);
		return i;
	}

}
