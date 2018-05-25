package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Locations;
import com.crm.pojo.CalendarDTO;
import com.crm.pojo.LocationDTO;
import com.crm.service.ILocationService;

@RestController
public class LocationController {

	@Autowired
	private ILocationService locationService;

	/**
	 * Method for find all Locations
	 * 
	 * @return List<Locations>
	 */
	@RequestMapping(value = "/findAllLocation", method = RequestMethod.GET)
	public List<Locations> findAllLocations() {
		return locationService.findAllLocations();
	}
	
	
	/**
	 * Method for findAllLocationDateByYearAndMonth
	 * 
	 * @param year
	 * @param month
	 * @return List<LocationDTO>
	 */
	@RequestMapping(value = "/findAllLocationDate", method = RequestMethod.POST)
	public List<LocationDTO> findAllLocationDate(@RequestBody CalendarDTO calendarDto) {
		return locationService.findAllLocationDate(calendarDto);
	}

	/**
	 * Method for save Locations
	 * 
	 * @param locations
	 * @return void
	 */
	@RequestMapping(value = "/saveLocations", method = RequestMethod.POST)
	public void saveLocations(@RequestBody Locations locations) {
		locationService.saveLocations(locationService.findLocationsMaxId(locations));
	}

	/**
	 * Method for update Locations
	 * 
	 * @param locations
	 * @return Locations
	 */
	@RequestMapping(value = "/updateLocations", method = RequestMethod.POST)
	public Locations updateLocations(@RequestBody Locations locations) {
		return locationService.updateLocations(locations);
	}

	/**
	 * Method for delete Locations
	 *
	 * @param locationId
	 * @return void
	 */
	@RequestMapping(value = "/deleteLocations/{locationId}", method = RequestMethod.GET)
	public void deleteLocations(@PathVariable Integer[] locationId) {
		locationService.deleteLocations(locationId);
	}

	/**
	 * Method for find Locations by locationsId
	 * 
	 * @param locationsId
	 * @return List<Locations>
	 */
	@RequestMapping(value = "/findLocationsById/{locationsId}", method = RequestMethod.GET)
	public List<Locations> findDelegateListByBId(@PathVariable Integer locationsId) {
		return locationService.findLocationsById(locationsId);
	}

}