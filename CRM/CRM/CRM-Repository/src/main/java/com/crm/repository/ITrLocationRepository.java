package com.crm.repository;

import java.util.List;

import com.crm.model.TrLocation;

public interface ITrLocationRepository {

	public List<TrLocation> findTrainingLocation();
	
	public List<TrLocation> findTrainingLocation1(Integer noOfItems, Integer pageNo);
}