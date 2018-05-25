package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.pojo.ReportingDTO;
import com.crm.service.IReportingService;

@RestController
public class ReportingController {

	@Autowired
	private IReportingService reportingService;

	@RequestMapping(value = "/findReporting", method = RequestMethod.GET)
	public List<Object[]> findReporting() {
		return reportingService.findReporting();
	}

	@RequestMapping(value = "/revenueVat", method = RequestMethod.POST)
	public List<Object[]> btnRevenueWithoutVatClick(@RequestBody ReportingDTO reportingDTO) {
		return reportingService.btnRevenueWithoutVatClick(reportingDTO);
	}

	@RequestMapping(value = "/revenue", method = RequestMethod.POST)
	public List<Object[]> btnRevenueClick(@RequestBody ReportingDTO reportingDTO) {
		return reportingService.btnRevenueClick(reportingDTO);
	}

	@RequestMapping(value = "/locatoins", method = RequestMethod.POST)
	public List<Object[]> btnlocationsClick(@RequestBody ReportingDTO reportingDTO) {
		System.out.println("inside ReportingController");
		return reportingService.btnlocationsClick(reportingDTO);
	}

	@RequestMapping(value = "/outstanding", method = RequestMethod.POST)
	public List<Object[]> btnOutstandingClick(@RequestBody ReportingDTO reportingDTO) {
		System.out.println("inside ReportingController");
		return reportingService.btnOutstandingClick(reportingDTO);
	}

	@RequestMapping(value = "/vatReport", method = RequestMethod.POST)
	public List<Object[]> btnVatReportClick(@RequestBody ReportingDTO reportingDTO) {
		System.out.println("inside ReportingController");
		return reportingService.btnVatReportClick(reportingDTO);
	}

	@RequestMapping(value = "/noOfCourse", method = RequestMethod.POST)
	public List<Object[]> btnCoursesClick(@RequestBody ReportingDTO reportingDTO) {
		System.out.println("btnCoursesClick Controller");
		return reportingService.btnCoursesClickService(reportingDTO);
	}

}