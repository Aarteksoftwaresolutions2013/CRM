package com.crm.repository;

import java.util.List;

public interface IMeetingRepository {

	public List<Object[]> findMeetingByYearAndMonth(String year, String month);

}