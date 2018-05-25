package com.crm.repository;

import java.util.List;

import com.crm.model.Locations;
import com.crm.pojo.CalendarDTO;

public interface ILocationRepository {

	public List<Locations> findAllLocations();

	public List findAllLocationDate(CalendarDTO calendarDto);
	
	public void saveLocations(Locations locations);

	public Locations updateLocations(Locations locations);

	public void deleteLocations(List<Integer> locationsId);

	public List<Integer> findMaxId();

	public List<Locations> findLocationsById(Integer locationsId);

}