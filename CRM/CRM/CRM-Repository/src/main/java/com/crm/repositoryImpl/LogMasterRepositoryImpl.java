package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.LogMaster;
import com.crm.repository.ILogMasterRepository;

@Repository
public class LogMasterRepositoryImpl implements ILogMasterRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save log master
	 * @param logMaster
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveLogMaster(LogMaster logMaster) {
		em.persist(logMaster);
	}

	/** 
	 * Method for update log master
	 * @param logMaster
	 * @return LogMaster
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public LogMaster updateLogMaster(LogMaster logMaster) {
		return em.merge(logMaster);
	}

	/**
	 * Method for finding Max Id of log master
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(logM.id) from LogMaster logM")
				.setMaxResults(1).getResultList();
	}

	/**
	 * Method for finding comment by booking id and entry date
	 * @param bId
	 * @param startDate
	 * @param endDate
	 * @return List<LogMaster>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<LogMaster> findCommentByBookingIdAndEntrydate(Integer bId,
			String startDate, String endDate) {
		return em.createQuery("select lm from LogMaster lm where lm.bookingId=?1 and entryDate>=?2 and entryDate<=?3")
				.setParameter(1, bId).setParameter(2, startDate)
				.setParameter(3, endDate).getResultList();
	}

}