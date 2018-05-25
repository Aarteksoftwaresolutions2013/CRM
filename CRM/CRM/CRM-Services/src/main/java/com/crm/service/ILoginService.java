package com.crm.service;

import java.util.List;

import com.crm.model.UserMaster;

public interface ILoginService {

	public List<UserMaster> findUserByIdAndPassword(String userName,
			String password);

	public List<UserMaster> findAllUser();

	public UserMaster saveUser(UserMaster user);

	public void deleteUser(Integer userId);
	
	public List<UserMaster> findUserById(Integer userId);

}