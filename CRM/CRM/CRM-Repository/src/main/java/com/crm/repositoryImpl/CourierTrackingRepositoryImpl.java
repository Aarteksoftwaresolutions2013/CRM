package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.CourierTracking;
import com.crm.repository.ICourierTrackingRepository;

@Repository
public class CourierTrackingRepositoryImpl implements
		ICourierTrackingRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save courier tracking
	 * @param courierTracking
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveCourierTracking(CourierTracking courierTracking) {
		em.persist(courierTracking);
	}

	/**
	 * Method for update courier tracking
	 * @param courierTracking
	 * @return CourierTracking
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public CourierTracking updateCourierTracking(CourierTracking courierTracking) {
		return em.merge(courierTracking);
	}
	
	/**
	 * Method for finding Max Id
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(cot.id) from CourierTracking cot")
				.setMaxResults(1).getResultList();
	}

}
