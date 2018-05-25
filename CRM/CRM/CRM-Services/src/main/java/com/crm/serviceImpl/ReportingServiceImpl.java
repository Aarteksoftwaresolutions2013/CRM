package com.crm.serviceImpl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.pojo.ReportingDTO;
import com.crm.repository.IReportingRepository;
import com.crm.service.IReportingService;

@Service
public class ReportingServiceImpl implements IReportingService {

	@Autowired
	private IReportingRepository reportingRepository;

	int noofMonths = 0;
	int noofWeeks = 0;
	boolean isFutureDate = false;
	boolean isPastDate = false;
	String paymentType = "All";

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> findReporting() {
		List<Object[]> bookingLists = reportingRepository.findAllReporting();

		/*
		 * List<RecordsDTO> recordsDTOs = new LinkedList<RecordsDTO>(); for (Object[]
		 * objects : bookingLists) { RecordsDTO recordsDto = new RecordsDTO(objects,
		 * true); recordsDTOs.add(recordsDto); }
		 */
		return bookingLists;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> btnRevenueWithoutVatClick(ReportingDTO reportingDTO) {
		String queryStartPart = "";
		String queryStartPartMeetingRoom = "";
		String queryStartPartCourse = "";
		String ReportType = "RevenueWithoutVat";
		periodSelectedItemValue(reportingDTO);
		if (noofMonths == 0 && noofWeeks == 0) {
			return null;
		} else {
			int[] months = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] years = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] days = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			String[] labels = new String[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			getMonthAndYear(days, months, years, labels);

			for (int i = 0; i < months.length; i++) {
				if (reportingDTO.getPayment() == "Received") {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime();
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = queryStartPart + "Sum(if (PayReceiveDate between '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total-vatamount,''))'" + labels[i]
								+ "',";
						System.out.println("queryStartPart...................." + queryStartPart);
						queryStartPartMeetingRoom = queryStartPartMeetingRoom + "Sum(if (payreceivedate between '"
								+ startDate.toString("yyyy-MM-dd") + "' and '" + endDate.toString("yyyy-MM-dd")
								+ "',Total-vatamount,''))'" + labels[i] + "',";
						queryStartPartCourse = queryStartPartCourse + "Sum(if (payreceivedate between '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total-vatamount,''))'" + labels[i]
								+ "',";
					} else {
						queryStartPart = queryStartPart + "Sum(if (Month(PayReceiveDate)=" + months[i] + " and year(PayReceiveDate)="
								+ years[i] + ",Total-vatamount,''))'" + labels[i] + "',";
						queryStartPartMeetingRoom = queryStartPartMeetingRoom + "Sum(if (Month(payreceivedate)=" + months[i]
								+ " and year(payreceivedate)=" + years[i] + ",Total-vatamount,''))'" + labels[i] + "',";
						queryStartPartCourse = queryStartPartCourse + "Sum(if (Month(payreceivedate)=" + months[i]
								+ " and year(payreceivedate)=" + years[i] + ",Total-vatamount,''))'" + labels[i] + "',";

					}

				} else {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);

						DateTime endDate = startDate.plusDays(6);
						queryStartPart = queryStartPart + "Sum(if (TrDate between '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',Total-vatamount,''))'" + labels[i] + "',";
						queryStartPartMeetingRoom = queryStartPartMeetingRoom + "Sum(if (meetingroomhiredate between '"
								+ startDate.toString("yyyy-MM-dd") + "' and '" + endDate.toString("yyyy-MM-dd")
								+ "',Total-vatamount,''))'" + labels[i] + "',";
						queryStartPartCourse = queryStartPartCourse + "Sum(if (t_date between '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',Total-vatamount,''))'" + labels[i] + "',";
					} else {
						queryStartPart = queryStartPart + "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)=" + years[i]
								+ ",Total-vatamount,''))'" + labels[i] + "',";
						queryStartPartMeetingRoom = queryStartPartMeetingRoom + "Sum(if (Month(meetingroomhiredate)=" + months[i]
								+ " and year(meetingroomhiredate)=" + years[i] + ",Total-vatamount,''))'" + labels[i]
								+ "',";
						queryStartPartCourse = queryStartPartCourse + "Sum(if (Month(t_date)=" + months[i] + " and year(t_date)=" + years[i]
								+ ",Total-vatamount,''))'" + labels[i] + "',";
					}

				}
			}

			List<Object[]> courseList = reportingRepository.btnRevenueWithoutVatClick(reportingDTO, queryStartPart,
					queryStartPartMeetingRoom, queryStartPartCourse);
			return courseList;
		}

	}

	private void getMonthAndYear(int[] weeks, int[] months, int[] years, String[] labels) {
		int i = 0;
		int index = 0;
		int z = 0;
		if (noofWeeks > 0) {

			if (isFutureDate) {
				for (i = noofWeeks; i > index; i--) {
					weeks[z] = DateTime.now().plusDays(-(i * 7)).getDayOfMonth();
					months[z] = DateTime.now().plusDays(-(i * 7)).getMonthOfYear();
					years[z] = DateTime.now().plusDays(-(i * 7)).getYear();
					labels[z] = DateTime.now().plusDays(+(i * 7)).toString("dd") + "/"
							+ DateTime.now().plusDays(+(i * 7 + 6)).toString("dd") + "-"
							+ DateTime.now().plusDays(+(i * 7 + 6)).toString("MM") + "-"
							+ DateTime.now().plusDays(+(i * 7 + 6)).toString("yy");
					z++;
				}
				index = noofWeeks + index;
			}
			if (isPastDate) {
				for (i = 0; i < noofWeeks; i++) {
					weeks[z] = DateTime.now().plusDays(-(i * 7)).getHourOfDay();
					months[z] = DateTime.now().plusDays(-(i * 7)).getMonthOfYear();
					years[z] = DateTime.now().plusDays(-(i * 7)).getYear();
					labels[z] = DateTime.now().plusDays(-(i * 7)).toString("dd") + "/"
							+ DateTime.now().plusDays(-(i * 7 - 6)).toString("dd") + "-"
							+ DateTime.now().plusDays(-(i * 7 - 6)).toString("MM") + "-"
							+ DateTime.now().plusDays(-(i * 7 - 6)).toString("yy");
					z++;
				}

			}

		} else {
			weeks = null;
			if (isFutureDate) {
				for (i = noofMonths; i > index; i--) {
					months[z] = DateTime.now().plusMonths(+(i)).getMonthOfYear();
					years[z] = DateTime.now().plusMonths(+(i)).getYear();
					labels[z] = DateTime.now().plusMonths(+(i)).toString("MM") + "-"
							+ DateTime.now().plusMonths(+(i)).toString("yy");
					z++;
				}
				index = noofWeeks + index;
			}
			if (isPastDate) {
				for (i = 0; i < noofMonths; i++) {
					months[z] = DateTime.now().plusMonths(-(i)).getMonthOfYear();
					years[z] = DateTime.now().plusMonths(-(i)).getYear();
					labels[z] = DateTime.now().plusMonths(-(i)).toString("MM") + "-"
							+ DateTime.now().plusMonths(-(i)).toString("yy");
					z++;
				}

			}
		}
	}

	private void periodSelectedItemValue(ReportingDTO reportingDTO) {
		noofMonths = 0;
		noofWeeks = 0;
		isFutureDate = false;
		isPastDate = false;

		if (reportingDTO.getPeriod() != "Show All") {

			if (("next 3 months").equals(reportingDTO.getPeriod())) {
				noofMonths = 3;
				isFutureDate = true;

			} else if (("weekly").equals(reportingDTO.getPeriod())) {
				noofWeeks = 12;
				isFutureDate = true;
				isPastDate = true;
			} else if (("1 month").equals(reportingDTO.getPeriod())) {
				noofMonths = 1;
				isPastDate = true;
			} else if (("3 month").equals(reportingDTO.getPeriod())) {
				noofMonths = 3;
				isPastDate = true;
			} else if (("6 month").equals(reportingDTO.getPeriod())) {
				noofMonths = 6;
				isPastDate = true;
			} else if (("1 year").equals(reportingDTO.getPeriod())) {
				noofMonths = 12;
				isPastDate = true;
			} else if (("2 years").equals(reportingDTO.getPeriod())) {
				noofMonths = 24;
				isPastDate = true;
			} else if (("3 years").equals(reportingDTO.getPeriod())) {
				noofMonths = 36;
				isPastDate = true;
			} else if (("4 years").equals(reportingDTO.getPeriod())) {
				noofMonths = 36;
				isPastDate = true;
			}

			else {
				noofMonths = 60;
				isPastDate = true;
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> btnRevenueClick(ReportingDTO reportingDTO) {
		String reportType = "Revenue";
		String queryStartPart = null;
		String queryStartPartMeetingRoom = null;
		String queryStartPartCourse = null;
		periodSelectedItemValue(reportingDTO);
		if (noofMonths == 0 && noofWeeks == 0) {
			return null;
		} else {
			int[] months = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] years = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] days = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			String[] labels = new String[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			getMonthAndYear(days, months, years, labels);

			for (int i = 0; i < months.length; i++) {
				if (paymentType == "Received") {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (PayReceiveDate between '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
						queryStartPartMeetingRoom = "Sum(if (payreceivedate between '"
								+ startDate.toString("yyyy-MM-dd") + "' and '" + endDate.toString("yyyy-MM-dd")
								+ "',Total,''))`" + labels[i] + "`,";
						queryStartPartCourse = "Sum(if (payreceivedate between '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(PayReceiveDate)=" + months[i] + " and year(PayReceiveDate)="
								+ years[i] + ",Total,''))`" + labels[i] + "`,";
						queryStartPartMeetingRoom = "Sum(if (Month(payreceivedate)=" + months[i]
								+ " and year(payreceivedate)=" + years[i] + ",Total,''))`" + labels[i] + "`,";
						queryStartPartCourse = "Sum(if (Month(payreceivedate)=" + months[i]
								+ " and year(payreceivedate)=" + years[i] + ",Total,''))`" + labels[i] + "`,";

					}

				} else {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (TrDate between '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
						queryStartPartMeetingRoom = "Sum(if (meetingroomhiredate between '"
								+ startDate.toString("yyyy-MM-dd") + "' and '" + endDate.toString("yyyy-MM-dd")
								+ "',Total,''))`" + labels[i] + "`,";
						queryStartPartCourse = "Sum(if (t_date between '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)=" + years[i]
								+ ",Total,''))`" + labels[i] + "`,";
						queryStartPartMeetingRoom = "Sum(if (Month(meetingroomhiredate)=" + months[i]
								+ " and year(meetingroomhiredate)=" + years[i] + ",Total,''))`" + labels[i] + "`,";
						queryStartPartCourse = "Sum(if (Month(t_date)=" + months[i] + " and year(t_date)=" + years[i]
								+ ",Total,''))`" + labels[i] + "`,";
					}

				}
			}

		}

		List<Object[]> list = reportingRepository.btnRevenueClick(reportingDTO, queryStartPart,
				queryStartPartMeetingRoom, queryStartPartCourse);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> btnlocationsClick(ReportingDTO reportingDTO) {
		String queryStartPart = null;
		if (reportingDTO.getStatus() == "") {
			System.out.println("Please Choose a Status");
			return null;
		}
		String reportType = "Locations";
		periodSelectedItemValue(reportingDTO);
		if (noofMonths == 0 && noofWeeks == 0) {
			return null;
		} else {
			int[] months = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] years = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] days = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			String[] labels = new String[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			getMonthAndYear(days, months, years, labels);

			for (int i = 0; i < months.length; i++) {
				if (paymentType == "Received") {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (PayReceiveDate BETWEEN '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(PayReceiveDate)=" + months[i] + " and year(PayReceiveDate)="
								+ years[i] + ",Total,''))`" + labels[i] + "`,";
					}

				} else {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (TrDate BETWEEN '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)=" + years[i]
								+ ",Total,''))`" + labels[i] + "`,";
					}
				}
			}
		}

		List<Object[]> list = reportingRepository.btnLocationsClick(reportingDTO, queryStartPart);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> btnOutstandingClick(ReportingDTO reportingDTO) {
		String queryStartPart = null;
		if (reportingDTO.getStatus() == "") {
			System.out.println("Please Choose a Status");
			return null;
		}
		String reportType = "Outstanding";
		periodSelectedItemValue(reportingDTO);
		if (noofMonths == 0 && noofWeeks == 0) {
			return null;
		} else {
			int[] months = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] years = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] days = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			String[] labels = new String[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			getMonthAndYear(days, months, years, labels);
			for (int i = 0; i < months.length; i++) {
				if (paymentType == "Received") {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (PayReceiveDate BETWEEN '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(PayReceiveDate)=" + months[i] + " and year(PayReceiveDate)="
								+ years[i] + ",Total,''))`" + labels[i] + "`,";
					}
				} else {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (TrDate BETWEEN '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)=" + years[i]
								+ ",Total,''))`" + labels[i] + "`,";
					}
				}
			}

		}
		List<Object[]> list = reportingRepository.btnOutstandingClick(reportingDTO, queryStartPart);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> btnVatReportClick(ReportingDTO reportingDTO) {
		String queryStartPart = null;
		if (reportingDTO.getStatus() == "") {
			System.out.println("Please Choose a Status");
			return null;
		}
		String reportType = "Vat";
		periodSelectedItemValue(reportingDTO);
		if (noofMonths == 0 && noofWeeks == 0) {
			return null;
		} else {
			int[] months = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] years = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			int[] days = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			String[] labels = new String[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
					: noofMonths];
			getMonthAndYear(days, months, years, labels);

			for (int i = 0; i < months.length; i++) {
				if (paymentType == "Received") {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = queryStartPart + "Sum(if (PayReceiveDate BETWEEN '"
								+ startDate.toString("yyyy-MM-dd") + "' and '" + endDate.toString("yyyy-MM-dd")
								+ "',vatamount,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(PayReceiveDate)=" + months[i] + " and year(PayReceiveDate)="
								+ years[i] + ",vatamount,''))`" + labels[i] + "`,";
					}
				} else {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);

						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (TrDate BETWEEN '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',vatamount,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)=" + years[i]
								+ ",vatamount,''))`" + labels[i] + "`,";
					}
				}
			}

			for (int i = 0; i < months.length; i++) {
				if (paymentType == "Received") {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (PayReceiveDate BETWEEN '" + startDate.toString("yyyy-MM-dd")
								+ "' and '" + endDate.toString("yyyy-MM-dd") + "',vatamount,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(PayReceiveDate)=" + months[i] + " and year(PayReceiveDate)="
								+ years[i] + ",vatamount,''))`" + labels[i] + "`,";
					}
				} else {
					if (noofWeeks > 0) {
						DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
						DateTime endDate = startDate.plusDays(6);
						queryStartPart = "Sum(if (TrDate BETWEEN '" + startDate.toString("yyyy-MM-dd") + "' and '"
								+ endDate.toString("yyyy-MM-dd") + "',vatamount,''))`" + labels[i] + "`,";
					} else {
						queryStartPart = "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)=" + years[i]
								+ ",vatamount,''))`" + labels[i] + "`,";
					}
				}
			}

		}
		List<Object[]> list = reportingRepository.btnVatReportClick(reportingDTO, queryStartPart);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> btnCoursesClickService(ReportingDTO reportingDTO) {
		System.out.println("btnCoursesClickService.....");
		String reportType = "Courses";

		/*
		 * if (noofMonths == 0 && noofWeeks == 0) return ;
		 */
		/*
		 * if (noofMonths == 0 && noofWeeks == 0) { return null; } else {
		 */
		int[] months = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
				: noofMonths];
		int[] years = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2 : noofMonths];
		int[] days = new int[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2 : noofMonths];
		String[] labels = new String[isFutureDate && isPastDate ? noofWeeks > 0 ? noofWeeks * 2 : noofMonths * 2
				: noofMonths];

		String queryStartPart = "Select TName,";

		getMonthAndYear(days, months, years, labels);
		for (int i = 0; i < months.length; i++) {
			if (paymentType == "Received") {
				if (noofWeeks > 0) {
					DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
					DateTime endDate = startDate.plusDays(6);

					queryStartPart = queryStartPart + "Sum(if (PayReceiveDate BETWEEN '"
							+ startDate.toString("yyyy-MM-dd") + "' and '" + endDate.toString("yyyy-MM-dd")
							+ "',Total,''))`" + labels[i] + "`,";
				} else {
					queryStartPart = queryStartPart + "Sum(if (Month(PayReceiveDate)=" + months[i]
							+ " and year(PayReceiveDate)=" + years[i] + ",Total,''))`" + labels[i] + "`,";
				}
			} else {
				if (noofWeeks > 0) {
					DateTime startDate = new DateTime(years[i], months[i], days[i], i, i);
					DateTime endDate = startDate.plusDays(6);
					queryStartPart = queryStartPart + "Sum(if (TrDate BETWEEN '" + startDate.toString("yyyy-MM-dd")
							+ "' and '" + endDate.toString("yyyy-MM-dd") + "',Total,''))`" + labels[i] + "`,";
				} else {
					queryStartPart = queryStartPart + "Sum(if (Month(TrDate)=" + months[i] + " and year(TrDate)="
							+ years[i] + ",Total,''))`" + labels[i] + "`,";
				}
			}
		}

		// Object selectUser = getSelectedUser(reportingDTO);

		List<Object[]> bookingList = reportingRepository.btnCoursesClickRepository(reportingDTO, queryStartPart);

		// List<ReportingDTO> reportingDTOs = new ArrayList<ReportingDTO>();

		return bookingList;

	}

}