package com.crm.repository;

import java.util.List;

import com.crm.model.Invoice;

public interface IInvoiceRepository {

	public void saveInvoice(Invoice invoice);

	public Invoice updateInvoice(Invoice invoice);

	public List<Integer> findMaxId();

}