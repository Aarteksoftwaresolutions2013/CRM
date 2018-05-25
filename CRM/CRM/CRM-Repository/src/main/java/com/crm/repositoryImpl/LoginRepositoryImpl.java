package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.UserMaster;
import com.crm.repository.ILoginRepository;

@Repository
public class LoginRepositoryImpl implements ILoginRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for finding user by userName and password
	 * 
	 * @param userName
	 * @param password
	 * @return user
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<UserMaster> findUserByIdAndPassword(String userName,
			String password) {
		Query query = em
				.createQuery("SELECT user FROM UserMaster user WHERE user.userName = ? and  user.password = ?");
		query.setParameter(1, userName);
		query.setParameter(2, password);
		List<UserMaster> user = query.getResultList();
		return user;
	}

	/**
	 * Method for finding all users
	 * 
	 * @return List<UserMaster>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<UserMaster> findAllUser() {
		Query query = em.createQuery("SELECT user FROM UserMaster user");
		List<UserMaster> user = query.getResultList();
		return user;
	}

	/**
	 * Method for Save/Update user
	 * 
	 * @param userMaster
	 * @return UserMaster
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public UserMaster saveUser(UserMaster userMaster) {
		return em.merge(userMaster);
	}

	/**
	 * Method for Delete user
	 * 
	 * @param userId
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteUser(Integer userId) {
		em.createQuery("delete from UserMaster um where um.id=?1")
				.setParameter(1, userId).executeUpdate();
	}
	
	/**
	 * Method for Edit user
	 * 
	 * @param userId
	 * @return List<UserMaster>
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<UserMaster> findUserById(Integer userId) {
		return em.createQuery("select um from UserMaster um where um.id=?1")
				.setParameter(1, userId).getResultList();
		
	}


	/**
	 * Method for finding Max Id of User
	 * 
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(um.id) from UserMaster um")
				.setMaxResults(1).getResultList();
	}

}