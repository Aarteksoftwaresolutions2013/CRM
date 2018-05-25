package com.crm.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Consultants;
import com.crm.repository.IConsultantsRepository;
import com.crm.service.IConsultantsService;

@Service
public class ConsultantsServiceImpl implements IConsultantsService {

	@Autowired
	private IConsultantsRepository consultantsRepository;

	/**
	 * Method for find all consultants
	 * 
	 * @return consultantsList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Consultants> findAllConsultants() {
		List<Consultants> consultantsList = consultantsRepository.findAllConsultants();
		/*
		 * List<Consultants> listOfAdditionValue = new ArrayList<Consultants>();
		 * listOfAdditionValue.add(new Consultants(0, "Not Allocated"));
		 * listOfAdditionValue.add(new Consultants(0, "Enquiries"));
		 * listOfAdditionValue.add(new Consultants(0, "Meeting Room"));
		 * listOfAdditionValue.add(new Consultants(0, "Donations "));
		 * listOfAdditionValue.add(new Consultants(0, "Courses"));
		 * consultantsList.addAll(listOfAdditionValue);
		 * System.out.println(listOfAdditionValue);
		 */
		return consultantsList;
	}

}