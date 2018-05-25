package com.crm.service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;

import com.crm.model.Booking;
import com.crm.pojo.InvoiceDTO;
import com.crm.pojo.RecordsDTO;

public interface IRecordsService {

	public void saveRecordsPage(RecordsDTO recordsDTO);

	public void btnConfEmailClick(Booking booking) throws Exception;

	public void createAndSendInvoice(RecordsDTO recordsDTO) throws IOException, InvalidFormatException, XmlException;

	public InvoiceDTO calculateTotal(RecordsDTO recordsDTO);

}