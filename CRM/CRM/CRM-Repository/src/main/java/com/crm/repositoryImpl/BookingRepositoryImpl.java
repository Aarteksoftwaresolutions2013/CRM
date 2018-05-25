package com.crm.repositoryImpl;

import static com.crm.utils.DateUtils.convertMonthToMonthEndDate;
import static com.crm.utils.DateUtils.convertMonthToMonthStartDate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Booking;
import com.crm.pojo.CalendarDTO;
import com.crm.pojo.DashboardFilterDTO;
import com.crm.repository.IBookingRepository;

@Repository
public class BookingRepositoryImpl implements IBookingRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method for save Booking.
	 * 
	 * @param booking
	 * @return void
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveBooking(Booking booking) {
		em.persist(booking);
	}

	/**
	 * Method for update Booking.
	 * 
	 * @param booking
	 * @return Booking
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Booking updateBooking(Booking booking) {
		return em.merge(booking);
	}

	/**
	 * Method for finding Max Id.
	 * 
	 * @return List<Integer>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> findMaxId() {
		return em.createQuery("select max(bk.bId) from booking bk").setMaxResults(1).getResultList();
	}

	/**
	 * Method for finding Booking by id.
	 * 
	 * @param articleId
	 * @return Booking
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Booking findBookingById(int articleId) {
		return em.find(Booking.class, articleId);
	}

	/**
	 * Method for find all bookings.
	 *
	 * @param dashboardFilterDTO
	 * @return the bookingLists
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findAllBooking(DashboardFilterDTO dashboardFilterDTO) {

		String setLimit = "limit " + ((dashboardFilterDTO.getPageNo() - 1) * dashboardFilterDTO.getNoOfItems()) + ","
				+ dashboardFilterDTO.getNoOfItems() + "";

		String sqlQuery = "select booking.BId,booking.calledDate Called, booking.calleduser Caller, ";
		sqlQuery += "booking.callerNotes, booking.Title, booking.FName, booking.ScName, ";
		sqlQuery += "booking.bookingPhase, training.TName, booking.TrDate, ";
		sqlQuery += "booking.SubmitDateTime, trlocation.trlocation trTrl, booking.STATUS, ";
		sqlQuery += "booking.DelegNum, booking.Tel, booking.Email,booking.Notes, booking.2ndDeleg, booking.3rdDeleg, booking.4thDeleg, booking.InvEmail, "
				+ "booking.2ndDelegEmail, booking.3rdDelegEmail, booking.4thDelegEmail,  booking.CallBackTime,  booking.StartTime, "
				+ "booking.EndTime, booking.FlCost, booking.TrainGoCost, booking.TaxiCost,  "
				+ "booking.HotelCost,booking.Consultant,  invoice.BId invBid,invoice.VatAmount,invoice.chkvat,  invoice.LT,invoice.additionaldelegates, "
				+ "invoice.Id,  invoice.1stDelegFee, invoice.2ndDelegFee, invoice.3rdDelegFee, invoice.Discount,invoice.cancsurcharge, invoice.SchAddr, "
				+ "invoice.Website, invoice.Total, invoice.4thDelegFee, invoice.Venue, invoice.Date,  invoice.PoNo, invoice.TrainCost, invoice.TravelCost, "
				+ "invoice.SoftCost, invoice.InvSentDate, invoice.PayReceiveDate, invoice.PayReceiveBy, invoice.AdditionalNotes, invoice.InvSentToSchFinance, "
				+ "invoice.BACSRefNo, invoice.ReconsWithBank, invoice.PaidByBacs, invoice.LTBacs, invoice.CheckNo, invoice.AccNo, invoice.SortCode, "
				+ "invoice.PaidByCheque, invoice.LTCheque, invoice.ChequeDate, invoice.ChecqueDepDate, invoice.BankReceiveDate, invoice.RaisedInvInPayPal, "
				+ "invoice.DateOfTransfToBank,invoice.paymenttype, booking.hear,booking.hear_other,booking.typeOfCall, salesperson.SName salesperson,booking.demoDate ";
		sqlQuery += "from";

		// If cond
		if (dashboardFilterDTO.getStatus() == 0 || !(dashboardFilterDTO.getInvoiceNumber() > 0)) {
			sqlQuery += "(select booking.* from booking booking where ";

			if (!dashboardFilterDTO.getTelephone().equals("")) {
				sqlQuery += "booking.Tel like '" + dashboardFilterDTO.getTelephone() + "%' and ";
				// setLimit = "";
			}

			if (!dashboardFilterDTO.getEmail().equals("")) {
				sqlQuery += "booking.Email like'%" + dashboardFilterDTO.getEmail() + "%' or ";
				sqlQuery += "booking.InvEmail like'%" + dashboardFilterDTO.getEmail() + "%' or ";
				sqlQuery += "booking.2ndDelegEmail like'%" + dashboardFilterDTO.getEmail() + "%' or ";
				sqlQuery += "booking.3rdDelegEmail like'%" + dashboardFilterDTO.getEmail() + "%' or ";
				sqlQuery += "booking.4thDelegEmail like'%" + dashboardFilterDTO.getEmail() + "%' and ";

			}

			if (!dashboardFilterDTO.getFullName().equals("")) {
				sqlQuery += "booking.FName like " + dashboardFilterDTO.getFullName() + " or ";
				sqlQuery += "booking.2ndDeleg like " + dashboardFilterDTO.getFullName() + " or ";
				sqlQuery += "booking.3rdDeleg like " + dashboardFilterDTO.getFullName() + " or ";
				sqlQuery += "booking.4thDeleg like " + dashboardFilterDTO.getFullName() + " or ";
				sqlQuery += "booking.BId IN (select bid from delegatelist where name like "
						+ dashboardFilterDTO.getFullName() + ") and ";
			}
			if (!dashboardFilterDTO.getSchoolNames().equals("")) {
				sqlQuery += "booking.ScName='" + dashboardFilterDTO.getSchoolNames() + "' and ";

			}

			// Need data In Integer values
			if (!dashboardFilterDTO.getTrainingLocation().equals(-1)) {
				sqlQuery += "booking.trlocation='" + dashboardFilterDTO.getTrainingLocation() + "' and ";
				// setLimit = "";
			}

			if (!dashboardFilterDTO.getCourse().equals(-1)) {

				sqlQuery += "booking.training='" + dashboardFilterDTO.getCourse() + "' and ";
				// setLimit = "";
			}
			if (!dashboardFilterDTO.getPayedBy().equals("Show All")) {
				System.out.println(dashboardFilterDTO.getPayedBy());
				sqlQuery += "booking.BId IN(" + dashboardFilterDTO.getPayedBy() + ") and ";

				// setLimit = "";
			}

			if (!dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")) {
				sqlQuery += "booking.BId IN(" + dashboardFilterDTO.getNotConfirmedLocations() + ") and ";
			}

			if ((!dashboardFilterDTO.getNotConfirmedInHouse().equals("Show All"))
					&& (!dashboardFilterDTO.getNotConfirmedInHouse().equals("CourierTrackingid"))) {
				sqlQuery += "booking.BId IN(" + dashboardFilterDTO.getNotConfirmedInHouse() + ") and ";
			}

			if (!dashboardFilterDTO.getSoftware().equals("Show All")) {

				if (dashboardFilterDTO.getSoftware().contains(",")) {
					String softIds[] = dashboardFilterDTO.getSoftware().split(",");
					for (int index = 0; index <= softIds.length - 1; index++) {
						sqlQuery += "booking.training='" + softIds[index] + "'";
						if (index < (softIds.length - 1)) {
							sqlQuery += " or ";
						}
					}
					sqlQuery += " and ";
				} else {
					sqlQuery += "booking.training='" + dashboardFilterDTO.getSoftware() + "' and ";
				}
			}

			if (!dashboardFilterDTO.getTrainingBookingEnquiry().equals("Show All")) {
				sqlQuery += "booking.STATUS='" + dashboardFilterDTO.getTrainingBookingEnquiry() + "' and ";
				// setLimit = "";
			}

			if (!dashboardFilterDTO.getDates().equals("Show All")) {
				sqlQuery += "booking.TrDate = '" + dashboardFilterDTO.getDates() + "' and ";
				// setLimit = "";
			} else if (!dashboardFilterDTO.getYear().equals("Show All")) {
				sqlQuery += "booking.TrDate > '" + dashboardFilterDTO.getYear() + "-01-01' AND booking.TrDate < ' and "
						+ dashboardFilterDTO.getYear() + "-12-31'";
				// setLimit = "";
			} else if (!dashboardFilterDTO.getMonths().equals("Show All")) {
				sqlQuery += "booking.TrDate >= '" + convertMonthToMonthStartDate(dashboardFilterDTO.getMonths())
						+ "' and booking.TrDate <= '" + convertMonthToMonthEndDate(dashboardFilterDTO.getMonths())
						+ "' and ";
				// setLimit = "";
			}
			/*
			 * if (!dashboardFilterDTO.getCourse().equals(-1) ||
			 * !dashboardFilterDTO.getSoftware().equals("Show All") ||
			 * !dashboardFilterDTO.getPayedBy().equals("Show All")) {
			 */
			sqlQuery += "order by booking.BId desc) booking ";
			sqlQuery = sqlQuery.replace("where order by", "order by");
			sqlQuery = sqlQuery.replace("and order by", "order by");
			/*
			 * } else { sqlQuery += "booking.SId > 0 ORDER BY booking.BId DESC) booking "; }
			 */

		} else {
			sqlQuery += " booking booking ";
		}

		sqlQuery += " left join training training on booking.training = training.TId ";

		if ((!dashboardFilterDTO.getNotConfirmedInHouse().equals("Show All"))
				&& (!dashboardFilterDTO.getNotConfirmedInHouse().equals("CourierTrackingid"))
				|| (!dashboardFilterDTO.getNotConfirmedLocations().equals("Show All"))) {
			sqlQuery += "left join confirmation on booking.Bid = confirmation.Bid ";
			// setLimit = "";
		}
		/*
		 * if ((!dashboardFilterDTO.getNotConfirmedInHouse().equals("Show All")) &&
		 * (!dashboardFilterDTO.getNotConfirmedInHouse().equals( "CourierTrackingid")))
		 * { sqlQuery += " AND IFNULL(confirmation." +
		 * dashboardFilterDTO.getNotConfirmedInHouse() + ",0)=0 ";
		 * 
		 * }
		 */
		/*
		 * if (!dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")) {
		 * sqlQuery += " AND IFNULL(confirmation." +
		 * dashboardFilterDTO.getNotConfirmedLocations() + ",0)=0 "; }
		 */

		sqlQuery += "left join trlocation on booking.trlocation = trlocation.TrLId ";
		sqlQuery += "left join invoice on booking.BId =invoice.BId ";

		sqlQuery += "left join couriertracking on booking.BId = couriertracking.bId ";
		sqlQuery += "left join salesperson on booking.SId = salesperson.SId AND booking.SId > 0";
		if (dashboardFilterDTO.getInvoiceNumber() > 0) {
			sqlQuery += " where invoice.Id='" + dashboardFilterDTO.getInvoiceNumber() + "' order by booking.BId desc ";
		}

		sqlQuery += " " + setLimit + "";

		Query query = em.createNativeQuery(sqlQuery);

		List<Object[]> bookingLists = query.getResultList();
		return bookingLists;
	}

	/**
	 * Method for finding Booking, confirmation, training location, tracking, sales
	 * person by Id.
	 * 
	 * @param bId
	 * @return bookingLists
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findBookingConfirmationTrLocationCouriertrackingSalesPersonByBId(int bId) {
		/*
		 * Query query = em.createNativeQuery(
		 * "SELECT bk.BId bookId, bk.calledDate Called,bk.calleduser Caller,bk.callerNotes,bk.Title, "
		 * +
		 * "bk.FName, bk.ScName, bk.BookingPhase, bk.Training, Training.TName, bk.TrDate,"
		 * +
		 * "bk.SubmitDateTime, bk.TrLocation bookTrl, trl.TrLocation trTrl, bk.STATUS,bk.DelegNum, bk.Tel,"
		 * +
		 * "bk.Email,bk.Notes, bk.2ndDeleg, bk.3rdDeleg, bk.4thDeleg, bk.InvEmail,bk.2ndDelegEmail,"
		 * +
		 * "bk.3rdDelegEmail, bk.4thDelegEmail,  bk.CallBackTime,bk.StartTime, bk.EndTime, bk.FlCost,"
		 * +
		 * "bk.TrainGoCost, bk.TaxiCost,bk.HotelCost,bk.Consultant, inv.BId,inv.VatAmount,inv.chkvat,"
		 * +
		 * "inv.LT,inv.additionaldelegates, inv.Id,inv.1stDelegFee, inv.2ndDelegFee, inv.3rdDelegFee, "
		 * +
		 * "inv.Discount,inv.cancsurcharge, inv.SchAddr, inv.Website, inv.Total, inv.4thDelegFee, inv.Venue, "
		 * +
		 * "inv.Date,inv.PoNo, inv.TrainCost, inv.TravelCost, inv.SoftCost, inv.InvSentDate, inv.PayReceiveDate,"
		 * +
		 * "inv.PayReceiveBy, inv.AdditionalNotes, inv.InvSentToSchFinance, inv.BACSRefNo,inv.ReconsWithBank,"
		 * +
		 * "inv.PaidByBacs, inv.LTBacs, inv.CheckNo, inv.AccNo, inv.SortCode, inv.PaidByCheque, inv.LTCheque,"
		 * +
		 * "inv.ChequeDate,inv.ChecqueDepDate, inv.BankReceiveDate, inv.RaisedInvInPayPal, inv.DateOfTransfToBank,"
		 * +
		 * "inv.paymenttype,bk.hear,bk.hear_other ,bk.typeOfCall, SalesPerson.SName sname, bk.demoDate, bk.SId, bk.SESSION "
		 * +
		 * "FROM booking bk LEFT JOIN training Training ON bk.Training = Training.TId "
		 * + "LEFT JOIN confirmation ON bk.Bid = confirmation.Bid " +
		 * "LEFT JOIN trLocation trl ON bk.TrLocation = trl.TrLId " +
		 * "LEFT JOIN invoice inv ON bk.BId =inv.BId " +
		 * "LEFT JOIN couriertracking ON bk.BId = couriertracking.bId " +
		 * "LEFT JOIN salesPerson SalesPerson ON bk.SId = SalesPerson.SId WHERE bk.BId=?1"
		 * );
		 */
		Query query = em.createNativeQuery(
				"select bk.BId bookId, bk.calledDate Called,bk.calleduser Caller,bk.callerNotes,bk.Title, bk.FName, bk.ScName, bk.bookingPhase, "
						+ "bk.training, training.TName, bk.TrDate,bk.SubmitDateTime, bk.trlocation bookTrl, trl.trlocation trTrl, bk.STATUS,bk.DelegNum, "
						+ "bk.Tel,bk.Email,bk.Notes, bk.2ndDeleg, bk.3rdDeleg, bk.4thDeleg, bk.InvEmail,bk.2ndDelegEmail,bk.3rdDelegEmail,"
						+ "bk.4thDelegEmail,  bk.CallBackTime,bk.StartTime, bk.EndTime, bk.FlCost,bk.TrainGoCost, bk.TaxiCost,bk.HotelCost,"
						+ "bk.Consultant,inv.BId invBid,inv.VatAmount,inv.chkvat,inv.LT,inv.additionaldelegates, inv.Id,inv.1stDelegFee, inv.2ndDelegFee, "
						+ "inv.3rdDelegFee, inv.Discount,inv.cancsurcharge, inv.SchAddr, inv.Website, inv.Total, inv.4thDelegFee, inv.Venue, "
						+ "inv.Date,inv.PoNo, inv.TrainCost, inv.TravelCost, inv.SoftCost, inv.InvSentDate, inv.PayReceiveDate,inv.PayReceiveBy, "
						+ "inv.AdditionalNotes, inv.InvSentToSchFinance, inv.BACSRefNo,inv.ReconsWithBank,inv.PaidByBacs, inv.LTBacs, inv.CheckNo, "
						+ "inv.AccNo, inv.SortCode, inv.PaidByCheque, inv.LTCheque,inv.ChequeDate,inv.ChecqueDepDate, inv.BankReceiveDate, "
						+ "inv.RaisedInvInPayPal, inv.DateOfTransfToBank,inv.paymenttype,bk.hear bkHear,bk.hear_other bkhearother,bk.typeOfCall, salesperson.SName sname, "
						+ "bk.demoDate, bk.SId, bk.SESSION , con.BId conBid, con.SchoolGenRol, con.SchConfirmDate, con.TrainerConfirmed,con.TrainerConfirmed2,"
						+ "con.AttendanceConfirmed,con.AttendanceConfirmedby, con.RolRepRecieved, con.RepPrinted, con.RepPosted, con.PresentPrep, "
						+ "con.PresentOnlineDrive, con.OneDayRemind, con.OneWeekRemind, con.SchoolAwareReq, con.UserPasKnowSchool, con.DelegLaptop, "
						+ "con.BringRolFFT, con.RolPecPrint, con.DelegRem1Day, con.DelegRem1Week, con.TrainRem1Week, con.LinkSent,con.InfoSent, "
						+ "con.InterestedAsset, con.CallbackAsset, con.AssetNotes, con.JourneyPlan,con.inhouserequirementconfirmedon, con.inhousenotes, "
						+ "con.traininglocationrequirementconfirmedon, con.traininglocationdelegateconfirmedon, con.softwarelinksenton, "
						+ "con.softwareinfosenton,hear Hear,hear_other hearother,bk.reminder, con.receivedLocation, con.trainingMaterialCreated, "
						+ "con.informationOnSchools, con.hotelHasAProjector, con.hotelHasWifi, con.hotelHasExtensionLeads,con.hotelHasTea,"
						+ "con.hotelHasLunch,con.hotelHasAfterNoonTea,con.price,con.contactsReceived,con.contactsSigned,con.confirmationLocationNotes,"
						+ "con.confirmationLocationtrainingMaterialCreated,con.trainingMaterialSentOn,con.emailHotelToInform,"
						+ "con.confirmationtrainingMaterialReceivedOn,cour.senddate couriertrackingsenddate, cour.id couriertrackingid,"
						+ "cour.trackingid couriertrackingtrackingid,cour.couriercompany couriertrackingcouriercompany,"
						+ "cour.websitelink couriertrackingwebsitelink,cour.deliveryconfirmedby couriertrackingdeliveryconfirmedby,"
						+ "cour.receiveddateandtime couriertrackingreceiveddateandtime,cour.comments couriertrackingcomments, bk.bookinguser, invNote.BId, "
						+ "invNote.InNotes, invNote.expectations from booking bk "
						+ "left join training training ON bk.training = training.TId LEFT JOIN confirmation con ON bk.Bid = con.Bid "
						+ "left join trlocation trl ON bk.trlocation = trl.TrLId left join invoice inv ON bk.BId =inv.BId "
						+ "left join couriertracking cour ON bk.BId = cour.bId "
						+ "left join salesperson salesperson ON bk.SId = salesperson.SId  left join invnotestab invNote ON bk.BId = invNote.BId  "
						+ "where bk.BId= ?1 order by  bk.STATUS, bk.TrDate");

		List<Object[]> bookingLists = query.setParameter(1, bId).getResultList();
		return bookingLists;
	}

	/**
	 * Method for finding Booking, confirmation, training location, tracking,
	 * 
	 * 
	 * @param year
	 * @param month
	 * @return bookingLists
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findBookingByYearAndMonth(CalendarDTO calendarDto) {
		String sqlQuery = "SELECT bk.BId bkId, bk.Title, bk.FName, bk.ScName,CASE WHEN training.TName='Analysis of KS5 Inspection Dashboard' THEN 'KS5' ELSE bk.bookingPhase "
				+ "END , bk.training, training.TName, bk.TrDate, bk.SubmitDateTime,bk.trlocation bookTrl, trloc.trlocation trTrl, bk.STATUS,bk.DelegNum, "
				+ "bk.Tel, bk.Email, bk.Notes, bk.2ndDeleg, bk.3rdDeleg, bk.4thDeleg, bk.InvEmail, bk.2ndDelegEmail, bk.3rdDelegEmail, bk.4thDelegEmail,  "
				+ "bk.CallBackTime, bk.StartTime, bk.EndTime, bk.FlCost, bk.TrainGoCost, bk.TaxiCost, bk.HotelCost,bk.Consultant, invoice.BId invBid ,invoice.VatAmount,"
				+ "invoice.chkvat,invoice.LT,invoice.additionaldelegates, invoice.Id, invnotestab.BId invNoteBid, invnotestab.InNotes,invnotestab.expectations, invoice.1stDelegFee, "
				+ "invoice.2ndDelegFee, invoice.3rdDelegFee, invoice.Discount,invoice.cancsurcharge, invoice.SchAddr, invoice.Website, invoice.Total, invoice.4thDelegFee, "
				+ "invoice.Venue, invoice.Date, invoice.PoNo, invoice.TrainCost, invoice.TravelCost, invoice.SoftCost,invoice.SoftSetup, invoice.InvSentDate, "
				+ "invoice.PayReceiveDate, invoice.PayReceiveBy, invoice.AdditionalNotes, invoice.InvSentToSchFinance, invoice.BACSRefNo, invoice.ReconsWithBank,"
				+ "invoice.PaidByBacs, invoice.LTBacs, invoice.CheckNo, invoice.AccNo, invoice.SortCode, invoice.PaidByCheque, invoice.LTCheque, invoice.ChequeDate, "
				+ "invoice.ChecqueDepDate, invoice.BankReceiveDate, invoice.RaisedInvInPayPal, invoice.DateOfTransfToBank,invoice.paymenttype, confirmation.BId confirmBid, "
				+ "confirmation.SchoolGenRol, confirmation.SchConfirmDate, confirmation.TrainerConfirmed,confirmation.TrainerConfirmed2,confirmation.AttendanceConfirmed,"
				+ "confirmation.AttendanceConfirmedby, confirmation.RolRepRecieved, confirmation.RepPrinted, confirmation.RepPosted, confirmation.PresentPrep,"
				+ "confirmation.PresentOnlineDrive, confirmation.OneDayRemind, confirmation.OneWeekRemind, confirmation.SchoolAwareReq, confirmation.UserPasKnowSchool,"
				+ "confirmation.DelegLaptop, confirmation.BringRolFFT, confirmation.RolPecPrint, confirmation.DelegRem1Day, confirmation.DelegRem1Week, "
				+ "confirmation.TrainRem1Week, confirmation.LinkSent, confirmation.InfoSent, confirmation.InterestedAsset, confirmation.CallbackAsset, "
				+ "confirmation.AssetNotes, confirmation.JourneyPlan,couriertracking.senddate , couriertracking.id ,"
				+ "couriertracking.trackingid ,couriertracking.couriercompany ,couriertracking.websitelink "
				+ ",couriertracking.deliveryconfirmedby ,couriertracking.receiveddateandtime "
				+ ",couriertracking.comments ,confirmation.inhouserequirementconfirmedon, "
				+ "confirmation.inhousenotes, confirmation.traininglocationrequirementconfirmedon, confirmation.traininglocationdelegateconfirmedon,"
				+ "confirmation.softwarelinksenton, confirmation.softwareinfosenton,hear,hear_other,bk.reminder, confirmation.receivedLocation, "
				+ "confirmation.trainingMaterialCreated, confirmation.informationOnSchools, confirmation.hotelHasAProjector, confirmation.hotelHasWifi,"
				+ "confirmation.hotelHasExtensionLeads,confirmation.hotelHasTea,confirmation.hotelHa"
				+ "sLunch,confirmation.hotelHasAfterNoonTea,confirmation.price,"
				+ "confirmation.contactsReceived,confirmation.contactsSigned,confirmation.confirmationLocationNotes,confirmation.confirmationLocationtrainingMaterialCreated,"
				+ "confirmation.trainingMaterialSentOn,confirmation.emailHotelToInform,confirmation.confirmationtrainingMaterialReceivedOn,bk.bookinguser,bk.session "
				+ "	FROM booking bk " + "LEFT JOIN training ON bk.training = training.TId "
				+ "LEFT JOIN trlocation trloc ON bk.trlocation = trloc.TrLId "
				+ "LEFT JOIN invoice ON invoice.BId = bk.BId "
				+ "LEFT JOIN couriertracking ON bk.BID = couriertracking.bid  "
				+ "LEFT JOIN confirmation ON confirmation.BId = bk.BId "
				+ "LEFT JOIN invnotestab ON invnotestab.BId = bk.BId WHERE YEAR(bk.TrDate)=?1 AND MONTH(bk.TrDate)= ?2  AND (IFNULL(bk.SId,0) = 0 "
				+ "OR (IFNULL(bk.SId,0) > 0 AND bk.STATUS = 'Booking')) AND bk.STATUS <> 'Cancelled' ";

		if (!calendarDto.getPhase().equals("Show All")) {
			sqlQuery += "And bk.BookingPhase= '" + calendarDto.getPhase() + "' ";
		}
		if (calendarDto.getConsultant() == null || calendarDto.getConsultant().equals("All")) {
			sqlQuery += " ";
		} else if (calendarDto.getConsultant().equals("Not Allocated")) {
			sqlQuery += "'  And bk.STATUS='Booking'  And bk.Consultant=''";
		} else if (calendarDto.getConsultant().equals("Enquiries")) {
			sqlQuery += " And bk.STATUS ='Enquiry'";
		} else {
			sqlQuery += " And bk.Consultant='" + calendarDto.getConsultant() + "'";
		}
		Query query = em.createNativeQuery(sqlQuery);
		query.setParameter(1, calendarDto.getYear());
		query.setParameter(2, calendarDto.getMonth());
		List<Object[]> bookingLists = query.getResultList();
		return bookingLists;
	}

	/**
	 * Method for finding all training dates by year.
	 * 
	 * @param dashboardDTO
	 * @return List<Date>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Date> findAllTrainingDateByYear() {
		return em.createQuery("select distinct bk.trDate from Booking bk order by bk.trDate desc").getResultList();
	}

	/**
	 * Method for find count of numbers of records in booking.
	 *
	 * @param dashboardFilterDTO
	 * @return List<Object[]>
	 */
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Object findCountOfAllBooking(DashboardFilterDTO dashboardFilterDTO) {

		if (dashboardFilterDTO.getInvoiceNumber() > 0) {
			List resultList = new ArrayList();
			resultList.add(0, 1);
			return resultList.get(0);
		}

		String sqlQuery = "";

		sqlQuery += "select count(*) from booking booking where ";

		if (!dashboardFilterDTO.getTelephone().equals("")) {
			sqlQuery += "booking.Tel like '" + dashboardFilterDTO.getTelephone() + "%' and ";
		}

		if (!dashboardFilterDTO.getEmail().equals("")) {
			sqlQuery += "booking.Email like'%" + dashboardFilterDTO.getEmail() + "%' or ";
			sqlQuery += "booking.InvEmail like'%" + dashboardFilterDTO.getEmail() + "%' or ";
			sqlQuery += "booking.2ndDelegEmail like'%" + dashboardFilterDTO.getEmail() + "%' or ";
			sqlQuery += "booking.3rdDelegEmail like'%" + dashboardFilterDTO.getEmail() + "%' or ";
			sqlQuery += "booking.4thDelegEmail like'%" + dashboardFilterDTO.getEmail() + "%' and ";
		}

		if (!dashboardFilterDTO.getFullName().equals("")) {
			sqlQuery += "booking.FName like " + dashboardFilterDTO.getFullName() + " or ";
			sqlQuery += "booking.2ndDeleg like " + dashboardFilterDTO.getFullName() + " or ";
			sqlQuery += "booking.3rdDeleg like " + dashboardFilterDTO.getFullName() + " or ";
			sqlQuery += "booking.4thDeleg like " + dashboardFilterDTO.getFullName() + " or ";
			sqlQuery += "booking.BId IN (select bid from delegatelist where name like "
					+ dashboardFilterDTO.getFullName() + ") and ";
			/*
			 * sqlQuery += "booking.BId in (Select bid from delegatelist where name like '%"
			 * + dashboardFilterDTO.getFullName() + "%')and ";
			 */
		}
		if (!dashboardFilterDTO.getSchoolNames().equals("")) {
			sqlQuery += "booking.ScName='" + dashboardFilterDTO.getSchoolNames() + "' and ";

		}

		if (!dashboardFilterDTO.getTrainingLocation().equals(-1)) {
			sqlQuery += "booking.trlocation='" + dashboardFilterDTO.getTrainingLocation() + "' and ";
		}

		if (!dashboardFilterDTO.getCourse().equals(-1)) {
			sqlQuery += "booking.Training='" + dashboardFilterDTO.getCourse() + "' and ";
		}
		if (!dashboardFilterDTO.getSoftware().equals("Show All")) {

			if (dashboardFilterDTO.getSoftware().contains(",")) {
				String softIds[] = dashboardFilterDTO.getSoftware().split(",");
				for (int index = 0; index <= softIds.length - 1; index++) {
					sqlQuery += "booking.training='" + softIds[index] + "'";
					if (index < (softIds.length - 1)) {
						sqlQuery += " or ";
					}
				}
				sqlQuery += " and ";
			} else {
				sqlQuery += "booking.training='" + dashboardFilterDTO.getSoftware() + "' and ";
			}
		}

		if (!dashboardFilterDTO.getPayedBy().equals("Show All")) {
			sqlQuery += "booking.BId IN(" + dashboardFilterDTO.getPayedBy() + ") and ";
			// setLimit = "";
		}
		if (!dashboardFilterDTO.getNotConfirmedLocations().equals("Show All")) {
			sqlQuery += "booking.BId IN(" + dashboardFilterDTO.getNotConfirmedLocations() + ") and ";
		}

		if ((!dashboardFilterDTO.getNotConfirmedInHouse().equals("Show All"))
				&& (!dashboardFilterDTO.getNotConfirmedInHouse().equals("CourierTrackingid"))) {
			sqlQuery += "booking.BId IN(" + dashboardFilterDTO.getNotConfirmedInHouse() + ") and ";
		}

		if (!dashboardFilterDTO.getTrainingBookingEnquiry().equals("Show All")) {
			sqlQuery += "booking.STATUS='" + dashboardFilterDTO.getTrainingBookingEnquiry() + "' and ";
		}

		if (!dashboardFilterDTO.getDates().equals("Show All")) {
			sqlQuery += "booking.TrDate = '" + dashboardFilterDTO.getDates() + "' and ";
		} else if (!dashboardFilterDTO.getYear().equals("Show All")) {
			sqlQuery += "booking.TrDate > '" + dashboardFilterDTO.getYear() + "-01-01' AND booking.TrDate < ' and "
					+ dashboardFilterDTO.getYear() + "-12-31'";
		} else if (!dashboardFilterDTO.getMonths().equals("Show All")) {
			sqlQuery += "booking.TrDate >= '" + convertMonthToMonthStartDate(dashboardFilterDTO.getMonths())
					+ "' and booking.TrDate <= '" + convertMonthToMonthEndDate(dashboardFilterDTO.getMonths())
					+ "' and ";
		}
		// sqlQuery += "booking.SId > 0 ORDER BY booking.BId";
		sqlQuery += "order by booking.BId";
		sqlQuery = sqlQuery.replace("and order by", "order by");
		sqlQuery = sqlQuery.replace("where order by", "order by");
		Query query = em.createNativeQuery(sqlQuery);

		List resultList = query.getResultList();
		return resultList.get(0);
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> getAllBIdFromInvoice(String paidByValue) {

		System.out.println("000000" + paidByValue);
		return em.createNativeQuery("SELECT DISTINCT BId FROM invoice WHERE payReceiveBy='" + paidByValue + "'")
				.getResultList();
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> getAllBIdFromConfirmationLocation(String confirmLocValue) {
		return em
				.createNativeQuery(
						"SELECT DISTINCT BId FROM confirmation confirm WHERE confirm." + confirmLocValue + "=" + "'0'")
				.getResultList();
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Integer> getAllBIdFromConfirmationInhouse(String confirmInhouseValue) {
		return em.createNativeQuery(
				"SELECT DISTINCT BId FROM confirmation confirm WHERE confirm." + confirmInhouseValue + "=" + "'0'")
				.getResultList();
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> btnBookingAndEnquiryListRepo(String date) {

		// String trainDate = booking.getTrDate().toString();

		String sqlQuery = "";
		sqlQuery = "SELECT booking.BId bookid ,booking.Title, booking.FName, booking.ScName, booking.bookingPhase, booking.training, training.TName,"
				+ " booking.TrDate,booking.SubmitDateTime, booking.trlocation booktrl, trlocation.trlocation locTr, booking.STATUS,booking.DelegNum,"
				+ " booking.Tel, booking.Email, booking.Notes, booking.2ndDeleg, booking.3rdDeleg, booking.4thDeleg, booking.InvEmail,"
				+ " booking.2ndDelegEmail, booking.3rdDelegEmail, booking.4thDelegEmail, booking.CallBackTime, booking.StartTime, booking.EndTime,"
				+ " booking.FlCost, booking.TrainGoCost, booking.TaxiCost, booking.HotelCost,booking.Consultant, invoice.BId invBid,invoice.VatAmount,"
				+ "invoice.chkvat,invoice.LT,invoice.additionaldelegates, invoice.Id, invnotestab.BId invnoteBid, invnotestab.InNotes,invnotestab.expectations,"
				+ ""
				+ " invoice.1stDelegFee, invoice.2ndDelegFee, invoice.3rdDelegFee, invoice.Discount,invoice.cancsurcharge, invoice.SchAddr, invoice.Website,"
				+ " invoice.Total, invoice.4thDelegFee, invoice.Venue, invoice.Date, invoice.PoNo, invoice.TrainCost, invoice.TravelCost, invoice.SoftCost,"
				+ "invoice.SoftSetup, invoice.InvSentDate, invoice.PayReceiveDate, invoice.PayReceiveBy, invoice.AdditionalNotes, invoice.InvSentToSchFinance,"
				+ " invoice.BACSRefNo, invoice.ReconsWithBank, invoice.PaidByBacs, invoice.LTBacs, invoice.CheckNo, invoice.AccNo, invoice.SortCode, invoice.PaidByCheque,"
				+ " invoice.LTCheque, invoice.ChequeDate, invoice.ChecqueDepDate, invoice.BankReceiveDate, invoice.RaisedInvInPayPal, invoice.DateOfTransfToBank,invoice.paymenttype,"
				+ " confirmation.BId conBid, confirmation.SchoolGenRol, confirmation.SchConfirmDate, confirmation.TrainerConfirmed,confirmation.TrainerConfirmed2,confirmation.AttendanceConfirmed,"
				+ "confirmation.AttendanceConfirmedby, confirmation.RolRepRecieved, confirmation.RepPrinted, confirmation.RepPosted, confirmation.PresentPrep,"
				+ " confirmation.PresentOnlineDrive, confirmation.OneDayRemind, confirmation.OneWeekRemind, confirmation.SchoolAwareReq, confirmation.UserPasKnowSchool, confirmation.DelegLaptop,"
				+ " confirmation.BringRolFFT, confirmation.RolPecPrint, confirmation.DelegRem1Day, confirmation.DelegRem1Week, confirmation.TrainRem1Week, confirmation.LinkSent, "
				+ "confirmation.InfoSent, confirmation.InterestedAsset, confirmation.CallbackAsset, confirmation.AssetNotes, confirmation.JourneyPlan,couriertracking.senddate CourierTrackingsenddate,"
				+ " couriertracking.id CourierTrackingid,couriertracking.trackingid CourierTrackingtrackingid,couriertracking.couriercompany CourierTrackingcouriercompany,"
				+ "couriertracking.websitelink CourierTrackingwebsitelink,couriertracking.deliveryconfirmedby CourierTrackingdeliveryconfirmedby,couriertracking.receiveddateandtime CourierTrackingreceiveddateandtime,"
				+ "couriertracking.comments CourierTrackingcomments,confirmation.inhouserequirementconfirmedon, confirmation.inhousenotes, confirmation.traininglocationrequirementconfirmedon,"
				+ " confirmation.traininglocationdelegateconfirmedon, confirmation.softwarelinksenton, confirmation.softwareinfosenton,hear,hear_other,booking.reminder, confirmation.receivedLocation,"
				+ " confirmation.trainingMaterialCreated, confirmation.informationOnSchools, confirmation.hotelHasAProjector, confirmation.hotelHasWifi, confirmation.hotelHasExtensionLeads,confirmation.hotelHasTea,"
				+ "confirmation.hotelHasLunch,confirmation.hotelHasAfterNoonTea,confirmation.price,confirmation.contactsReceived,confirmation.contactsSigned,confirmation.confirmationLocationNotes,"
				+ "confirmation.confirmationLocationTrainingMaterialCreated,confirmation.trainingMaterialSentOn,confirmation.emailHotelToInform,confirmation.confirmationTrainingMaterialReceivedOn,booking.bookinguser,"
				+ "booking.calleduser,booking.calledDate,booking.callerNotes,booking.session, booking.typeOfCall, salesperson.SName salesperson, booking.demoDate "
				+ "FROM booking "
				+ "LEFT JOIN training ON booking.training = training.TId LEFT JOIN trlocation ON booking.trlocation = trlocation.TrLId "
				+ "LEFT JOIN invoice ON booking.BId=invoice.BId  LEFT JOIN couriertracking ON booking.BID = couriertracking.bid  "
				+ "LEFT JOIN confirmation ON booking.BId=confirmation.BId  LEFT JOIN invnotestab ON booking.BId=invnotestab.BId  "
				+ "LEFT JOIN salesperson ON booking.SId=salesperson.SId WHERE booking.TrDate = ?1 AND booking.STATUS <> 'Cancelled'  ORDER BY  booking.STATUS, trlocation.trlocation";

		System.out.println("sqlQuery .... " + sqlQuery);

		Query query = em.createNativeQuery(sqlQuery);
		query.setParameter(1, date);

		List<Object[]> bookingLists = query.getResultList();
		System.out.println("resultList size ... " + bookingLists.size());
		return bookingLists;

	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Integer getInvoiceNumber(String paymentType) {
		List<Integer> query;

		if (paymentType.equals("L")) {
			String sqlQuery = "Select Max(id) `id` From invoice where paymenttype='L' And (id not between 20100 and 20174 and id>20000) "
					+ "Union All Select Max(id) From invoicemeetingroom where paymenttype='L' and id>20000 Union All Select Max(id) "
					+ "From coursesmaster_invoice where paymenttype='L'  and id>20000 ORDER BY id DESC";
			query = em.createNativeQuery(sqlQuery).getResultList();

		} else {
			String sqlQuery = "Select Max(id) `id` From invoice where paymenttype='S' And (id not between 20100 and 20174 and id>20000) "
					+ "Union All Select Max(id) From invoicemeetingroom where paymenttype='S' and id>20000 Union All Select Max(id) From "
					+ "coursesmaster_invoice where paymenttype='S'  and id>20000 ORDER BY id DESC";
			query = em.createNativeQuery(sqlQuery).getResultList();

		}
		// paymentType = "" + query;
		return query.get(0) + 1;

		/*
		 * return em.createNativeQuery(
		 * "SELECT DISTINCT BId FROM Confirmation confirm WHERE confirm." +
		 * confirmInhouseValue + "=" + "'0'") .getResultList();
		 */
	}
}