package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Consultants;
import com.crm.repository.IConsultantsRepository;

@Repository
public class ConsultantsRepositoryImpl implements IConsultantsRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method for finding all consultants
	 * @return consultantsList
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Consultants> findAllConsultants() {
		Query query = entityManager
				.createQuery("SELECT cons FROM Consultants cons");
		List<Consultants> consultantsList = query.getResultList();
		return consultantsList;
	}

}