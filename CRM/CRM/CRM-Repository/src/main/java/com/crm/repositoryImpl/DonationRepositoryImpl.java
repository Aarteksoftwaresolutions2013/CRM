package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.repository.IDonationRepository;

@Repository
public class DonationRepositoryImpl implements IDonationRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for finding MeetingRoom, confirmation, training location, tracking,
	 * 
	 * 
	 * @param year
	 * @param month
	 * @return bookingLists
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findDonationByYearAndMonth(String year, String month) {
		Query query = em.createNativeQuery(
				"select id donation_id, name donation_name, companyname donation_companyname, tel donation_tel,"
						+ "email donation_email, donationinfo donation_donationinfo, collectiondate donation_collectiondate,"
						+ "submitdatetime donation_submitdatetime, certificateposted donation_certificateposted "
						+ "From donation where YEAR(donation.collectiondate)= ?1 and MONTH(donation.collectiondate)= ?2 ");
		query.setParameter(1, year);
		query.setParameter(2, month);
		List<Object[]> donationLists = query.getResultList();
		return donationLists;
	}

}