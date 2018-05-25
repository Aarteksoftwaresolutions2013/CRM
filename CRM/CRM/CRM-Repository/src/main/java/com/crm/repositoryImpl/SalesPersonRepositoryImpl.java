package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.SalesPerson;
import com.crm.repository.ISalesPersonRepository;

@Repository
public class SalesPersonRepositoryImpl implements ISalesPersonRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save sales person
	 * @param salesPerson
	 * @return SalesPerson
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public SalesPerson saveSalesPerson(SalesPerson salesPerson) {
		return em.merge(salesPerson);
	}

	/**
	 * Method for finding sales person by id
	 * @param salePersonId
	 * @return SalesPerson
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public SalesPerson findSalesPersonById(int salePersonId) {
		return em.find(SalesPerson.class, salePersonId);
	}

	/**
	 * Method for finding all sales person
	 * @return List<SalesPerson>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<SalesPerson> findAllSalesPerson() {
		Query query = em
				.createQuery("select sp from SalesPerson sp where sp.status=?");
		query.setParameter(1, true);
		List<SalesPerson> ssalesPersonLists = query.getResultList();
		return ssalesPersonLists;
	}

}