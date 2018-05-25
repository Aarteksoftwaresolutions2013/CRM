package com.crm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.DelegateList;
import com.crm.repository.IDelegateListRepository;
import com.crm.service.IDelegateListService;

@Service
public class DelegateListServiceImpl implements IDelegateListService {

	@Autowired
	private IDelegateListRepository delgateRepository;

	/**
	 * Method for save delegate list
	 * @param delegateList
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDelegateList(DelegateList delegateList) {
		delgateRepository.saveDelegateList(delegateList);
	}

	/**
	 * Method for update delegate list
	 * @param delegateList
	 * @return DelegateList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public DelegateList updateDelegateList(DelegateList delegateList) {
		return delgateRepository.updateDelegateList(delegateList);
	}

	/**
	 * Method for delete delegate list 
	 * @param delegateListId
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDelegateList(Integer delegateListId) {
		delgateRepository.deleteDelegateList(delegateListId);
	}

	/**
	 * Method for delete delegate list by booking id
	 * @param bookingId
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDelegateListByBookingId(Integer bookingId) {
		delgateRepository.deleteDelegateListByBId(bookingId);
	}

	/**
	 * Method for find delegate list by booking id
	 * @param bookingId
	 * @return List<DelegateList>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<DelegateList> findDelegateListByBId(Integer bookingId) {
		return delgateRepository.findDelegateListByBId(bookingId);
	}

	/**
	 * Method for find delegate list Max Id
	 * @param delegateList
	 * @return delegateList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public DelegateList findDelegateListMaxId(DelegateList delegateList) {
		List<Integer> delMaxId = delgateRepository.findMaxId();
		delegateList.setId(delMaxId.get(0) + 1);
		return delegateList;
	}

}