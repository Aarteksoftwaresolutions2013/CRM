package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.UserMaster;
import com.crm.service.ILoginService;

@RestController
public class LoginController {

	@Autowired
	private ILoginService loginService;

	/**
	 * Method for login
	 * 
	 * @param user
	 * @return List<UserMaster>
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public List<UserMaster> login(@RequestBody UserMaster user) {
		return loginService.findUserByIdAndPassword(user.getUserName(),
				user.getPassword());
	}

	/**
	 * Method for find all user
	 * 
	 * @return List<UserMaster>
	 */
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public List<UserMaster> findAllUser() {
		return loginService.findAllUser();
	}

	/**
	 * Method for Save/Update user
	 * 
	 * @param userMaster
	 * @return UserMaster
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public UserMaster saveUser(@RequestBody UserMaster userMaster) {
		loginService.saveUser(userMaster);
		return userMaster;
	}

	/**
	 * Method for Delete user
	 * 
	 * @param userId
	 * @return void
	 */
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public void deleteUser(@PathVariable Integer userId) {
		loginService.deleteUser(userId);
	}
	
	
	/**
	 * Method for Edit user
	 * 
	 * @param userId
	 * @return List<UserMaster>
	 */
	@RequestMapping(value = "/findUserById/{userId}", method = RequestMethod.GET)
	public List<UserMaster> findUserById(@PathVariable Integer userId) {
		return loginService.findUserById(userId);
		 
	}
	
	
	

}