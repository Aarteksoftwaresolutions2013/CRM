package com.crm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@Column(name = "BId")
	private Integer bId;

	@Column(name = "Title")
	private String title;

	@Column(name = "FName")
	private String fName;

	@Column(name = "ScName")
	private String scName;

	@Column(name = "Training")
	private Integer training;

	@Column(name = "DelegNum")
	private Integer delegNum;

	@Column(name = "2ndDeleg")
	private String secondDeleg;

	@Column(name = "3rdDeleg")
	private String thirdDeleg;

	@Column(name = "TrLocation")
	private Integer trLocation;

	@Column(name = "Email")
	private String email;

	@Column(name = "InvEmail")
	private String invEmail;

	@Column(name = "Tel")
	private String tel;

	@Column(name = "TrDate")
	private Date trDate;

	@Transient
	private String trDateDummy;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "4thDeleg")
	private String fourthDeleg;

	@Column(name = "SubmitDateTime")
	private String submitDateTime;

	@Column(name = "2ndDelegEmail")
	private String secondDelegEmail;

	@Column(name = "3rdDelegEmail")
	private String thirdDelegEmail;

	@Column(name = "4thDelegEmail")
	private String fourthDelegEmail;

	@Column(name = "CallBackTime")
	private String callBackTime;

	@Column(name = "StartTime")
	private String startTime;

	@Column(name = "EndTime")
	private String endTime;

	@Column(name = "FlCost")
	private Double flCost;

	@Column(name = "TrainGoCost")
	private Double trainGoCost;

	@Column(name = "TaxiCost")
	private Double taxiCost;

	@Column(name = "HotelCost")
	private Double hotelCost;

	@Column(name = "BookingPhase")
	private String bookingPhase;

	@Column(name = "Consultant")
	private String consultant;

	@Column(name = "hear")
	private String hear;

	@Column(name = "hear_other")
	private String hearOther;

	@Column(name = "SESSION")
	private String bookingSession;

	@Column(name = "reminder")
	private String reminder;

	@Column(name = "bookinguser")
	private String bookingUser;

	@Column(name = "calleduser")
	private String calledUser;

	@Column(name = "calledDate")
	private String calledDate;

	@Column(name = "callerNotes")
	private String callerNotes;

	@Column(name = "typeOfCall")
	private String typeOfCall;

	@Column(name = "SId")
	private Integer sId;

	@Column(name = "demoDate")
	private String demoDate;

	@Transient
	private String trainingName;

	@Transient
	private String trainingLocation;

	/**
	 * @return the bId
	 */
	public Integer getbId() {
		return bId;
	}

	/**
	 * @param bId
	 *            the bId to set
	 */
	public void setbId(Integer bId) {
		this.bId = bId;
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
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the scName
	 */
	public String getScName() {
		return scName;
	}

	/**
	 * @param scName
	 *            the scName to set
	 */
	public void setScName(String scName) {
		this.scName = scName;
	}

	/**
	 * @return the training
	 */
	public Integer getTraining() {
		return training;
	}

	/**
	 * @param training
	 *            the training to set
	 */
	public void setTraining(Integer training) {
		this.training = training;
	}

	/**
	 * @return the delegNum
	 */
	public Integer getDelegNum() {
		return delegNum;
	}

	/**
	 * @param delegNum
	 *            the delegNum to set
	 */
	public void setDelegNum(Integer delegNum) {
		this.delegNum = delegNum;
	}

	/**
	 * @return the secondDeleg
	 */
	public String getSecondDeleg() {
		return secondDeleg;
	}

	/**
	 * @param secondDeleg
	 *            the secondDeleg to set
	 */
	public void setSecondDeleg(String secondDeleg) {
		this.secondDeleg = secondDeleg;
	}

	/**
	 * @return the thirdDeleg
	 */
	public String getThirdDeleg() {
		return thirdDeleg;
	}

	/**
	 * @param thirdDeleg
	 *            the thirdDeleg to set
	 */
	public void setThirdDeleg(String thirdDeleg) {
		this.thirdDeleg = thirdDeleg;
	}

	/**
	 * @return the trLocation
	 */
	public Integer getTrLocation() {
		return trLocation;
	}

	/**
	 * @param trLocation
	 *            the trLocation to set
	 */
	public void setTrLocation(Integer trLocation) {
		this.trLocation = trLocation;
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
	 * @return the invEmail
	 */
	public String getInvEmail() {
		return invEmail;
	}

	/**
	 * @param invEmail
	 *            the invEmail to set
	 */
	public void setInvEmail(String invEmail) {
		this.invEmail = invEmail;
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
	 * @return the trDate
	 */
	public Date getTrDate() {
		return trDate;
	}

	/**
	 * @param trDate
	 *            the trDate to set
	 */
	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}

	/**
	 * @return the trDateDummy
	 */
	public String getTrDateDummy() {
		return trDateDummy;
	}

	/**
	 * @param trDateDummy
	 *            the trDateDummy to set
	 */
	public void setTrDateDummy(String trDateDummy) {
		this.trDateDummy = trDateDummy;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the fourthDeleg
	 */
	public String getFourthDeleg() {
		return fourthDeleg;
	}

	/**
	 * @param fourthDeleg
	 *            the fourthDeleg to set
	 */
	public void setFourthDeleg(String fourthDeleg) {
		this.fourthDeleg = fourthDeleg;
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
	 * @return the thirdDelegEmail
	 */
	public String getThirdDelegEmail() {
		return thirdDelegEmail;
	}

	/**
	 * @param thirdDelegEmail
	 *            the thirdDelegEmail to set
	 */
	public void setThirdDelegEmail(String thirdDelegEmail) {
		this.thirdDelegEmail = thirdDelegEmail;
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
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the flCost
	 */
	public Double getFlCost() {
		return flCost;
	}

	/**
	 * @param flCost
	 *            the flCost to set
	 */
	public void setFlCost(Double flCost) {
		this.flCost = flCost;
	}

	/**
	 * @return the trainGoCost
	 */
	public Double getTrainGoCost() {
		return trainGoCost;
	}

	/**
	 * @param trainGoCost
	 *            the trainGoCost to set
	 */
	public void setTrainGoCost(Double trainGoCost) {
		this.trainGoCost = trainGoCost;
	}

	/**
	 * @return the taxiCost
	 */
	public Double getTaxiCost() {
		return taxiCost;
	}

	/**
	 * @param taxiCost
	 *            the taxiCost to set
	 */
	public void setTaxiCost(Double taxiCost) {
		this.taxiCost = taxiCost;
	}

	/**
	 * @return the hotelCost
	 */
	public Double getHotelCost() {
		return hotelCost;
	}

	/**
	 * @param hotelCost
	 *            the hotelCost to set
	 */
	public void setHotelCost(Double hotelCost) {
		this.hotelCost = hotelCost;
	}

	/**
	 * @return the bookingPhase
	 */
	public String getBookingPhase() {
		return bookingPhase;
	}

	/**
	 * @param bookingPhase
	 *            the bookingPhase to set
	 */
	public void setBookingPhase(String bookingPhase) {
		this.bookingPhase = bookingPhase;
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
	 * @return the bookingSession
	 */
	public String getBookingSession() {
		return bookingSession;
	}

	/**
	 * @param bookingSession
	 *            the bookingSession to set
	 */
	public void setBookingSession(String bookingSession) {
		this.bookingSession = bookingSession;
	}

	/**
	 * @return the reminder
	 */
	public String getReminder() {
		return reminder;
	}

	/**
	 * @param reminder
	 *            the reminder to set
	 */
	public void setReminder(String reminder) {
		this.reminder = reminder;
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
	 * @return the calledUser
	 */
	public String getCalledUser() {
		return calledUser;
	}

	/**
	 * @param calledUser
	 *            the calledUser to set
	 */
	public void setCalledUser(String calledUser) {
		this.calledUser = calledUser;
	}

	/**
	 * @return the calledDate
	 */
	public String getCalledDate() {
		return calledDate;
	}

	/**
	 * @param calledDate
	 *            the calledDate to set
	 */
	public void setCalledDate(String calledDate) {
		this.calledDate = calledDate;
	}

	/**
	 * @return the callerNotes
	 */
	public String getCallerNotes() {
		return callerNotes;
	}

	/**
	 * @param callerNotes
	 *            the callerNotes to set
	 */
	public void setCallerNotes(String callerNotes) {
		this.callerNotes = callerNotes;
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
	 * @return the demoDate
	 */
	public String getDemoDate() {
		return demoDate;
	}

	/**
	 * @param demoDate
	 *            the demoDate to set
	 */
	public void setDemoDate(String demoDate) {
		this.demoDate = demoDate;
	}

	/**
	 * @return the trainingName
	 */
	public String getTrainingName() {
		return trainingName;
	}

	/**
	 * @param trainingName
	 *            the trainingName to set
	 */
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	/**
	 * @return the trainingLocation
	 */
	public String getTrainingLocation() {
		return trainingLocation;
	}

	/**
	 * @param trainingLocation
	 *            the trainingLocation to set
	 */
	public void setTrainingLocation(String trainingLocation) {
		this.trainingLocation = trainingLocation;
	}

}