package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.TrLocation;
import com.crm.repository.ITrLocationRepository;
import com.crm.service.ITrLocationService;

@RestController
@Controller
public class TrLocationController {

	@Autowired
	private ITrLocationService trLocationService;
	
	@Autowired
	private ITrLocationRepository locationRepository;
	

	/**
	 * Method for get training location
	 * @return List<TrLocation>
	 */
	@RequestMapping(value = "getTrainingLocation", method = RequestMethod.GET)
	public List<TrLocation> getTrainingLocation() {
		return trLocationService.findTrainingLocation();
	}
	
	/**
	 * Method for get training location
	 * @return List<TrLocation>
	 */
	@RequestMapping(value = "/getLoc/{noOfItems}/{pageNo}", method = RequestMethod.GET)
	public List<TrLocation> getTrainingLocation1(@PathVariable Integer noOfItems, @PathVariable Integer pageNo) {
		return locationRepository.findTrainingLocation1(noOfItems, pageNo);
	}

}