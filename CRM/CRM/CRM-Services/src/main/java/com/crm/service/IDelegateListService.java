package com.crm.service;

import java.util.List;

import com.crm.model.DelegateList;

public interface IDelegateListService {

	public void saveDelegateList(DelegateList delegateList);

	public DelegateList updateDelegateList(DelegateList delegateList);

	public void deleteDelegateList(Integer delegateListId);

	public void deleteDelegateListByBookingId(Integer bookingId);

	public List<DelegateList> findDelegateListByBId(Integer bookingId);

	public DelegateList findDelegateListMaxId(DelegateList delegateList);

}