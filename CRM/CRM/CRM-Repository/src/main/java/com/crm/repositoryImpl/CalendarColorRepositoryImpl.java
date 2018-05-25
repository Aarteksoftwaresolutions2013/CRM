package com.crm.repositoryImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.CalendarColor;
import com.crm.pojo.CalendarDTO;
import com.crm.repository.ICalendarColorRepository;

@Repository
public class CalendarColorRepositoryImpl implements ICalendarColorRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Object[]> findCalendarColorByYearAndMonth(
			CalendarDTO calendarDto) {
		Query query = em
				.createNativeQuery("select date, color from calendercolor where Month(Date)=?2 and Year(Date)=?1");
		query.setParameter(1, calendarDto.getYear());
		query.setParameter(2, calendarDto.getMonth());
		List<Object[]> calendarColorList = query.getResultList();
		return calendarColorList;
	}

	/**
	 * Method for save calendarColor.
	 * 
	 * @param calendarColor
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveCalendarColor(CalendarColor calendarColor) {
		em.persist(calendarColor);
	}

	/**
	 * Method for deleteCalendarColor.
	 * 
	 * @param calendarColor
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteCalendarColor(Date date) {
		em.createQuery("delete from CalendarColor cc where cc.date=?1")
				.setParameter(1, date).executeUpdate();
	}

	/**
	 * Method for finding Max Id.
	 * 
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(cc.id) from CalendarColor cc")
				.setMaxResults(1).getResultList();
	}
}