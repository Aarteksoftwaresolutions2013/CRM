package com.crm.service;

import java.util.List;

import com.crm.pojo.ReportingDTO;

public interface IReportingService {

	public List<Object[]> findReporting();

	public List<Object[]> btnRevenueWithoutVatClick(ReportingDTO reportingDTO);

	public List<Object[]> btnRevenueClick(ReportingDTO reportingDTO);

	public List<Object[]> btnlocationsClick(ReportingDTO reportingDTO);

	public List<Object[]> btnOutstandingClick(ReportingDTO reportingDTO);

	public List<Object[]> btnVatReportClick(ReportingDTO reportingDTO);

	public List<Object[]> btnCoursesClickService(ReportingDTO reportingDTO);

}