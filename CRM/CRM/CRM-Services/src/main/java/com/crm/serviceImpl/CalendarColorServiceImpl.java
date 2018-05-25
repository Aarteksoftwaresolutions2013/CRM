package com.crm.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.CalendarColor;
import com.crm.repository.ICalendarColorRepository;
import com.crm.service.ICalendarColorService;

@Service
public class CalendarColorServiceImpl implements ICalendarColorService {

	@Autowired
	private ICalendarColorRepository calendarColorRepository;

	/**
	 * Method for save calendarColor.
	 * 
	 * @param calendarColor
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveCalendarColor(CalendarColor calendarColor) {
		try {
			calendarColor.setDate(new SimpleDateFormat("yyyy-MM-dd")
					.parse(calendarColor.getDateDummy()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendarColor.setId(calendarColorRepository.findMaxId().get(0)+1);
		calendarColorRepository.deleteCalendarColor(calendarColor.getDate());
		calendarColorRepository.saveCalendarColor(calendarColor);
	}

}