package com.crm.service;

import java.util.List;

import com.crm.model.Dates;

public interface IDateService {
	
	public List<Dates> findAllDates();
	
	public void saveDates(Dates dates);

	public Dates findDatesMaxId(Dates dates);

	public void deleteDates(Integer[] datesId);

	public Dates updateDates(Dates dates);
	
	public List<Dates> findDatesById(Integer datesId);

}
