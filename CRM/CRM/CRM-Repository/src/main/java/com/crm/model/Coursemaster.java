package com.crm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coursesmaster")
public class Coursemaster {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "tel_num")
	private String telNum;

	@Column(name = "email")
	private String email;

	@Column(name = "enquiry")
	private String enquiry;

	@Column(name = "c_name")
	private String cName;

	@Column(name = "t_loc")
	private String tLoc;

	@Column(name = "hear")
	private String hear;

	@Column(name = "hear_other")
	private String hearOther;

	@Column(name = "seconddelegname")
	private String secondDelegName;

	@Column(name = "seconddelegemail")
	private String secondDelegEmail;

	@Column(name = "thirddelegname")
	private String thirdDelegName;

	@Column(name = "thirddelegemail")
	private String thirddelegemail;

	@Column(name = "fourthdelegname")
	private String fourthDelegName;

	@Column(name = "fourthdelegemail")
	private String fourthDelegEmail;

	@Column(name = "SubmitDateTime")
	private Date SubmitDateTime;

	@Column(name = "title")
	private String title;

	@Column(name = "t_date")
	private Date tDate;

	@Column(name = "t_loc_date")
	private Date tLocDate;

	@Column(name = "numberofdelegates")
	private String numberOfDelegates;

	@Column(name = "callbacktime")
	private Date callBackTime;

	@Column(name = "discount")
	private Double discount;

	@Column(name = "schoolname")
	private String schoolName;

	@Column(name = "message")
	private String message;

	@Column(name = "booking_user")
	private String bookingUser;

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
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * @param telNum
	 *            the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
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
	 * @return the enquiry
	 */
	public String getEnquiry() {
		return enquiry;
	}

	/**
	 * @param enquiry
	 *            the enquiry to set
	 */
	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}

	/**
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}

	/**
	 * @param cName
	 *            the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}

	/**
	 * @return the tLoc
	 */
	public String gettLoc() {
		return tLoc;
	}

	/**
	 * @param tLoc
	 *            the tLoc to set
	 */
	public void settLoc(String tLoc) {
		this.tLoc = tLoc;
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
	 * @return the secondDelegName
	 */
	public String getSecondDelegName() {
		return secondDelegName;
	}

	/**
	 * @param secondDelegName
	 *            the secondDelegName to set
	 */
	public void setSecondDelegName(String secondDelegName) {
		this.secondDelegName = secondDelegName;
	}

	/**
	 * @return the secondDelegEmail
	 */
	public String getSecondDelegEmail() {
		return secondDelegEmail;
	}

	/**
	 * @param secondDelegEmail
	 *            the secondDelegEmail to set
	 */
	public void setSecondDelegEmail(String secondDelegEmail) {
		this.secondDelegEmail = secondDelegEmail;
	}

	/**
	 * @return the thirdDelegName
	 */
	public String getThirdDelegName() {
		return thirdDelegName;
	}

	/**
	 * @param thirdDelegName
	 *            the thirdDelegName to set
	 */
	public void setThirdDelegName(String thirdDelegName) {
		this.thirdDelegName = thirdDelegName;
	}

	/**
	 * @return the thirddelegemail
	 */
	public String getThirddelegemail() {
		return thirddelegemail;
	}

	/**
	 * @param thirddelegemail
	 *            the thirddelegemail to set
	 */
	public void setThirddelegemail(String thirddelegemail) {
		this.thirddelegemail = thirddelegemail;
	}

	/**
	 * @return the fourthDelegName
	 */
	public String getFourthDelegName() {
		return fourthDelegName;
	}

	/**
	 * @param fourthDelegName
	 *            the fourthDelegName to set
	 */
	public void setFourthDelegName(String fourthDelegName) {
		this.fourthDelegName = fourthDelegName;
	}

	/**
	 * @return the fourthDelegEmail
	 */
	public String getFourthDelegEmail() {
		return fourthDelegEmail;
	}

	/**
	 * @param fourthDelegEmail
	 *            the fourthDelegEmail to set
	 */
	public void setFourthDelegEmail(String fourthDelegEmail) {
		this.fourthDelegEmail = fourthDelegEmail;
	}

	/**
	 * @return the submitDateTime
	 */
	public Date getSubmitDateTime() {
		return SubmitDateTime;
	}

	/**
	 * @param submitDateTime
	 *            the submitDateTime to set
	 */
	public void setSubmitDateTime(Date submitDateTime) {
		SubmitDateTime = submitDateTime;
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
	 * @return the tDate
	 */
	public Date gettDate() {
		return tDate;
	}

	/**
	 * @param tDate
	 *            the tDate to set
	 */
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	/**
	 * @return the tLocDate
	 */
	public Date gettLocDate() {
		return tLocDate;
	}

	/**
	 * @param tLocDate
	 *            the tLocDate to set
	 */
	public void settLocDate(Date tLocDate) {
		this.tLocDate = tLocDate;
	}

	/**
	 * @return the numberOfDelegates
	 */
	public String getNumberOfDelegates() {
		return numberOfDelegates;
	}

	/**
	 * @param numberOfDelegates
	 *            the numberOfDelegates to set
	 */
	public void setNumberOfDelegates(String numberOfDelegates) {
		this.numberOfDelegates = numberOfDelegates;
	}

	/**
	 * @return the callBackTime
	 */
	public Date getCallBackTime() {
		return callBackTime;
	}

	/**
	 * @param callBackTime
	 *            the callBackTime to set
	 */
	public void setCallBackTime(Date callBackTime) {
		this.callBackTime = callBackTime;
	}

	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName
	 *            the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

}
