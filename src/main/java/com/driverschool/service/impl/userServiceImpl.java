package com.driverschool.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.driverschool.dao.userDAO;
import com.driverschool.model.address;
import com.driverschool.model.user;
import com.driverschool.service.userService;

@Service
public class userServiceImpl implements userService {
	
	@Resource
	private userDAO dao;

	public user findUserByUserName(String username) {
		user user = dao.findUserByUserName(username);
		return user;
	}
	
	@Transactional
	public int addUser(user user) {
		int i = dao.addUser(user);
		return i;
	}

	public List<address> findAddress() {
		List<address> result = dao.findAddress();
		return result;
	}
	
	@Transactional
	public int updateUser(user user) {
		int i = dao.updateUser(user);
		return i;
	}

}
