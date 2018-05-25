package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Confirmation;
import com.crm.repository.IConfirmationRepository;

@Repository
public class ConfirmationRepositoryImpl implements IConfirmationRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for saving confirmation
	 * @param confirmation
	 * @return confirmation
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveConfirmation(Confirmation confirmation) {
		em.persist(confirmation);
	}

	/**
	 * Method for update confirmation
	 * @param confirmation
	 * @return confirmation
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Confirmation updateConfirmation(Confirmation confirmation) {
		return em.merge(confirmation);
	}
	
	/**
	 * Method for finding Max Id
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(con.bId) from Confirmation con")
				.setMaxResults(1).getResultList();
	}

}