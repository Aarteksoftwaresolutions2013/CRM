package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.pojo.BookingDTO;
import com.crm.pojo.DashboardFilterDTO;
import com.crm.pojo.RecordsDTO;
import com.crm.service.IBookingService;

@RestController
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	/**
	 * Method for find booking by id
	 * 
	 * @param bId
	 * @return List<RecordsDTO>
	 */
	@RequestMapping(value = "/findBookingById/{bId}", method = RequestMethod.GET)
	public List<RecordsDTO> findBookingById(@PathVariable Integer bId) {
		return bookingService.findValuesOfBookingByBId(bId);
	}

	/**
	 * Method for get all booking
	 * 
	 * @param dashboardFilterDTO
	 * @return List<RecordsDTO>
	 */
	@RequestMapping(value = "/getBooking", method = RequestMethod.POST)
	public List<RecordsDTO> getAllBooking(@RequestBody DashboardFilterDTO dashboardFilterDTO) {
		return bookingService.getAllBooking(dashboardFilterDTO);
	}

	/**
	 * Method for get booking by id
	 * 
	 * @param bId
	 * @return List<RecordsDTO>
	 */
	@RequestMapping(value = "/getBookingById/{bId}", method = RequestMethod.GET)
	public List<RecordsDTO> getAllBookingById(@PathVariable Integer bId) {
		return bookingService.findValuesOfBookingByBId(bId);
	}

	/**
	 * Method for get all training date by year
	 * 
	 * @param dashboardFilterDTO
	 * @return List<RecordsDTO>
	 */
	@RequestMapping(value = "/getAllTrainingDateByYear", method = RequestMethod.GET)
	public List<BookingDTO> getAllTrainingDateByYear() {
		return bookingService.findAllTrainingDateByYear();
	}

	/**
	 * Method for get all booking
	 * 
	 * @param dashboardFilterDTO
	 * @return List<RecordsDTO>
	 */
	@RequestMapping(value = "/getTotalNumOfBookingRecords", method = RequestMethod.POST)
	public Object getCountOfAllBooking(@RequestBody DashboardFilterDTO dashboardFilterDTO) {
		return bookingService.getCountOfAllBooking(dashboardFilterDTO);
	}

	@RequestMapping(value = "/getUsersByTrDate/{trDate}", method = RequestMethod.GET)
	public List<BookingDTO> btnBookingAndEnquiryList(@PathVariable String trDate) throws Exception {
		return bookingService.btnBookingAndEnquiryListService(trDate);
	}
}