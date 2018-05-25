package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.InvNotesTab;
import com.crm.repository.IInvNotesTabRepository;

@Repository
public class InvNotesTabRepositoryImpl implements IInvNotesTabRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save InvNotesTab(Expectations tab)
	 * @param invNotesTab
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveInvNotesTab(InvNotesTab invNotesTab) {
		em.persist(invNotesTab);
	}

	/**
	 * Method for update InvNotesTab(Expectations tab)
	 * @param invNotesTab
	 * @return InvNotesTab
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public InvNotesTab updateInvNotesTab(InvNotesTab invNotesTab) {
		return em.merge(invNotesTab);
	}

	/**
	 * Method for finding Max Id of InvNotesTab(Expectations tab)
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(invN.bId) from InvNotesTab invN")
				.setMaxResults(1).getResultList();
	}

}