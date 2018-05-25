package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.pojo.ReportingDTO;
import com.crm.repository.IReportingRepository;

@Repository
public class ReportingRepositoryImpl implements IReportingRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for find all bookings.
	 * 
	 * @param dashboardFilterDTO
	 * @return the bookingLists
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findAllReporting() {

		/*
		 * String queryStartPart = "Select 'RaiseOnline' `Subject`,  ";
		 * 
		 * String queryEndPart = ")AS Booking ";
		 * 
		 * queryStartPart = queryStartPart + "Sum(if (PayReceiveDate between '" +
		 * startDate.ToString("yyyy-MM-dd") + "' and '" + endDate.ToString("yyyy-MM-dd")
		 * + "',Total-vatamount,''))`" + labels[i] + "`,";
		 */

		/*
		 * String sqlQuery =
		 * "SELECT 'RaiseOnline' `Subject`,  SUM(IF (MONTH(TrDate)=4 AND YEAR(TrDate)=2018,Total-vatamount,''))`04-18`,"
		 * +
		 * "SUM(IF (MONTH(TrDate)=3 AND YEAR(TrDate)=2018,Total-vatamount,''))`03-18`,SUM(IF (MONTH(TrDate)=2 AND YEAR(TrDate)=2018,Total-vatamount,''))`02-18` "
		 * +
		 * "FROM (SELECT Training.TName, Booking.TrDate,TrLocation.TrLocation, Booking.STATUS,Invoice.PayReceiveDate,Invoice.Total,Invoice.vatamount,"
		 * +
		 * "Invoice.chkvat,Invoice.LT, Invoice.Date  FROM Booking LEFT JOIN Training ON Booking.Training = Training.TId LEFT JOIN "
		 * +
		 * "TrLocation ON Booking.TrLocation = TrLocation.TrLId LEFT JOIN Invoice ON Invoice.BId = Booking.BId LEFT JOIN Confirmation ON "
		 * +
		 * "Confirmation.BId = Booking.BId LEFT JOIN InvNotesTab ON InvNotesTab.BId = Booking.BId  WHERE Booking.Status='Booking' "
		 * +
		 * "GROUP BY Booking.BId, Booking.Title, Booking.FName, Booking.ScName, Booking.Training, Training.TName, Booking.TrDate, Booking.TrLocation,"
		 * +
		 * "TrLocation.TrLocation, Booking.STATUS, Booking.DelegNum, Booking.Tel, Booking.Email, Booking.Notes, Booking.2ndDeleg, Booking.3rdDeleg, Booking.4thDeleg, "
		 * +
		 * "Booking.InvEmail, Booking.2ndDelegEmail, Booking.3rdDelegEmail, Booking.4thDelegEmail, Booking.SubmitDateTime, Booking.CallBackTime, "
		 * +
		 * "Booking.StartTime, Booking.EndTime, Booking.FlCost, Booking.TrainGoCost, Booking.TaxiCost, Booking.HotelCost, Invoice.BId, Invoice.Id, InvNotesTab.BId,"
		 * +
		 * "Invoice.VatAmount,Invoice.chkvat,Invoice.LT, Invoice.additionaldelegates, InvNotesTab.InNotes, Invoice.1stDelegFee, Invoice.2ndDelegFee, Invoice.3rdDelegFee, "
		 * +
		 * "Invoice.Discount, Invoice.SchAddr, Invoice.Website, Invoice.Total, Invoice.4thDelegFee, Invoice.Venue, Invoice.Date, Invoice.PoNo, Invoice.TrainCost, "
		 * +
		 * "Invoice.TravelCost, Invoice.SoftCost, Invoice.InvSentDate, Invoice.PayReceiveDate, Invoice.PayReceiveBy, Invoice.AdditionalNotes, Invoice.InvSentToSchFinance, "
		 * +
		 * "Invoice.BACSRefNo, Invoice.ReconsWithBank, Invoice.CheckNo, Invoice.AccNo, Invoice.SortCode, Invoice.ChequeDate, Invoice.ChecqueDepDate, Invoice.BankReceiveDate, "
		 * +
		 * "Invoice.RaisedInvInPayPal, Invoice.DateOfTransfToBank, Confirmation.BId, Confirmation.SchoolGenRol, Confirmation.SchConfirmDate, Confirmation.TrainerConfirmed, "
		 * +
		 * "Confirmation.RolRepRecieved, Confirmation.RepPrinted, Confirmation.RepPosted, Confirmation.PresentPrep, Confirmation.PresentOnlineDrive, Confirmation.OneDayRemind, "
		 * +
		 * "Confirmation.OneWeekRemind, Confirmation.SchoolAwareReq, Confirmation.UserPasKnowSchool, Confirmation.DelegLaptop, Confirmation.BringRolFFT, Confirmation.RolPecPrint, "
		 * +
		 * "Confirmation.DelegRem1Day, Confirmation.DelegRem1Week, Confirmation.TrainRem1Week, Confirmation.LinkSent, Confirmation.InfoSent, Confirmation.InterestedAsset, "
		 * +
		 * "Confirmation.CallbackAsset, Confirmation.AssetNotes ORDER BY Booking.BId)AS Booking"
		 * ;
		 */

		/*
		 * String sqlQuery =
		 * "SELECT 'RaiseOnline' 'Subject', SUM(IF (MONTH(TrDate)=4 AND YEAR(TrDate)=2017,Total-vatamount,'')) '04-17',SUM(IF (MONTH(TrDate)=3 AND YEAR(TrDate)=2017,Total-vatamount,''))'03-17',"
		 * +
		 * "SUM(IF (MONTH(TrDate)=2 AND YEAR(TrDate)=2017,Total-vatamount,''))'02-17' FROM (SELECT Booking.TrDate, "
		 * +
		 * "invoice.Total,invoice.vatamount,invoice.chkvat,invoice.LT, invoice.Date  FROM booking LEFT JOIN training ON booking.training = training.TId LEFT JOIN trlocation ON "
		 * +
		 * "booking.trlocation = trlocation.TrLId LEFT JOIN invoice ON invoice.BId = booking.BId LEFT JOIN confirmation ON confirmation.BId = booking.BId LEFT JOIN invnotestab ON invnotestab.BId = booking.BId "
		 * +
		 * "WHERE booking.Status='Booking' AND YEAR(booking.TrDate)=2017 AND ( MONTH(booking.TrDate)=4 OR MONTH(booking.TrDate)=3 OR MONTH(booking.TrDate)=2) "
		 * +
		 * "GROUP BY booking.BId,booking.training, training.TName, booking.TrDate, Booking.trlocation,trlocation.trlocation, "
		 * +
		 * "booking.STATUS, booking.FlCost, booking.TrainGoCost, booking.TaxiCost, booking.HotelCost, invoice.BId, "
		 * +
		 * "invoice.Id, invnotestab.BId,invoice.VatAmount,invoice.chkvat,invoice.LT,invoice.Discount, invoice.Total, invoice.Date,  invoice.TrainCost, invoice.TravelCost, invoice.SoftCost, "
		 * +
		 * "invoice.InvSentDate, invoice.PayReceiveDate, invoice.ReconsWithBank, invoice.CheckNo, "
		 * +
		 * "invoice.AccNo, invoice.SortCode, invoice.ChequeDate, invoice.ChecqueDepDate, invoice.BankReceiveDate, invoice.RaisedInvInPayPal, invoice.DateOfTransfToBank, confirmation.BId "
		 * + "ORDER BY booking.BId)AS booking";
		 */

		String sqlQuery = "SELECT 'RaiseOnline' 'Subject', SUM(IF (MONTH(TrDate)=4 AND YEAR(TrDate)=2017,Total-vatamount,'')) '04-17',SUM(IF (MONTH(TrDate)=3 AND YEAR(TrDate)=2017,Total-vatamount,''))'03-17',"
				+ "SUM(IF (MONTH(TrDate)=2 AND YEAR(TrDate)=2017,Total-vatamount,''))'02-17' FROM (SELECT Booking.TrDate, "
				+ "invoice.Total,invoice.vatamount,invoice.chkvat,invoice.LT, invoice.Date  FROM booking LEFT JOIN training ON booking.training = training.TId LEFT JOIN trlocation ON "
				+ "booking.trlocation = trlocation.TrLId LEFT JOIN invoice ON invoice.BId = booking.BId LEFT JOIN confirmation ON confirmation.BId = booking.BId LEFT JOIN invnotestab ON invnotestab.BId = booking.BId "
				+ "WHERE booking.Status='Booking' AND YEAR(booking.TrDate)=2017 AND ( MONTH(booking.TrDate)=4 OR MONTH(booking.TrDate)=3 OR MONTH(booking.TrDate)=2) "
				+ "GROUP BY booking.BId,booking.training, training.TName, booking.TrDate, Booking.trlocation,trlocation.trlocation, "
				+ "booking.STATUS, booking.FlCost, booking.TrainGoCost, booking.TaxiCost, booking.HotelCost, invoice.BId, "
				+ "invoice.Id, invnotestab.BId,invoice.VatAmount,invoice.chkvat,invoice.LT,invoice.Discount, invoice.Total, invoice.Date,  invoice.TrainCost, invoice.TravelCost, invoice.SoftCost, "
				+ "invoice.InvSentDate, invoice.PayReceiveDate, invoice.ReconsWithBank, invoice.CheckNo, "
				+ "invoice.AccNo, invoice.SortCode, invoice.ChequeDate, invoice.ChecqueDepDate, invoice.BankReceiveDate, invoice.RaisedInvInPayPal, invoice.DateOfTransfToBank, confirmation.BId "
				+ "ORDER BY booking.BId)AS booking";

		Query query = em.createNativeQuery(sqlQuery);

		List<Object[]> bookingLists = query.getResultList();

		return bookingLists;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> btnRevenueWithoutVatClick(ReportingDTO reportingDTO, String queryStartPart,
			String queryStartPartMeetingRoom, String queryStartPartCourse) {
		String meetingRoomQuery = "Select meetingroomhiredate,invoicemeetingroom.total,invoicemeetingroom.vatamount,invoicemeetingroom.payreceivedate from "
				+ "meetingroom left outer join invoicemeetingroom on meetingroom.id=invoicemeetingroom.meetingroomid";
		String courseQuery = "Select coursesmaster.t_date,coursesmaster_invoice.vatamount,coursesmaster_invoice.total,"
				+ "coursesmaster_invoice.payreceivedate from coursesmaster left outer join coursesmaster_invoice on"
				+ " coursesmaster.id=coursesmaster_invoice.coursesmasterid";
		if (reportingDTO.getUser() != null) {

			System.out.println("rEPO uSER..............." + reportingDTO.getUser());

			meetingRoomQuery = meetingRoomQuery + " where  ifnull(bookinguser,'')='" + reportingDTO.getUser() + "' ";
		}
		queryStartPart = "Select 'RaiseOnline' 'Subject'," + queryStartPart;
		queryStartPartMeetingRoom = "Select  'MeetingRoom' 'Subject'," + queryStartPartMeetingRoom;
		queryStartPartCourse = "Select  'Courses' 'Subject'," + queryStartPartCourse;
		String queryEndPart = ")AS Booking ";

		queryStartPart = queryStartPart.substring(0, queryStartPart.length() - 1) + " From (";
		queryStartPartMeetingRoom = queryStartPartMeetingRoom.substring(0, queryStartPartMeetingRoom.length() - 1)
				+ " From (";
		queryStartPartCourse = queryStartPartCourse.substring(0, queryStartPartCourse.length() - 1) + " From (";
		String query = queryStartPart + getQueryString(reportingDTO) + queryEndPart;
		String queryMeetingRoom = queryStartPartMeetingRoom + meetingRoomQuery + queryEndPart;
		String queryCourse = queryStartPartCourse + courseQuery + queryEndPart;
		String sqlQuery = query + " Union All " + queryMeetingRoom + " Union All " + queryCourse;
		Query query1 = em.createNativeQuery(sqlQuery);

		List<Object[]> meetingRoomList = query1.getResultList();
		System.out.println(meetingRoomList);
		return meetingRoomList;

	}

	public String getQueryString(ReportingDTO reportingDTO) {
		String query = "SELECT training.TName, booking.TrDate,trlocation.trlocation, booking.STATUS,invoice.PayReceiveDate,"
				+ "invoice.Total,invoice.vatamount,invoice.chkvat,invoice.LT, invoice.Date  FROM booking "
				+ "LEFT JOIN training ON booking.training = training.TId LEFT JOIN trlocation ON booking.trlocation = trlocation.TrLId LEFT JOIN invoice ON invoice.BId = booking.BId "
				+ "LEFT JOIN confirmation ON confirmation.BId = booking.BId LEFT JOIN invnotestab ON invnotestab.BId = booking.BId "
				+ " where booking.Status='" + reportingDTO.getStatus() + "'";
		if (reportingDTO.getUser() != null) {
			query = query + " And ifnull(bookinguser,'')='" + reportingDTO.getUser() + "' ";
		}
		if (reportingDTO.getPayment() == "Not Received") {
			query = query + " And ifnull(invoice.PayReceiveBy,'')='' ";
		}
		query = query
				+ "GROUP BY booking.BId,booking.training, training.TName, booking.TrDate, Booking.trlocation,trlocation.trlocation, "
				+ "booking.STATUS, booking.FlCost, booking.TrainGoCost, booking.TaxiCost, booking.HotelCost, invoice.BId, "
				+ "invoice.Id, invnotestab.BId,invoice.VatAmount,invoice.chkvat,invoice.LT,invoice.Discount, invoice.Total, invoice.Date,  invoice.TrainCost, invoice.TravelCost, invoice.SoftCost, "
				+ "invoice.InvSentDate, invoice.PayReceiveDate, invoice.ReconsWithBank, invoice.CheckNo, "
				+ "invoice.AccNo, invoice.SortCode, invoice.ChequeDate, invoice.ChecqueDepDate, invoice.BankReceiveDate, invoice.RaisedInvInPayPal, invoice.DateOfTransfToBank, confirmation.BId "
				+ "ORDER BY booking.BId";
		return query;
	}

	public List<Object[]> btnRevenueClick(ReportingDTO reportingDTO, String queryStartPart,
			String queryStartPartMeetingRoom, String queryStartPartCourse) {
		System.out.println("Inside btnRevenueClick function in repository");
		String meetingRoomQuery = "Select meetingroomhiredate,invoicemeetingroom.total,invoicemeetingroom.vatamount,invoicemeetingroom.payreceivedate from meetingroom left outer join invoicemeetingroom on meetingroom.id=invoicemeetingroom.meetingroomid";
		String courseQuery = "Select coursesmaster.t_date,coursesmaster_invoice.vatamount,coursesmaster_invoice.total,coursesmaster_invoice.payreceivedate from coursesmaster left outer join coursesmaster_invoice on coursesmaster.id=coursesmaster_invoice.coursesmasterid";
		if (reportingDTO.getUser() != null) {
			meetingRoomQuery = meetingRoomQuery + " where  ifnull(bookinguser,'')='" + reportingDTO.getUser() + "' ";
		}
		queryStartPart = "Select 'RaiseOnline' 'Subject',  " + queryStartPart;
		queryStartPartMeetingRoom = "Select  'MeetingRoom' 'Subject'," + queryStartPartMeetingRoom;
		queryStartPartCourse = "Select  'Courses' 'Subject', " + queryStartPartCourse;
		String queryEndPart = ")AS booking ";

		queryStartPart = queryStartPart.substring(0, queryStartPart.length() - 1) + " From (";
		queryStartPartMeetingRoom = queryStartPartMeetingRoom.substring(0, queryStartPartMeetingRoom.length() - 1)
				+ " From (";
		queryStartPartCourse = queryStartPartCourse.substring(0, queryStartPartCourse.length() - 1) + " From (";
		String query = queryStartPart + getQueryString(reportingDTO) + queryEndPart;
		String queryMeetingRoom = queryStartPartMeetingRoom + meetingRoomQuery + queryEndPart;
		String queryCourse = queryStartPartCourse + courseQuery + queryEndPart;
		query = query + " Union All " + queryMeetingRoom + " Union All " + queryCourse;
		Query query1 = em.createNativeQuery(query);

		List<Object[]> list = query1.getResultList();
		System.out.println(list);
		return list;
	}

	public List<Object[]> btnLocationsClick(ReportingDTO reportingDTO, String queryStartPart) {

		queryStartPart = "Select TrLocation," + queryStartPart;
		String queryEndPart = ")AS Booking Group By TrLocation Order By 1";
		queryStartPart = queryStartPart.substring(0, queryStartPart.length() - 1) + " From (";
		String query = queryStartPart + getQueryString(reportingDTO) + queryEndPart;
		Query query1 = em.createNativeQuery(query);

		List<Object[]> list = query1.getResultList();
		System.out.println(list);
		return list;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> btnOutstandingClick(ReportingDTO reportingDTO, String queryStartPart) {

		queryStartPart = "Select TName,";
		String queryEndPart = ")AS Booking Group By TName Order By 1";
		queryStartPart = queryStartPart.substring(0, queryStartPart.length() - 1) + " From (";
		String query = queryStartPart + getQueryString(reportingDTO) + queryEndPart;
		Query query1 = em.createNativeQuery(query);

		List<Object[]> list = query1.getResultList();
		System.out.println(list);
		return list;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> btnVatReportClick(ReportingDTO reportingDTO, String queryStartPart) {
		queryStartPart = "Select 'VAT' `VAT TYPE`,";
		String queryEndPart = ")AS Booking where ifnull(LT,0)=0 ";
		queryStartPart = queryStartPart.substring(0, queryStartPart.length() - 1) + " From (";
		String query = queryStartPart + getQueryString(reportingDTO) + queryEndPart;
		queryStartPart = "Select 'VAT LT' `VAT TYPE`,";
		queryEndPart = ")AS Booking where ifnull(LT,0)=1 Order By 1";

		Query query1 = em.createNativeQuery(query);

		List<Object[]> list = query1.getResultList();
		System.out.println(list);
		return list;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> btnCoursesClickRepository(ReportingDTO reportingDTO, String queryStartPart) {
		System.out.println("queryStartPart ... " + queryStartPart);

		System.out.println("btnCoursesClickRepository.......");

		String queryEndPart = ")AS Booking Group By TName Order By 1";

		queryStartPart = queryStartPart.substring(0, queryStartPart.length() - 1) + " From (";
		String addQuery = queryStartPart + getQueryString(reportingDTO) + queryEndPart;

		// BindGrid(query, false);

		System.out.println("addQuery ........ " + addQuery);

		Query query = em.createNativeQuery(addQuery);

		List<Object[]> courseLists = query.getResultList();
		System.out.println("courseLists.size() ..... " + courseLists.size());
		return courseLists;
	}

}
