package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.SalesPerson;
import com.crm.service.ISalesPersonService;

@RestController
@Controller
public class SalesPersonController {

	@Autowired
	private ISalesPersonService salesPersonService;

	/**
	 * Method for save sales person
	 * @param salesPerson
	 * @return SalesPerson
	 */
	@RequestMapping(value = "saveSalesPerson", method = RequestMethod.POST)
	public SalesPerson saveSalesPerson(@RequestBody SalesPerson salesPerson) {
		return salesPersonService.saveSalesPerson(salesPerson);
	}

	/**
	 * Method for get sales person by id
	 * @param salesPersonId
	 * @return SalesPerson
	 */
	@RequestMapping(value = "getSalesPersonById/{salesPersonId}", method = RequestMethod.GET)
	public SalesPerson getSalesPersonById(@PathVariable int salesPersonId) {
		return salesPersonService.findSalesPersonById(salesPersonId);
	}

	/**
	 * Method for get all sales person
	 * @return List<SalesPerson>
	 */
	@RequestMapping(value = "getAllSalesPerson", method = RequestMethod.GET)
	public List<SalesPerson> getAllSalesPerson() {
		return salesPersonService.getAllSalesPerson();
	}

}