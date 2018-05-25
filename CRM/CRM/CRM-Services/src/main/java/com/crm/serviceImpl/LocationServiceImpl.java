package com.crm.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.CalendarColor;
import com.crm.model.Locations;
import com.crm.pojo.CalendarDTO;
import com.crm.pojo.CourseMasterDTO;
import com.crm.pojo.DonationDTO;
import com.crm.pojo.LocationDTO;
import com.crm.pojo.MeetingDTO;
import com.crm.repository.IBookingRepository;
import com.crm.repository.ICalendarColorRepository;
import com.crm.repository.ICourseMasterRepository;
import com.crm.repository.IDonationRepository;
import com.crm.repository.ILocationRepository;
import com.crm.repository.IMeetingRepository;
import com.crm.service.ILocationService;
import com.crm.utils.DateUtils;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	private ILocationRepository locationRepository;

	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private IMeetingRepository meetingRepository;

	@Autowired
	private IDonationRepository donationRepository;

	@Autowired
	private ICourseMasterRepository coursemasterrepository;

	@Autowired
	private ICalendarColorRepository calendarcolorrepository;

	List<Object[]> bookingList;
	List<Object[]> meetingLists;
	List<Object[]> donationList;
	List<Object[]> courseMasterList;
	List<LocationDTO> listOfCalendarBooking = new ArrayList<LocationDTO>();
	List<LocationDTO> listOfCalendarMettings = new ArrayList<LocationDTO>();
	List<LocationDTO> listOfCalendarDonation = new ArrayList<LocationDTO>();
	List<LocationDTO> listOfCalendarCourse = new ArrayList<LocationDTO>();
	List<LocationDTO> listOfCalendarRecords = new ArrayList<LocationDTO>();

	/**
	 * Method for Find All Locations.
	 * 
	 * @return List<Locations>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Locations> findAllLocations() {
		return locationRepository.findAllLocations();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<LocationDTO> findAllLocationDate(CalendarDTO calendarDto) {
		loadData(calendarDto);
		getBookingEnquiry(calendarDto);
		List<LocationDTO> listOfCalendarColors = new ArrayList<LocationDTO>();
		List<Object[]> calendarColor = calendarcolorrepository.findCalendarColorByYearAndMonth(calendarDto);
		for (Object[] calendarColor2 : calendarColor) {
			LocationDTO locationsDto = new LocationDTO();
			locationsDto.setBackgroudColor((String) calendarColor2[1]);
			locationsDto.settDate((String) calendarColor2[0].toString());
			listOfCalendarColors.add(locationsDto);
		}
		listOfCalendarRecords.addAll(listOfCalendarColors);
		listOfCalendarRecords.addAll(listOfCalendarBooking);
		listOfCalendarRecords.addAll(listOfCalendarMettings);
		listOfCalendarRecords.addAll(listOfCalendarDonation);
		listOfCalendarRecords.addAll(listOfCalendarCourse);
		return listOfCalendarRecords;
	}

	private void loadData(CalendarDTO calendarDto) {
		if (calendarDto.getConsultant() != null) {
			if (calendarDto.getConsultant().equals("Meeting Room")) {
				meetingLists = meetingRepository.findMeetingByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				return;
			}
			if (calendarDto.getConsultant().equals("Courses")) {
				courseMasterList = coursemasterrepository.findCourseMasterByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				return;
			}
			if (calendarDto.getConsultant().equals("Donations")) {
				donationList = donationRepository.findDonationByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				return;
			}
			if (calendarDto.getConsultant().toString().equals("Enquiries")) {
				bookingList = bookingRepository.findBookingByYearAndMonth(calendarDto);
				meetingLists = meetingRepository.findMeetingByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				courseMasterList = coursemasterrepository.findCourseMasterByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				return;
			}
			if (calendarDto.getConsultant().equals("All")) {
				bookingList = bookingRepository.findBookingByYearAndMonth(calendarDto);
				meetingLists = meetingRepository.findMeetingByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				courseMasterList = coursemasterrepository.findCourseMasterByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				donationList = donationRepository.findDonationByYearAndMonth(calendarDto.getYear(),
						calendarDto.getMonth());
				return;
			} else {
				bookingList = bookingRepository.findBookingByYearAndMonth(calendarDto);
				return;
			}
		} else {
			bookingList = bookingRepository.findBookingByYearAndMonth(calendarDto);
			meetingLists = meetingRepository.findMeetingByYearAndMonth(calendarDto.getYear(), calendarDto.getMonth());
			donationList = donationRepository.findDonationByYearAndMonth(calendarDto.getYear(), calendarDto.getMonth());
			courseMasterList = coursemasterrepository.findCourseMasterByYearAndMonth(calendarDto.getYear(),
					calendarDto.getMonth());
		}

	}

	private void getBookingEnquiry(CalendarDTO calendarDto) {
		listOfCalendarBooking.clear();
		listOfCalendarMettings.clear();
		listOfCalendarDonation.clear();
		listOfCalendarCourse.clear();
		listOfCalendarRecords.clear();
		if (calendarDto.getConsultant() != null) {
			if (calendarDto.getConsultant().equals("Meeting Room")) {
				listOfCalendarMettings = getBookEnqMeetingRoom(meetingLists);
				return;
			}
			if (calendarDto.getConsultant().equals("Courses")) {
				listOfCalendarCourse = getBookEnqCourseMaster(courseMasterList, calendarDto);
				return;
			}
			if (calendarDto.getConsultant().equals("Donations")) {
				listOfCalendarDonation = getBookEnqDonation(donationList);
				return;
			}
			if (calendarDto.getConsultant().equals("Enquiries")) {
				listOfCalendarBooking = getBookEnqForLocation(bookingList, calendarDto);
				listOfCalendarMettings = getBookEnqMeetingRoom(meetingLists);
				listOfCalendarCourse = getBookEnqCourseMaster(courseMasterList, calendarDto);
				return;
			}
			if (calendarDto.getConsultant().equals("All")) {
				listOfCalendarBooking = getBookEnqForLocation(bookingList, calendarDto);
				listOfCalendarMettings = getBookEnqMeetingRoom(meetingLists);
				listOfCalendarDonation = getBookEnqDonation(donationList);
				listOfCalendarCourse = getBookEnqCourseMaster(courseMasterList, calendarDto);
				return;
			} else {
				listOfCalendarBooking = getBookEnqForLocation(bookingList, calendarDto);
				return;
			}
		} else {
			listOfCalendarBooking = getBookEnqForLocation(bookingList, calendarDto);
			listOfCalendarMettings = getBookEnqMeetingRoom(meetingLists);
			listOfCalendarDonation = getBookEnqDonation(donationList);
			listOfCalendarCourse = getBookEnqCourseMaster(courseMasterList, calendarDto);
		}

	}

	private List<LocationDTO> getBookEnqForLocation(List<Object[]> bookingList, CalendarDTO calendarDto) {
		List<Object[]> locationsList = locationRepository.findAllLocationDate(calendarDto);
		int bookCount = 0;
		int medium;
		int enqCount = 0;
		int assetEnqCount = 0;
		String locationText = "";
		////////// Phases Count///////////
		String[] match_symbols = { "Primary", "Secondary", "Conditional Booking", "Combined", "KS5", "" };
		String[] match_symbols1 = { "Junior", "Special Secondary", "Conditional Booking", "Combined", "KS5", "" };
		String[] match_symbols2 = { "Infant", "Secondary", "Conditional Booking", "Combined", "KS5", "" };
		String[] match_symbols3 = { "Special Primary", "Secondary", "Conditional Booking", "Combined", "KS5", "" };
		String[] display_symbols = { "P", "S", "C", "PS", "K", "U" };
		int[] counting = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		String[] AssetsSubjectsPrimary = { "14" };
		String[] AssetsSubjectsSecondary = { "15" };
		String[] AssetsSubjectsBoth = { "22" };
		String[] AssetsSubjectsAll = { "14", "15", "22" };
		List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
		List<LocationDTO> locationDTOBooking = new ArrayList<LocationDTO>();
		List<LocationDTO> calendarBookingList = new ArrayList<LocationDTO>();
		for (Object[] objects : locationsList) {
			LocationDTO locationsDto = new LocationDTO(objects);
			locationDTOs.add(locationsDto);
		}
		for (Object[] objects : bookingList) {
			LocationDTO locationsDto = new LocationDTO(objects, true);
			locationDTOBooking.add(locationsDto);
		}
		if (calendarDto.getPhase().equals("Show All") && calendarDto.getConsultant().equals("All")) {
			locationDTOBooking.addAll(locationDTOs);
		}

		Map<String, List<LocationDTO>> mapOfLOcationsBooking = new HashMap<String, List<LocationDTO>>();
		Map<String, List<LocationDTO>> mapOfBookingOfSameDate = new HashMap<String, List<LocationDTO>>();
		for (LocationDTO locationDTO : locationDTOBooking) {
			if (!mapOfLOcationsBooking.containsKey(locationDTO.getLocation())) {
				List<LocationDTO> listOfAllBookingLocations = new ArrayList<LocationDTO>();
				listOfAllBookingLocations.add(locationDTO);
				mapOfLOcationsBooking.put(locationDTO.getLocation(), listOfAllBookingLocations);
			} else {
				List<LocationDTO> listOfAllBookingLocations = mapOfLOcationsBooking.get(locationDTO.getLocation());
				listOfAllBookingLocations.add(locationDTO);
				mapOfLOcationsBooking.put(locationDTO.getLocation(), listOfAllBookingLocations);
			}

		}
		for (LocationDTO locationDTO : locationDTOBooking) {
			if (!mapOfBookingOfSameDate.containsKey(locationDTO.getTrDateDummy())) {
				List<LocationDTO> listOfAllBookingDates = new ArrayList<LocationDTO>();
				listOfAllBookingDates.add(locationDTO);
				mapOfBookingOfSameDate.put(locationDTO.getTrDateDummy(), listOfAllBookingDates);
			} else {
				List<LocationDTO> listOfAllBookingDate = mapOfBookingOfSameDate.get(locationDTO.getTrDateDummy());
				listOfAllBookingDate.add(locationDTO);
				mapOfBookingOfSameDate.put(locationDTO.getTrDateDummy(), listOfAllBookingDate);
			}

		}
		for (Map.Entry<String, List<LocationDTO>> location : mapOfLOcationsBooking.entrySet()) {
			boolean isAsset = false;
			LocationDTO bookingLocationText = null;
			List<LocationDTO> locationDatesList = mapOfLOcationsBooking.get(location.getKey());
			List<String> dates = new ArrayList<String>();
			for (LocationDTO locationDTO : locationDatesList) {
				if (!dates.contains(locationDTO.getTrDateDummy())) {
					dates.add(locationDTO.getTrDateDummy());
				}
			}
			for (int i = 0; i < dates.size(); i++) {
				LocationDTO locationDtoObj = new LocationDTO();
				List<LocationDTO> listSameDateBooking = new ArrayList<LocationDTO>();
				List<Integer> bidList = new ArrayList<Integer>();
				for (int j = 0; j < locationDatesList.size(); j++) {
					if (locationDatesList.get(j).getTrDateDummy().equals(dates.get(i))) {
						listSameDateBooking.add(locationDatesList.get(j));
					}
				}
				for (Map.Entry<String, List<LocationDTO>> dateBooking : mapOfBookingOfSameDate.entrySet()) 
				{
				List<LocationDTO> bookingDatesList = mapOfBookingOfSameDate.get(dateBooking.getKey());
				for(LocationDTO locationDtoObject : bookingDatesList){
					if(locationDtoObject.getTrDateDummy().equals(dates.get(i))){
						bidList.add(locationDtoObject.getbId());
					}
				}
				}
				
				for (LocationDTO bookingLocation : listSameDateBooking) {

					medium = 0;
					bookingLocationText = bookingLocation;
					bidList.add(bookingLocation.getbId());
					if (bookingLocation.getStatus().equals("Booking")
							|| bookingLocation.getStatus().equals("Conditional Booking")) {
						medium = bookingLocation.getDelegateNum();
						if (bookingLocation.getLocation().equals("In-house")) {
							bookCount += 1;
						} else {
							bookCount += medium;
						}
					}

					else if (bookingLocation.getStatus().equals("temp")) {
						bookCount += 1000;
					} else if (bookingLocation.getStatus().equals("Enquiry")) {
						medium = bookingLocation.getDelegateNum();
						if (Arrays.asList(AssetsSubjectsAll).contains(bookingLocation.getTraining())) {
							isAsset = true;
							if (bookingLocation.getLocation().equals("In-house")) {
								assetEnqCount += 1;
							} else {
								assetEnqCount += medium;
							}

						} else {
							if (bookingLocation.getLocation().equals("In-house")) {
								enqCount += 1;
							} else {
								enqCount += medium;

							}
						}

					}

					for (int x = 0; x < match_symbols.length; x++) {
						if (bookingLocation.getBookingPhase() != null) {
							if ((bookingLocation.getBookingPhase().equals(match_symbols[x])
									|| bookingLocation.getBookingPhase().equals(match_symbols1[x])
									|| bookingLocation.getBookingPhase().equals(match_symbols2[x])
									|| bookingLocation.getBookingPhase().equals(match_symbols3[x]))
									&& (bookingLocation.getStatus().equals("Booking")
											|| bookingLocation.getStatus().equals("Conditional Booking"))) {
								// int.TryParse(row2["DelegNum"].ToString(), out
								// medium);
								medium = bookingLocation.getDelegateNum();

								if (Arrays.asList(AssetsSubjectsAll).contains(bookingLocation.getTraining())) {
									int locationCount = 0;
									if (Arrays.asList(AssetsSubjectsPrimary).contains(bookingLocation.getTraining())) {
										isAsset = true;
										locationCount = 7;
									} else if (Arrays.asList(AssetsSubjectsSecondary)
											.contains(bookingLocation.getTraining())) {
										isAsset = true;
										locationCount = 8;
									} else if (Arrays.asList(AssetsSubjectsBoth)
											.contains(bookingLocation.getTraining())) {
										isAsset = true;
										locationCount = 9;
									}
									if (bookingLocation.getLocation().equals("In-house")) {
										counting[locationCount] += 1;
									} else {
										counting[locationCount] += medium;
									}

								} else {
									if (bookingLocation.getStatus().equals("Conditional Booking")) {
										if (bookingLocation.getLocation().equals("In-house")) {
											counting[2] += 1;
										} else {
											counting[2] += medium;
										}
									} else {
										if (bookingLocation.getLocation().equals("In-house")) {
											counting[x] += 1;
										} else {
											counting[x] += medium;
										}
									}

								}
							}
						}

					}

				}
				locationText = "";
				if (!isAsset) {
					locationText += bookingLocationText.getLocation() + " ";
				} else {
					locationText += "\nAsset ";
				}

				Boolean hasBooking = false;
				if (bookCount > 0) {
					if (bookingLocationText.getLocation().equals("In-house") || bookCount > 1) {
						locationText += (bookCount >= 1000 ? bookCount % 1000 : bookCount) + "B";
					}
					if (!bookingLocationText.getLocation().equals("In-house")) {
						hasBooking = true;
						locationText += "(";
					}
				}
				if (hasBooking) {
					for (int x = 0; x < display_symbols.length; x++) {
						String s_1 = "";

						if (counting[x] > 0) {
							locationText += (counting[x] >= 1000 ? counting[x] % 1000 : counting[x]) + ""
									+ display_symbols[x] + ", " + s_1;
						}
					}

					if (counting[7] > 0) {
						if (bookingLocationText.getLocation().equals("No Location")) {
							locationText += counting[7] + "P" + ", ";
						} else {
							locationText += "Asset " + counting[7] + "P" + ", ";
						}
					}

					if (counting[8] > 0) {
						if (bookingLocationText.getLocation().equals("No Location")) {
							locationText += counting[8] + "S" + ", ";
						} else {
							locationText += "Asset " + counting[8] + "S" + ", ";
						}
					}

					if (counting[9] > 0) {
						if (bookingLocationText.getLocation().equals("No Location")) {
							locationText += counting[9] + "PS" + ", ";
						} else {
							locationText += "Asset " + counting[9] + "PS" + ", ";
						}
					}
					if (locationText.contains(", ")) {
						locationText = locationText.substring(0, locationText.length() - 2);
					}

					locationText += ") ";
					if (locationText.contains("()")) {
						locationText = locationText.substring(0, locationText.length() - 3);
					}
				}
				if (enqCount > 0) {
					locationText += enqCount + "E ";
				}
				if (assetEnqCount > 0) {

					if (bookingLocationText.getLocation().equals("No Location")) {
						locationText += assetEnqCount + "E ";
					} else {
						locationText += "Asset " + assetEnqCount + "E ";
					}

				}
				locationDtoObj.setLocation(locationText);
				locationDtoObj.settDate(bookingLocationText.gettDate());
				locationDtoObj.setbId(Collections.max(bidList));
				calendarBookingList.add(locationDtoObj);
				for (int x = 0; x < counting.length; x++) {
					counting[x] = 0;
				}
				bookCount = 0;
				enqCount = 0;
				assetEnqCount = 0;
			}
		}

		return calendarBookingList;
	}

	private List<LocationDTO> getBookEnqDonation(List<Object[]> donationList) {
		int bookCount = 0;
		int enqCount = 0;
		String locationText = " ";
		List<LocationDTO> calendarDonationList = new ArrayList<LocationDTO>();
		List<DonationDTO> donationsList = new ArrayList<DonationDTO>();
		for (Object[] objects : donationList) {
			DonationDTO donationDto = new DonationDTO(objects);
			donationsList.add(donationDto);
		}

		List<String> trLocations = new ArrayList<String>();
		trLocations.add("");
		for (int index = 0; index < trLocations.size(); index++) {
			Boolean isAsset = false;
			List<String> dates = new ArrayList<String>();
			for (DonationDTO donationDto : donationsList) {
				if (!dates.contains(donationDto.getCollectionDate().toString())) {
					dates.add(donationDto.getCollectionDate().toString());
				}
			}
			for (int dateIndex = 0; dateIndex < dates.size(); dateIndex++) {
				LocationDTO locationsDto = new LocationDTO();
				List<DonationDTO> listSameDateDonation = new ArrayList<DonationDTO>();
				DonationDTO donationDtoText = null;
				for (int listIndex = 0; listIndex < donationsList.size(); listIndex++) {
					if ((donationsList.get(listIndex).getCollectionDate() != null)) {
						if (donationsList.get(listIndex).getCollectionDate().toString().equals(dates.get(dateIndex))) {
							listSameDateDonation.add(donationsList.get(listIndex));
						}
					}

				}
				for (DonationDTO donation : listSameDateDonation) {
					donationDtoText = donation;
					bookCount += 1;
				}
				locationText = "";
				if (!isAsset) {
					locationText += trLocations.get(index) + " ";
				}
				if (bookCount > 0) {
					locationText += " Donation " + bookCount;
				}

				if (enqCount > 0) {
					locationText += enqCount + "E ";
				}

				locationsDto.setLocation(locationText);
				locationsDto.settDate(donationDtoText.getCollectionDate());
				locationsDto.setbId(0);
				calendarDonationList.add(locationsDto);
			}
		}

		return calendarDonationList;
	}

	private List<LocationDTO> getBookEnqMeetingRoom(List<Object[]> meetingList) {
		int bookCount = 0;
		// int medium;
		int enqCount = 0;
		// int assetEnqCount = 0;
		String locationText = "";
		List<LocationDTO> calendarMeetingList = new ArrayList<LocationDTO>();
		List<MeetingDTO> meetingsList = new ArrayList<MeetingDTO>();
		for (Object[] objects : meetingList) {
			MeetingDTO meetingDto = new MeetingDTO(objects);
			meetingsList.add(meetingDto);
		}

		List<String> trLocations = new ArrayList<String>();
		trLocations.add("Meeting");

		for (int index = 0; index < trLocations.size(); index++) {
			Boolean isAsset = false;
			List<String> dates = new ArrayList<String>();
			for (MeetingDTO meetingDto : meetingsList) {
				if (!dates.contains(meetingDto.getMeetingRoomHireDate().toString())
						&& meetingDto.getMeetingRoomHireDate() != null) {
					dates.add(meetingDto.getMeetingRoomHireDate().toString());
				}
			}

			for (int dateIndex = 0; dateIndex < dates.size(); dateIndex++) {
				MeetingDTO mettingsObj = new MeetingDTO();
				LocationDTO locationsDto = new LocationDTO();
				List<MeetingDTO> listSameDateMeeting = new ArrayList<MeetingDTO>();
				MeetingDTO meetingLocationText = null;
				for (int listIndex = 0; listIndex < meetingsList.size(); listIndex++) {
					if ((meetingsList.get(listIndex).getMeetingRoomHireDate() != null)) {
						if (meetingsList.get(listIndex).getMeetingRoomHireDate().toString()
								.equals(dates.get(dateIndex))) {
							listSameDateMeeting.add(meetingsList.get(listIndex));
						}
					}
				}

				for (MeetingDTO meetings : listSameDateMeeting) {
					// medium = 0;
					meetingLocationText = meetings;
					if (meetings.getBookingEnquiry().contains("Booking")) {
						if ((meetings.getMeetingRoomHireEndDate() != null)) {
							bookCount += (Days
									.daysBetween(new LocalDate(meetings.getMeetingRoomHireEndDate().getTime()),
											new LocalDate(meetings.getMeetingRoomHireDate().getTime()))
									.getDays() + 1);
						} else {
							bookCount += 1;
						}
					} else if (meetings.getBookingEnquiry().contains("Enquiry")) {
						enqCount += 1;
					}

				}
				locationText = "";
				if (bookCount > 0 || enqCount > 0) {
					locationText += trLocations.get(index) + " (";
				}
				if (bookCount > 0) {
					locationText += bookCount + "B";
				}

				if (bookCount > 0 && enqCount > 0) {
					locationText += " ";
				}
				if (enqCount > 0) {
					locationText += enqCount + "E";
				}

				if (bookCount > 0 || enqCount > 0) {
					locationText += ")";
				}
				locationsDto.setLocation(locationText);
				locationsDto.settDate(meetingLocationText.getMeetingRoomHireDate().toString());
				locationsDto.setbId(0);
				calendarMeetingList.add(locationsDto);
				bookCount = 0;
				enqCount = 0;
			}
		}
		return calendarMeetingList;
	}

	private List<LocationDTO> getBookEnqCourseMaster(List<Object[]> courseMasterList, CalendarDTO calendarDto) {
		List<Object[]> courseList = coursemasterrepository.findCourseByYearAndMonthFromCourseTrlocationAndCourseCourses(
				calendarDto.getYear(), calendarDto.getMonth());
		int bookCount = 0;
		int enqCount = 0;
		String locationText = null;
		List<LocationDTO> calendarCourseList = new ArrayList<LocationDTO>();
		List<CourseMasterDTO> courseList1 = new ArrayList<CourseMasterDTO>();
		Map<String, String> courseMasterTraningLocationList = new HashMap<String, String>();
		List<String> trLocations = new ArrayList<String>();
		List<Object[]> allCourse = coursemasterrepository.findCourseByCourseMaster();
		int daydifference = 0;
		for (Object[] objects : courseMasterList) {
			CourseMasterDTO course = new CourseMasterDTO(objects);
			courseList1.add(course);
		}
		for (Object[] object : courseList) {
			CourseMasterDTO course = new CourseMasterDTO();
			CourseMasterDTO courseMaster = new CourseMasterDTO(object, true);
			courseMasterTraningLocationList.put(courseMaster.getCourse(), courseMaster.getShortname());
			Date start = (Date) object[1];
			Date end = (Date) object[2];
			daydifference = Days.daysBetween(new LocalDate(start.getTime()), new LocalDate(end.getTime())).getDays();
			if (daydifference > 0) {
				for (int index = 0; index <= daydifference; index++) {
					course.setCoursesmaster_c_name((String) object[0]);
					course.setCoursesmaster_t_date(DateUtils.addDaysIndate(start, index));
					course.setCoursesmaster_enquiry("temp");
					course.setShortname((String) object[3]);
					courseList1.add(course);
				}
			} else {
				course.setCoursesmaster_c_name((String) object[0]);
				course.setCoursesmaster_t_date(object[1].toString());
				course.setCoursesmaster_enquiry("temp");
				course.setShortname((String) object[3]);
				courseList1.add(course);
			}
		}
		Map<String, List<CourseMasterDTO>> mapOfCourses = new HashMap<String, List<CourseMasterDTO>>();
		for (CourseMasterDTO coursemasterDTO : courseList1) {
			if (!mapOfCourses.containsKey(coursemasterDTO.getCoursesmaster_c_name())) {
				if (coursemasterDTO.getCoursesmaster_t_date().substring(5, 7).equals(calendarDto.getMonth())) {
					List<CourseMasterDTO> listOfAllCourses = new ArrayList<CourseMasterDTO>();
					listOfAllCourses.add(coursemasterDTO);
					mapOfCourses.put(coursemasterDTO.getCoursesmaster_c_name(), listOfAllCourses);
				}
			} else {
				if (coursemasterDTO.getCoursesmaster_t_date().substring(5, 7).equals(calendarDto.getMonth())) {
					List<CourseMasterDTO> listOfAllCourses = mapOfCourses
							.get(coursemasterDTO.getCoursesmaster_c_name());
					listOfAllCourses.add(coursemasterDTO);
					mapOfCourses.put(coursemasterDTO.getCoursesmaster_c_name(), listOfAllCourses);
				}
			}

		}
		for (Object object : allCourse) {
			trLocations.add((String) object);
		}
		for (int locIndex = 0; locIndex < trLocations.size(); locIndex++) {

			List<CourseMasterDTO> courseListForLocation = mapOfCourses.get(trLocations.get(locIndex));
			List<String> dates = new ArrayList<String>();
			if (courseListForLocation != null) {
				for (CourseMasterDTO course : courseListForLocation) {
					if (!dates.contains(course.getCoursesmaster_t_date()) && course.getCoursesmaster_t_date() != null) {
						dates.add(course.getCoursesmaster_t_date());
					}
				}
				for (int dateIndex = 0; dateIndex < dates.size(); dateIndex++) {
					LocationDTO locationsDto = new LocationDTO();
					List<CourseMasterDTO> listSameDateCourse = new ArrayList<CourseMasterDTO>();
					CourseMasterDTO courseText = null;
					for (int listIndex = 0; listIndex < courseListForLocation.size(); listIndex++) {
						if ((courseListForLocation.get(listIndex).getCoursesmaster_t_date() != null)) {
							if (courseListForLocation.get(listIndex).getCoursesmaster_t_date()
									.equals(dates.get(dateIndex))) {
								listSameDateCourse.add(courseListForLocation.get(listIndex));
							}
						}
					}
					for (CourseMasterDTO coursemaster : listSameDateCourse) {
						courseText = coursemaster;

						if (coursemaster.getCoursesmaster_enquiry().contains("Booking")) {
							bookCount += Integer.parseInt(coursemaster.getCoursesmaster_numberofdelegates());
						} else if (coursemaster.getCoursesmaster_enquiry().contains("Enquiry")) {
							enqCount += Integer.parseInt(coursemaster.getCoursesmaster_numberofdelegates());
						}

						else if (coursemaster.getCoursesmaster_enquiry().contains("temp")) {
							bookCount += 1000;
						}
					}
					locationText = "";
					if (bookCount > 0 || enqCount > 0) {
						locationText += courseMasterTraningLocationList.get(trLocations.get(locIndex)) + " ";
					}
					if (bookCount > 0) {
						locationText += (bookCount >= 1000 ? (bookCount % 1000) : bookCount) + "B";
					}

					if (bookCount > 0 && enqCount > 0) {
						locationText += " ";
					}

					if (enqCount > 0) {
						locationText += enqCount + "E";
					}

					if (bookCount > 0 || enqCount > 0) {
						locationText += " ";
					}
					locationsDto.setLocation(locationText);
					locationsDto.settDate(courseText.getCoursesmaster_t_date());
					locationsDto.setbId(0);
					calendarCourseList.add(locationsDto);
					bookCount = 0;
					enqCount = 0;
				}
			}
		}
		return calendarCourseList;
	}

	/**
	 * Method for save Locations.
	 * 
	 * @param locations
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveLocations(Locations locations) {
		locationRepository.saveLocations(locations);
	}

	/**
	 * Method for find Locations Max Id
	 * 
	 * @param locations
	 * @return Locations
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Locations findLocationsMaxId(Locations locations) {
		List<Integer> locMaxId = locationRepository.findMaxId();
		locations.setId(locMaxId.get(0) + 1);
		return locations;
	}

	/**
	 * Method for update Locations
	 * 
	 * @param locations
	 * @return Locations
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Locations updateLocations(Locations locations) {
		return locationRepository.updateLocations(locations);
	}

	/**
	 * Method for delete Locations
	 * 
	 * @param locationsId
	 * @return void
	 */
	/*
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * deleteLocations(Integer locationsId) {
	 * locationRepository.deleteLocations(locationsId); }
	 */

	/**
	 * Method for find Locations by booking id
	 * 
	 * @param locationsId
	 * @return List<Locations>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Locations> findLocationsById(Integer locationsId) {
		return locationRepository.findLocationsById(locationsId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteLocations(Integer[] locationId) {
		locationRepository.deleteLocations(Arrays.asList(locationId));

	}

}