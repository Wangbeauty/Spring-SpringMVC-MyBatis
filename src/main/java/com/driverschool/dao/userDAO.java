package com.driverschool.dao;

import java.util.List;

import com.driverschool.model.address;
import com.driverschool.model.user;

public interface userDAO {
	
	public user findUserByUserName(String username);
	public int addUser(user user);
	//地址下拉框
	public List<address> findAddress();
	//报名成功
	public int updateUser(user user);

}
