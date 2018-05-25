package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Dates;
import com.crm.service.IDateService;

@RestController
public class DatesController {

	@Autowired
	private IDateService dateservice;

	/**
	 * Method for find all Locations
	 * 
	 * @return List<Dates>
	 */
	@RequestMapping(value = "/findAllDate", method = RequestMethod.GET)
	public List<Dates> findAllDates() {
		return dateservice.findAllDates();
	}

	/**
	 * Method for save Dates
	 * 
	 * @param dates
	 * @return void
	 */
	@RequestMapping(value = "/saveDates", method = RequestMethod.POST)
	public void saveDates(@RequestBody Dates dates) {
		dateservice.saveDates(dateservice.findDatesMaxId(dates));
	}

	/**
	 * Method for update Dates
	 * 
	 * @param dates
	 * @return Dates
	 */
	@RequestMapping(value = "/updateDates", method = RequestMethod.POST)
	public Dates updateDates(@RequestBody Dates dates) {
		return dateservice.updateDates(dates);
	}

	/**
	 * Method for delete Dates
	 *
	 * @param datesId
	 * @return void
	 */
	@RequestMapping(value = "/deleteDates/{datesId}", method = RequestMethod.GET)
	public void deleteDates(@PathVariable Integer[] datesId) {
		dateservice.deleteDates(datesId);
	}

	/**
	 * Method for find Dates by datesId
	 * 
	 * @param datesId
	 * @return List<Dates>
	 */
	@RequestMapping(value = "/findDatesById/{datesId}", method = RequestMethod.GET)
	public List<Dates> findDatesById(@PathVariable Integer datesId) {
		return dateservice.findDatesById(datesId);
	}
}
