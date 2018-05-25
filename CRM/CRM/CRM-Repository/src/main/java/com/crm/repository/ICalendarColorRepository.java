package com.crm.repository;

import java.util.Date;
import java.util.List;

import com.crm.model.CalendarColor;
import com.crm.pojo.CalendarDTO;

public interface ICalendarColorRepository {

	public List<Object[]> findCalendarColorByYearAndMonth(
			CalendarDTO calendarDto);

	public void saveCalendarColor(CalendarColor calendarColor);

	public void deleteCalendarColor(Date date);

	public List<Integer> findMaxId();
}