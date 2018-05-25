package com.crm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.UserMaster;
import com.crm.repository.ILoginRepository;
import com.crm.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginRepository loginRepository;

	/**
	 * Method for find user by userName and password
	 * 
	 * @param userName
	 * @param password
	 * @return List<UserMaster>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<UserMaster> findUserByIdAndPassword(String userName,
			String password) {
		List<UserMaster> user = loginRepository.findUserByIdAndPassword(
				userName, password);
		if (user != null) {
			return user;
		}
		return user;
	}

	/**
	 * Method for find all users
	 * 
	 * @return List<UserMaster>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<UserMaster> findAllUser() {
		List<UserMaster> user = loginRepository.findAllUser();
		return user;
	}

	/**
	 * Method for Save/Update user
	 * 
	 * @param user
	 * @return UserMaster
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public UserMaster saveUser(UserMaster user) {

		if (user.getId() == null) {
			List<Integer> findMaxId = loginRepository.findMaxId();
			user.setId(findMaxId.get(0) + 1);
		}

		return loginRepository.saveUser(user);
	}

	/**
	 * Method for Delete user
	 * 
	 * @param userId
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(Integer userId) {
		loginRepository.deleteUser(userId);
	}
	
	/**
	 * Method for Edit user
	 * 
	 * @param userId
	 * @return List<UserMaster>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<UserMaster> findUserById(Integer userId) {
		return loginRepository.findUserById(userId);
		 
	}

}