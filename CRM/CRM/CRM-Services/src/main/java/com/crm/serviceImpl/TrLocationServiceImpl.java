package com.crm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.TrLocation;
import com.crm.repository.ITrLocationRepository;
import com.crm.service.ITrLocationService;

@Service
public class TrLocationServiceImpl implements ITrLocationService {

	@Autowired
	private ITrLocationRepository trLocationRepository;

	/**
	 * Method for finding training location
	 * @return List<TrLocation>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TrLocation> findTrainingLocation() {
		return trLocationRepository.findTrainingLocation();
	}

}