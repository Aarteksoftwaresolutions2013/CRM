package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.TrLocation;
import com.crm.repository.ITrLocationRepository;

@Repository
public class TrLocationRepositoryImpl implements ITrLocationRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for find training location 
	 * @return List<TrLocation>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<TrLocation> findTrainingLocation() {
		return em.createQuery("from TrLocation").getResultList();
	}
	
	/**
	 * Method for find training location 
	 * @return List<TrLocation>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<TrLocation> findTrainingLocation1(Integer noOfItems, Integer pageNo) {
		
		pageNo = ((pageNo-1)*noOfItems);
		
		return em.createQuery("from TrLocation")
				.setFirstResult(pageNo).setMaxResults(noOfItems).getResultList();
	}
}