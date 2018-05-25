package com.crm.service;

import java.util.List;

import com.crm.model.Booking;
import com.crm.pojo.BookingDTO;
import com.crm.pojo.DashboardFilterDTO;
import com.crm.pojo.RecordsDTO;

public interface IBookingService {

	public Booking findBookingById(Integer id);

	public List<RecordsDTO> getAllBooking(DashboardFilterDTO dashboardFilterDTO);

	public List<RecordsDTO> findValuesOfBookingByBId(Integer bId);

	public List<BookingDTO> findAllTrainingDateByYear();

	public Object getCountOfAllBooking(DashboardFilterDTO dashboardFilterDTO);

	public List<BookingDTO> btnBookingAndEnquiryListService(String date);

}