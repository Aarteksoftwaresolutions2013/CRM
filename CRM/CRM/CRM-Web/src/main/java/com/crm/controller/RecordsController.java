package com.crm.controller;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Booking;
import com.crm.pojo.InvoiceDTO;
import com.crm.pojo.RecordsDTO;
import com.crm.service.IRecordsService;

@RestController
@Controller
public class RecordsController {

	@Autowired
	private IRecordsService iRecordsService;

	/**
	 * Method for save records page
	 * 
	 * @param recordsDTO
	 * @return void
	 */

	@RequestMapping(value = "/saveRecordsPage", method = RequestMethod.POST)
	public void saveRecordsPage(@RequestBody RecordsDTO recordsDTO) {
		iRecordsService.saveRecordsPage(recordsDTO);
	}

	@RequestMapping(value = "/btnConfEmailClick", method = RequestMethod.POST)
	public void btnConfEmailClick(@RequestBody Booking booking) throws Exception {
		System.out.println("controller");
		iRecordsService.btnConfEmailClick(booking);
	}

	@RequestMapping(value = "/createAndSendInvoice", method = RequestMethod.POST)
	public void createAndSendInvoice(@RequestBody RecordsDTO recordsDTO)
			throws IOException, InvalidFormatException, XmlException {
		iRecordsService.createAndSendInvoice(recordsDTO);
	}
	@RequestMapping(value = "/calculateTotal", method = RequestMethod.POST)
	public InvoiceDTO calculateTotal(@RequestBody RecordsDTO recordsDTO) throws IOException, InvalidFormatException, XmlException {
		System.out.println("inside calculateTotal controller");
		return iRecordsService.calculateTotal(recordsDTO);
		
		
		
		
	}

}