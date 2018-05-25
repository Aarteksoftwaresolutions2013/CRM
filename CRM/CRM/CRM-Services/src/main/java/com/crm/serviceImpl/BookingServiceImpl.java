package com.crm.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Booking;
import com.crm.pojo.BookingDTO;
import com.crm.pojo.DashboardFilterDTO;
import com.crm.pojo.RecordsDTO;
import com.crm.repository.IBookingRepository;
import com.crm.service.IBookingService;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	/**
	 * Method for find booking by id
	 * 
	 * @param id
	 * @return Booking
	 */
	public Booking findBookingById(Integer id) {
		return bookingRepository.findBookingById(id);
	}

	/**
	 * Method for get all booking
	 * 
	 * @param dashboardFilterDTO
	 * @return BookingDTOs
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<RecordsDTO> getAllBooking(DashboardFilterDTO dashboardFilterDTO) {
		if (dashboardFilterDTO.getInvoiceNumberDummy() != null && dashboardFilterDTO.getInvoiceNumberDummy() != "") {
			dashboardFilterDTO.setInvoiceNumber(Integer.parseInt(dashboardFilterDTO.getInvoiceNumberDummy()));
		} else {
			dashboardFilterDTO.setInvoiceNumber(-1);
		}
		List<Integer> bIds = null;
		if (!dashboardFilterDTO.getPayedBy().equals("Show All")
				|| !dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")
				|| !dashboardFilterDTO.getNotConfirmedInHouse().equals("Show All")) {
			if (!dashboardFilterDTO.getPayedBy().equals("Show All")) {
				bIds = bookingRepository.getAllBIdFromInvoice(dashboardFilterDTO.getPayedBy());
				dashboardFilterDTO.setPayedBy(GenrateCommaSeperatedString(bIds));
			} else {
				if (!dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")) {
					bIds = bookingRepository
							.getAllBIdFromConfirmationLocation(dashboardFilterDTO.getNotConfirmedLocations());
					dashboardFilterDTO.setNotConfirmedLocations(GenrateCommaSeperatedString(bIds));
				} else {
					bIds = bookingRepository
							.getAllBIdFromConfirmationInhouse(dashboardFilterDTO.getNotConfirmedInHouse());
					dashboardFilterDTO.setNotConfirmedInHouse(GenrateCommaSeperatedString(bIds));
				}
			}

		}

		List<Object[]> bookingLists = bookingRepository.findAllBooking(dashboardFilterDTO);

		List<RecordsDTO> BookingDTOs = new LinkedList<RecordsDTO>();
		for (Object[] objects : bookingLists) {
			RecordsDTO bookDto = new RecordsDTO(objects);
			BookingDTOs.add(bookDto);
		}
		return BookingDTOs;
	}

	private String GenrateCommaSeperatedString(List<Integer> bIds) {
		StringBuilder commaSepIds = new StringBuilder();
		for (int i = 0; i < bIds.size(); i++) {
			commaSepIds.append(bIds.get(i));
			if (i != bIds.size() - 1) {
				commaSepIds.append(", ");
			}
		}
		return commaSepIds.toString();
	}

	/**
	 * Method for find values of booking by id
	 * 
	 * @param bId
	 * @return recordsDTOs
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<RecordsDTO> findValuesOfBookingByBId(Integer bId) {
		List<Object[]> bookingLists = bookingRepository
				.findBookingConfirmationTrLocationCouriertrackingSalesPersonByBId(bId);

		List<RecordsDTO> recordsDTOs = new LinkedList<RecordsDTO>();
		for (Object[] objects : bookingLists) {
			RecordsDTO recordsDto = new RecordsDTO(objects, true);
			recordsDTOs.add(recordsDto);
		}
		return recordsDTOs;
	}

	/**
	 * Method for find all training dates by year
	 * 
	 * @param dashboardDTO
	 * @return bookingDTOs
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BookingDTO> findAllTrainingDateByYear() {
		List<Date> dateYearLists = bookingRepository.findAllTrainingDateByYear();

		List<BookingDTO> bookingDTOs = new LinkedList<BookingDTO>();
		for (Date objects : dateYearLists) {
			BookingDTO bookingDto = new BookingDTO();
			bookingDto.setTrDate(objects.toString());
			bookingDTOs.add(bookingDto);
		}
		return bookingDTOs;
	}

	/**
	 * Method for count of all booking
	 * 
	 * @param dashboardFilterDTO
	 * @return BookingDTOs
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object getCountOfAllBooking(DashboardFilterDTO dashboardFilterDTO) {
		if (dashboardFilterDTO.getInvoiceNumberDummy() != null && dashboardFilterDTO.getInvoiceNumberDummy() != "") {
			dashboardFilterDTO.setInvoiceNumber(Integer.parseInt(dashboardFilterDTO.getInvoiceNumberDummy()));
		} else {
			dashboardFilterDTO.setInvoiceNumber(-1);
		}
		List<Integer> bIds = null;
		if (!dashboardFilterDTO.getPayedBy().equals("Show All")
				|| !dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")
				|| !dashboardFilterDTO.getNotConfirmedInHouse().equals("Show All")) {
			if (!dashboardFilterDTO.getPayedBy().equals("Show All")) {
				bIds = bookingRepository.getAllBIdFromInvoice(dashboardFilterDTO.getPayedBy());
				dashboardFilterDTO.setPayedBy(GenrateCommaSeperatedString(bIds));
			} else {
				if (!dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")) {
					bIds = bookingRepository
							.getAllBIdFromConfirmationLocation(dashboardFilterDTO.getNotConfirmedLocations());
					dashboardFilterDTO.setNotConfirmedLocations(GenrateCommaSeperatedString(bIds));
				} else {
					bIds = bookingRepository
							.getAllBIdFromConfirmationInhouse(dashboardFilterDTO.getNotConfirmedInHouse());
					dashboardFilterDTO.setNotConfirmedInHouse(GenrateCommaSeperatedString(bIds));
				}
			}

		}
		Object bookingLists = bookingRepository.findCountOfAllBooking(dashboardFilterDTO);
		return bookingLists;
	}

	public List<BookingDTO> btnBookingAndEnquiryListService(String date) {

		System.out.println("booking trDate... " + date);
		List<Object[]> bookingList = bookingRepository.btnBookingAndEnquiryListRepo(date);
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		if(bookingList!= null) {
			
			for (Object[] objects : bookingList) {
				BookingDTO bookingDTO = new BookingDTO(objects, true);
				bookingDTOs.add(bookingDTO);	
		
			}
		
		}
		System.out.println("booking.getfName ... " + date);
		return bookingDTOs;
	}
}