package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Consultants;
import com.crm.service.IConsultantsService;

@Controller
@RestController
public class ConsultantsController {

	@Autowired
	private IConsultantsService consultantsService;

	/**
	 * Method for find all consultants
	 * @return List<Consultants>
	 */
	@RequestMapping(value = "/getAllConsultants", method = RequestMethod.GET)
	public List<Consultants> findAllConsultants() {
		return consultantsService.findAllConsultants();
	}

}