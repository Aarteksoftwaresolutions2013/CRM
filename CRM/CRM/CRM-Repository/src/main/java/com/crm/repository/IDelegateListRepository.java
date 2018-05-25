package com.crm.repository;

import java.util.List;

import com.crm.model.DelegateList;

public interface IDelegateListRepository {

	public void saveDelegateList(DelegateList delegateList);

	public DelegateList updateDelegateList(DelegateList delegateList);

	public void deleteDelegateList(Integer delegateId);

	public List<Integer> findMaxId();

	public void deleteDelegateListByBId(Integer bid);

	public List<DelegateList> findDelegateListByBId(Integer bid);
}