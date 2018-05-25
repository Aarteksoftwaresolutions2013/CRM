package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Training;
import com.crm.service.ITrainingService;

@RestController
@Controller
public class TrainingController {

	@Autowired
	private ITrainingService trainingService;

	/**
	 * Method for get all training
	 * @return List<Training>
	 */
	@RequestMapping(value = "getTraining", method = RequestMethod.GET)
	public List<Training> getAllTraining() {
		return trainingService.findTraining();
	}

}