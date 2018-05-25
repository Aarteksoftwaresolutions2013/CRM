package com.crm.repository;

import java.util.List;

import com.crm.pojo.ReportingDTO;

public interface IReportingRepository {

	public List<Object[]> findAllReporting();

	public List<Object[]> btnRevenueWithoutVatClick(ReportingDTO reportingDTO, String queryStartPart,
			String queryStartPartMeetingRoom, String queryStartPartCourse);

	public String getQueryString(ReportingDTO reportingDTO);

	public List<Object[]> btnRevenueClick(ReportingDTO reportingDTO, String queryStartPart,
			String queryStartPartMeetingRoom, String queryStartPartCourse);

	public List<Object[]> btnLocationsClick(ReportingDTO reportingDTO, String queryStartPart);

	public List<Object[]> btnOutstandingClick(ReportingDTO reportingDTO, String queryStartPart);

	public List<Object[]> btnVatReportClick(ReportingDTO reportingDTO, String queryStartPart);

	public List<Object[]> btnCoursesClickRepository(ReportingDTO reportingDTO, String queryStartPart);

}