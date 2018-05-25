package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Dates;
import com.crm.repository.IDateRepository;

@Repository
public class DateRepositoryImpl implements IDateRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for find all dates
	 * 
	 * @return List<Dates>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Dates> findAllDates() {
		return em.createQuery("from Dates date ORDER BY date.t_date DESC").getResultList();
	}

	/**
	 * Method for save Dates.
	 * 
	 * @param dates
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void saveDates(Dates dates) {
		em.persist(dates);

	}

	/**
	 * Method for update Dates
	 * 
	 * @param dates
	 * @return Dates
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Dates updateDates(Dates dates) {
		return em.merge(dates);
	}

	/**
	 * Method for finding Max Id of Dates.
	 * 
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findDateMaxId() {
		return em.createQuery("select max(date.id) from Dates date").setMaxResults(1).getResultList();
	}

	/**
	 * Method for finding Dates by Id.
	 * 
	 * @return List<Integer>
	 */
	public List<Dates> findDatesById(Integer datesId) {
		return em.createQuery("select date from Dates date where date.id=?1").setParameter(1, datesId).getResultList();
	}

	/**
	 * Method for delete Dates by datesId
	 * 
	 * @param datesId
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void deleteDates(List<Integer> datesId) {
		em.createQuery("delete from Dates date where date.id in ?1").setParameter(1, datesId).executeUpdate();
	}

}
