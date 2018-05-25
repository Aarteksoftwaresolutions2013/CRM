package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.DelegateList;
import com.crm.repository.IDelegateListRepository;

@Repository
public class DelegateListRepositoryImpl implements IDelegateListRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save delegate list
	 * @param delegateList
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveDelegateList(DelegateList delegateList) {
		em.persist(delegateList);
	}

	/**
	 * Method for update delegate list
	 * @param delegateList
	 * @return DelegateList
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public DelegateList updateDelegateList(DelegateList delegateList) {
		return em.merge(delegateList);
	}

	/**
	 * Method for delete delegate list
	 * @param delegateId
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteDelegateList(Integer delegateId) {
		em.createQuery("delete from DelegateList dl where dl.id=?1")
				.setParameter(1, delegateId).executeUpdate();
	}

	/**
	 * Method for delete delegate list by booking id
	 * @param bid
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteDelegateListByBId(Integer bid) {
		em.createQuery("delete from DelegateList dl where dl.bid=?1")
				.setParameter(1, bid).executeUpdate();
	}

	/**
	 * Method for finding Max Id of delegate list
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(delL.id) from DelegateList delL")
				.setMaxResults(1).getResultList();
	}

	/**
	 * Method for finding delegate list by booking id
	 * @param bid
	 * @return List<DelegateList>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<DelegateList> findDelegateListByBId(Integer bid) {
		return em.createQuery("select delL from DelegateList delL where delL.bid=?1")
				.setParameter(1, bid).getResultList();
	}
}