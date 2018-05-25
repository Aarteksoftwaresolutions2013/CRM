package com.crm.pojo;

import static com.crm.utils.DateUtils.convertYYYY_MM_DDToDD_MM_YYYYDateFormat;

public class LocationDTO {

	private String status;
	private Integer delegateNum;
	private String location;
	private String shortName;
	private String trDateDummy;
	private String tDate;
	private String training;
	private Integer bId;
	private String bookingPhase;
	private String backgroudColor;

	public LocationDTO() {

	}

	public LocationDTO(Object[] object) {
		this.setbId(0);
		this.setLocation((String) object[0]);
		this.setTrDateDummy(object[1] != null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[1].toString()) : null);
		this.settDate(object[1] != null ? object[1].toString() : null);
		this.setShortName((String) object[2]);
		this.setDelegateNum(0);
		this.setStatus("temp");
		this.setBookingPhase("temp");

	}

	public LocationDTO(Object[] object, boolean status) {
		this.setTrDateDummy(object[7] != null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[7].toString()) : null);
		this.settDate(object[7] != null ? object[7].toString() : null);
		this.setDelegateNum((Integer) object[12]);
		this.setLocation((String) object[10]);
		this.setbId((Integer) object[0]);
		this.setBookingPhase((String) object[4]);
		this.setStatus((String) object[11]);

	}

	/**
	 * @return the backgroudColor
	 */
	public String getBackgroudColor() {
		return backgroudColor;
	}

	/**
	 * @param backgroudColor
	 *            the backgroudColor to set
	 */
	public void setBackgroudColor(String backgroudColor) {
		this.backgroudColor = backgroudColor;
	}

	/**
	 * @return the tDate
	 */
	public String gettDate() {
		return tDate;
	}

	/**
	 * @param tDate
	 *            the tDate to set
	 */
	public void settDate(String tDate) {
		this.tDate = tDate;
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
	 * @return the delegateNum
	 */
	public Integer getDelegateNum() {
		return delegateNum;
	}

	/**
	 * @param delegateNum
	 *            the delegateNum to set
	 */
	public void setDelegateNum(Integer delegateNum) {
		this.delegateNum = delegateNum;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	 * @return the trainingLocation
	 */
	public String getTraining() {
		return training;
	}

	/**
	 * @param trainingLocation
	 *            the trainingLocation to set
	 */
	public void setTraining(String training) {
		this.training = training;
	}

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

}