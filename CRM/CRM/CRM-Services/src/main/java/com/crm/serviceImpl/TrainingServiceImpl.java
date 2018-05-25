package com.crm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Training;
import com.crm.repository.ITrainingRepository;
import com.crm.service.ITrainingService;

@Service
public class TrainingServiceImpl implements ITrainingService {

	@Autowired
	private ITrainingRepository trainingRepository;

	/**
	 * Method for find training
	 * @return List<Training>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Training> findTraining() {
		return trainingRepository.findTraining();
	}

}