package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Invoice;
import com.crm.repository.IInvoiceRepository;

@Repository
public class InvoiceRepositoryImpl implements IInvoiceRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save Invoice
	 * @param invoice
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveInvoice(Invoice invoice) {
		em.persist(invoice);
	}

	/**
	 * Method for update invoice
	 * @param invoice
	 * @return Invoice
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Invoice updateInvoice(Invoice invoice) {
		return em.merge(invoice);
	}

	/**
	 * Method for finding Max Id for invoice
	 * @return List<Integer> 
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(inv.id) from Invoice inv")
				.setMaxResults(1).getResultList();
	}

}