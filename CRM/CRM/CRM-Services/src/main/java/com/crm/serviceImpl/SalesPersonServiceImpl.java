package com.crm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.SalesPerson;
import com.crm.repository.ISalesPersonRepository;
import com.crm.service.ISalesPersonService;

@Service
public class SalesPersonServiceImpl implements ISalesPersonService {

	@Autowired
	private ISalesPersonRepository salesPersonRepository;

	/**
	 * Method for save sales person
	 * @param salesPerson
	 * @return SalesPerson
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public SalesPerson saveSalesPerson(SalesPerson salesPerson) {
		return salesPersonRepository.saveSalesPerson(salesPerson);
	}

	/**
	 * Method for find sales person by id
	 * @param id
	 * @return SalesPerson
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public SalesPerson findSalesPersonById(Integer id) {
		return salesPersonRepository.findSalesPersonById(id);
	}

	/**
	 * Method for get all sales person
	 * @return salesPersonLists
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SalesPerson> getAllSalesPerson() {
		List<SalesPerson> salesPersonLists = salesPersonRepository
				.findAllSalesPerson();
		return salesPersonLists;
	}
}