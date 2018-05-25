package com.crm.pojo;

import java.util.Date;

public class MeetingDTO {

	private Date meetingRoomHireDate;
	private Date meetingRoomHireEndDate;
	private String bookingEnquiry;
	private String location;

	public MeetingDTO() {

	}

	public MeetingDTO(Object[] object) {

		this.setMeetingRoomHireDate((Date) object[5]);
		this.setBookingEnquiry((String) object[12]);
		this.setMeetingRoomHireEndDate((Date) object[6]);
	}

	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the meetingRoomHireEndDate
	 */
	public Date getMeetingRoomHireEndDate() {
		return meetingRoomHireEndDate;
	}

	/**
	 * @param meetingRoomHireEndDate
	 *            the meetingRoomHireEndDate to set
	 */
	public void setMeetingRoomHireEndDate(Date meetingRoomHireEndDate) {
		this.meetingRoomHireEndDate = meetingRoomHireEndDate;
	}

	/**
	 * @return the bookingEnquiry
	 */
	public String getBookingEnquiry() {
		return bookingEnquiry;
	}

	/**
	 * @param bookingEnquiry
	 *            the bookingEnquiry to set
	 */
	public void setBookingEnquiry(String bookingEnquiry) {
		this.bookingEnquiry = bookingEnquiry;
	}

	/**
	 * @return the meetingRoomHireDate
	 */
	public Date getMeetingRoomHireDate() {
		return meetingRoomHireDate;
	}

	/**
	 * @param meetingRoomHireDate
	 *            the meetingRoomHireDate to set
	 */
	public void setMeetingRoomHireDate(Date meetingRoomHireDate) {
		this.meetingRoomHireDate = meetingRoomHireDate;
	}

}
