package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.CalendarColor;
import com.crm.service.ICalendarColorService;

@RestController
public class CalendarColorController {

	@Autowired
	private ICalendarColorService calendarColorService;

	/**
	 * Method for save CalendarColor
	 * 
	 * @param calendarColor
	 * @return void
	 */
	@RequestMapping(value = "saveCalendarColor", method = RequestMethod.POST)
	public void saveCalendarColor(@RequestBody CalendarColor calendarColor) {
		calendarColorService.saveCalendarColor(calendarColor);
	}

}
