package com.crm.repository;

import java.util.List;

import com.crm.model.Dates;

public interface IDateRepository {
	
	public List<Dates> findAllDates();
	
	public void saveDates(Dates dates);

	public Dates updateDates(Dates dates);

	public void deleteDates(List<Integer> datesId);

	public List<Integer> findDateMaxId();

	public List<Dates> findDatesById(Integer datesId);

}
