package com.crm.service;

import java.util.List;

import com.crm.model.SalesPerson;

public interface ISalesPersonService {

	public SalesPerson saveSalesPerson(SalesPerson salesPerson);

	public SalesPerson findSalesPersonById(Integer id);

	public List<SalesPerson> getAllSalesPerson();
}