package com.crm.repository;

import java.util.List;

import com.crm.model.Training;

public interface ITrainingRepository {

	public List<Training> findTraining();
}