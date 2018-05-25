package com.crm.service;

import java.util.List;

import com.crm.model.Locations;
import com.crm.pojo.CalendarDTO;
import com.crm.pojo.LocationDTO;

public interface ILocationService {

	public List<Locations> findAllLocations();

	public List<LocationDTO> findAllLocationDate(CalendarDTO calendarDto);
	
	public void saveLocations(Locations locations);

	public Locations findLocationsMaxId(Locations locations);

	public void deleteLocations(Integer[] locationId);

	public Locations updateLocations(Locations locations);
	
	public List<Locations> findLocationsById(Integer locationsId);

	

	
}