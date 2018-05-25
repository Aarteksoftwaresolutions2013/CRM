package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.repository.IMeetingRepository;

@Repository
public class MeetingRepositoryImpl implements IMeetingRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for finding MeetingRoom, confirmation, training location,
	 * tracking,
	 * 
	 * 
	 * @param year
	 * @param month
	 * @return meetingLists
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findMeetingByYearAndMonth(String year, String month) {
		Query query = em.createNativeQuery(
				"select mr.id mr_id, mr.title mr_title, mr.full_name mr_full_name, mr.tel mr_tel, mr.companyname mr_companyname, mr.meetingroomhiredate mr_mrhiredate, "
						+ "mr.meetingroomhireenddate mr_mrhireenddate, mr.startingtime mr_startingtime, mr.endingtime mr_endingtime, "
						+ "mr.numberofdelegates mr_numberofdelegates, mr.telephone mr_telephone,mr.labelname mr_labelname,mr.booking_enquiry "
						+ "mr_booking_enquiry, mr.duration mr_duration, mr.email mr_email, mr.dt mr_dt, mr.submitdatetime mr_submitdatetime, "
						+ "mr.discount_code mr_discount_code, mr.message mr_message, mr.callbacktime mr_callbacktime, mr.coffeetime1 mr_coffeetime1, "
						+ "mr.coffeetime2 mr_coffeetime2, mr.coffeetime3 mr_coffeetime3, mr.iscoffeeunlimited mr_iscoffeeunlimited, mr.islunchrequired "
						+ "mr_islunchrequired, mr.lunchtime mr_lunchtime, mr.lunchinformation mr_lunchinformation, mr.sittingstyle mr_sittingstyle, "
						+ "mr.sittingstylecomment mr_sittingstylecomment, mr.numberofcomputers mr_numberofcomputers, mr.informationaboutthecourse mr_informationaboutthecourse "
						+ "from meetingroom mr " + "LEFT JOIN invoicemeetingroom invMr ON  mr.id=invMr.meetingroomid "
						+ "LEFT JOIN confirmationmeetingroom conMr ON   mr.id=conMr.meetingroomid "
						+ "LEFT JOIN futuretrainingmeetingroom futureMr ON   mr.id=futureMr.meetingroomid "
						+ "where YEAR(mr.meetingroomhiredate)= ?1 AND MONTH(mr.meetingroomhiredate)= ?2  AND IFNULL(mr.typeOfCall,'') <> 'Spoken toreceptionist' AND mr.booking_enquiry<> 'Cancelled' ");
		query.setParameter(1, year);
		query.setParameter(2, month);
		List<Object[]> meetingLists = query.getResultList();
		return meetingLists;
	}

}