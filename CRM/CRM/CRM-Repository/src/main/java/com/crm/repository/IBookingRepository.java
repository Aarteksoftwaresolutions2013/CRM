package com.crm.repository;

import java.sql.Date;
import java.util.List;

import com.crm.model.Booking;
import com.crm.pojo.CalendarDTO;
import com.crm.pojo.DashboardFilterDTO;

public interface IBookingRepository {

	public void saveBooking(Booking booking); 

	public Booking updateBooking(Booking booking);

	public Booking findBookingById(int articleId);

	public List<Object[]> findAllBooking(DashboardFilterDTO dashboardFilterDTO);

	public List<Integer> getAllBIdFromInvoice(String paidByValue);
	
	public Integer getInvoiceNumber(String paymentType);

	public List<Object[]> findBookingConfirmationTrLocationCouriertrackingSalesPersonByBId(int bId);

	public List<Integer> findMaxId();

	public List<Date> findAllTrainingDateByYear();

	public Object findCountOfAllBooking(DashboardFilterDTO dashboardFilterDTO);

	public List<Integer> getAllBIdFromConfirmationLocation(String notConfirmedLocations);

	public List<Integer> getAllBIdFromConfirmationInhouse(String notConfirmedLocations);

	public List<Object[]> findBookingByYearAndMonth(CalendarDTO calendarDto);
	
	public List<Object[]> btnBookingAndEnquiryListRepo(String date);

}