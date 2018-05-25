package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Training;
import com.crm.repository.ITrainingRepository;

@Repository
public class TrainingRepositoryImpl implements ITrainingRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for find list of training 
	 * @return List<Training>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Training> findTraining() {
		return em.createQuery("from Training").getResultList();
	}
}