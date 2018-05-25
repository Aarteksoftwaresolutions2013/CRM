package com.crm.repository;

import java.util.List;

import com.crm.model.SalesPerson;

public interface ISalesPersonRepository {

	public SalesPerson saveSalesPerson(SalesPerson salesPerson);

	public SalesPerson findSalesPersonById(int salePersonId);

	public List<SalesPerson> findAllSalesPerson();

}
