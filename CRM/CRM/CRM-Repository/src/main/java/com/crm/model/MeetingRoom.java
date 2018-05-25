package com.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meetingroom")
public class MeetingRoom {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "tel")
	private String tel;

	@Column(name = "companyname")
	private String companynName;

	@Column(name = "meetingroomhiredate")
	private Date meetingRoomHireDate;

	@Column(name = "meetingroomhireenddate")
	private Date meetingRoomHireEndDate;

	@Column(name = "startingtime")
	private String startingTime;

	@Column(name = "endingtime")
	private String endingTime;

	@Column(name = "numberofdelegates")
	private Integer numberOfDelegates;

	@Column(name = "labelname")
	private String labelName;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "booking_enquiry")
	private String bookingEnquiry;

	@Column(name = "duration")
	private String duration;

	@Column(name = "email")
	private String email;

	@Column(name = "dt")
	private Date dt;

	@Column(name = "submitdatetime")
	private String submitDateTime;

	@Column(name = "discount_code")
	private String discountCode;

	@Column(name = "message")
	private String message;

	@Column(name = "callbacktime")
	private String callBackTime;

	@Column(name = "coffeetime1")
	private String coffeeTime1;

	@Column(name = "coffeetime2")
	private String coffeeTime2;

	@Column(name = "coffeetime3")
	private String coffeeTime3;

	@Column(name = "iscoffeeunlimited")
	private Boolean isCoffeeUnlimited;

	@Column(name = "islunchrequired")
	private Boolean isLunchRequired;

	@Column(name = "Consultant")
	private String consultant;

	@Column(name = "lunchtime")
	private String lunchTime;

	@Column(name = "lunchinformation")
	private String lunchInformation;

	@Column(name = "sittingstyle")
	private String sittingStyle;

	@Column(name = "numberofcomputers")
	private Integer numberOfComputers;

	@Column(name = "sittingstylecomment")
	private String sittingStyleComment;

	@Column(name = "informationaboutthecourse")
	private String informationAboutTheCourse;

	@Column(name = "hear")
	private String hear;

	@Column(name = "hear_other")
	private String hearOther;

	@Column(name = "bookinguser")
	private String bookingUser;

	@Column(name = "SID")
	private Integer sId;

	@Column(name = "typeOfCall")
	private String typeOfCall;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the companynName
	 */
	public String getCompanynName() {
		return companynName;
	}

	/**
	 * @param companynName
	 *            the companynName to set
	 */
	public void setCompanynName(String companynName) {
		this.companynName = companynName;
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
	 * @return the startingTime
	 */
	public String getStartingTime() {
		return startingTime;
	}

	/**
	 * @param startingTime
	 *            the startingTime to set
	 */
	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	/**
	 * @return the endingTime
	 */
	public String getEndingTime() {
		return endingTime;
	}

	/**
	 * @param endingTime
	 *            the endingTime to set
	 */
	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	/**
	 * @return the numberOfDelegates
	 */
	public Integer getNumberOfDelegates() {
		return numberOfDelegates;
	}

	/**
	 * @param numberOfDelegates
	 *            the numberOfDelegates to set
	 */
	public void setNumberOfDelegates(Integer numberOfDelegates) {
		this.numberOfDelegates = numberOfDelegates;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName
	 *            the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dt
	 */
	public Date getDt() {
		return dt;
	}

	/**
	 * @param dt
	 *            the dt to set
	 */
	public void setDt(Date dt) {
		this.dt = dt;
	}

	/**
	 * @return the submitDateTime
	 */
	public String getSubmitDateTime() {
		return submitDateTime;
	}

	/**
	 * @param submitDateTime
	 *            the submitDateTime to set
	 */
	public void setSubmitDateTime(String submitDateTime) {
		this.submitDateTime = submitDateTime;
	}

	/**
	 * @return the discountCode
	 */
	public String getDiscountCode() {
		return discountCode;
	}

	/**
	 * @param discountCode
	 *            the discountCode to set
	 */
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the callBackTime
	 */
	public String getCallBackTime() {
		return callBackTime;
	}

	/**
	 * @param callBackTime
	 *            the callBackTime to set
	 */
	public void setCallBackTime(String callBackTime) {
		this.callBackTime = callBackTime;
	}

	/**
	 * @return the coffeeTime1
	 */
	public String getCoffeeTime1() {
		return coffeeTime1;
	}

	/**
	 * @param coffeeTime1
	 *            the coffeeTime1 to set
	 */
	public void setCoffeeTime1(String coffeeTime1) {
		this.coffeeTime1 = coffeeTime1;
	}

	/**
	 * @return the coffeeTime2
	 */
	public String getCoffeeTime2() {
		return coffeeTime2;
	}

	/**
	 * @param coffeeTime2
	 *            the coffeeTime2 to set
	 */
	public void setCoffeeTime2(String coffeeTime2) {
		this.coffeeTime2 = coffeeTime2;
	}

	/**
	 * @return the coffeeTime3
	 */
	public String getCoffeeTime3() {
		return coffeeTime3;
	}

	/**
	 * @param coffeeTime3
	 *            the coffeeTime3 to set
	 */
	public void setCoffeeTime3(String coffeeTime3) {
		this.coffeeTime3 = coffeeTime3;
	}

	/**
	 * @return the isCoffeeUnlimited
	 */
	public Boolean getIsCoffeeUnlimited() {
		return isCoffeeUnlimited;
	}

	/**
	 * @param isCoffeeUnlimited
	 *            the isCoffeeUnlimited to set
	 */
	public void setIsCoffeeUnlimited(Boolean isCoffeeUnlimited) {
		this.isCoffeeUnlimited = isCoffeeUnlimited;
	}

	/**
	 * @return the isLunchRequired
	 */
	public Boolean getIsLunchRequired() {
		return isLunchRequired;
	}

	/**
	 * @param isLunchRequired
	 *            the isLunchRequired to set
	 */
	public void setIsLunchRequired(Boolean isLunchRequired) {
		this.isLunchRequired = isLunchRequired;
	}

	/**
	 * @return the consultant
	 */
	public String getConsultant() {
		return consultant;
	}

	/**
	 * @param consultant
	 *            the consultant to set
	 */
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	/**
	 * @return the lunchTime
	 */
	public String getLunchTime() {
		return lunchTime;
	}

	/**
	 * @param lunchTime
	 *            the lunchTime to set
	 */
	public void setLunchTime(String lunchTime) {
		this.lunchTime = lunchTime;
	}

	/**
	 * @return the lunchInformation
	 */
	public String getLunchInformation() {
		return lunchInformation;
	}

	/**
	 * @param lunchInformation
	 *            the lunchInformation to set
	 */
	public void setLunchInformation(String lunchInformation) {
		this.lunchInformation = lunchInformation;
	}

	/**
	 * @return the sittingStyle
	 */
	public String getSittingStyle() {
		return sittingStyle;
	}

	/**
	 * @param sittingStyle
	 *            the sittingStyle to set
	 */
	public void setSittingStyle(String sittingStyle) {
		this.sittingStyle = sittingStyle;
	}

	/**
	 * @return the numberOfComputers
	 */
	public Integer getNumberOfComputers() {
		return numberOfComputers;
	}

	/**
	 * @param numberOfComputers
	 *            the numberOfComputers to set
	 */
	public void setNumberOfComputers(Integer numberOfComputers) {
		this.numberOfComputers = numberOfComputers;
	}

	/**
	 * @return the sittingStyleComment
	 */
	public String getSittingStyleComment() {
		return sittingStyleComment;
	}

	/**
	 * @param sittingStyleComment
	 *            the sittingStyleComment to set
	 */
	public void setSittingStyleComment(String sittingStyleComment) {
		this.sittingStyleComment = sittingStyleComment;
	}

	/**
	 * @return the informationAboutTheCourse
	 */
	public String getInformationAboutTheCourse() {
		return informationAboutTheCourse;
	}

	/**
	 * @param informationAboutTheCourse
	 *            the informationAboutTheCourse to set
	 */
	public void setInformationAboutTheCourse(String informationAboutTheCourse) {
		this.informationAboutTheCourse = informationAboutTheCourse;
	}

	/**
	 * @return the hear
	 */
	public String getHear() {
		return hear;
	}

	/**
	 * @param hear
	 *            the hear to set
	 */
	public void setHear(String hear) {
		this.hear = hear;
	}

	/**
	 * @return the hearOther
	 */
	public String getHearOther() {
		return hearOther;
	}

	/**
	 * @param hearOther
	 *            the hearOther to set
	 */
	public void setHearOther(String hearOther) {
		this.hearOther = hearOther;
	}

	/**
	 * @return the bookingUser
	 */
	public String getBookingUser() {
		return bookingUser;
	}

	/**
	 * @param bookingUser
	 *            the bookingUser to set
	 */
	public void setBookingUser(String bookingUser) {
		this.bookingUser = bookingUser;
	}

	/**
	 * @return the sId
	 */
	public Integer getsId() {
		return sId;
	}

	/**
	 * @param sId
	 *            the sId to set
	 */
	public void setsId(Integer sId) {
		this.sId = sId;
	}

	/**
	 * @return the typeOfCall
	 */
	public String getTypeOfCall() {
		return typeOfCall;
	}

	/**
	 * @param typeOfCall
	 *            the typeOfCall to set
	 */
	public void setTypeOfCall(String typeOfCall) {
		this.typeOfCall = typeOfCall;
	}

}
