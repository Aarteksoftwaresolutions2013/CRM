package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Locations;
import com.crm.pojo.CalendarDTO;
import com.crm.repository.ILocationRepository;

@Repository
public class LocationRepositoryImpl implements ILocationRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for finding All Locations.
	 * 
	 * @return List<Locations>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Locations> findAllLocations() {
		return em.createQuery("select loc from Locations loc").getResultList();
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Locations> findAllLocationDate(CalendarDTO calendarDTO) {
		Query query = em.createNativeQuery("SELECT DISTINCT " + "  locations.location, " + "  dates.t_date, "
				+ "  locations.shortname\r\n" + "FROM Locations locations " + "  JOIN Dates dates "
				+ "    ON locations.location = dates.location "
				+ "WHERE dates.addtocalender = 1 and YEAR(dates.t_date)= ?1 and MONTH(dates.t_date) = ?2");
		query.setParameter(1, calendarDTO.getYear());
		query.setParameter(2, calendarDTO.getMonth());
		return query.getResultList();
	}

	/**
	 * Method for save Locations.
	 * 
	 * @param locations
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveLocations(Locations locations) {
		em.persist(locations);
	}

	/**
	 * Method for update Locations
	 * 
	 * @param locations
	 * @return Locations
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Locations updateLocations(Locations locations) {
		return em.merge(locations);
	}

	/**
	 * Method for finding Max Id of Locations.
	 * 
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(loc.id) from Locations loc").setMaxResults(1).getResultList();
	}

	/**
	 * Method for finding Locations By LocationId.
	 * 
	 * @param locationsId
	 * @return List<Locations>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Locations> findLocationsById(Integer locationsId) {
		return em.createQuery("select loc from Locations loc where loc.id=?1").setParameter(1, locationsId)
				.getResultList();
	}

	/**
	 * Method for delete Locations by locationsId
	 * 
	 * @param locationsId
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteLocations(List<Integer> locationsId) {
		em.createQuery("delete from Locations loc where loc.id in ?1").setParameter(1, locationsId).executeUpdate();

	}

	

}