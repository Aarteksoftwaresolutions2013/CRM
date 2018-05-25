package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.LogMaster;
import com.crm.service.ILogMasterService;

@RestController
public class LogMasterController {

	@Autowired
	private ILogMasterService logMasterService;

	/**
	 * Method for find comment by id, start date and end date
	 * @param bookingId
	 * @param startDate
	 * @param endDate
	 * @return List<LogMaster>
	 */
	@RequestMapping(value = "/commentByBIdAndStartDateAndEndDate/{bookingId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public List<LogMaster> findCommentByBIdAndStartDateAndEndDate(
			@PathVariable Integer bookingId, @PathVariable String startDate,
			@PathVariable String endDate) {
		return logMasterService.findCommentByBookingIdAndEntrydate(bookingId,
				startDate, endDate);
	}
}