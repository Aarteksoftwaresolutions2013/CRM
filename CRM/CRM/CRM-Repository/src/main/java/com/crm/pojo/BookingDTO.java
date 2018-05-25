package com.crm.pojo;

public class BookingDTO {

	private Integer bId;

	private String fullName;

	private String schoolName;

	private String trDate;

	public BookingDTO() {
	}

	public BookingDTO(Object[] object) {
		fullName = ((String) object[0]);
		schoolName = ((String) object[1]);
	}

	public BookingDTO(Object[] object, boolean isStatus) {
		bId = ((Integer) object[0]);
		System.out.println("booking id ... " + bId);
		fullName = ((String) object[2]);
		System.out.println("fullName ... " + fullName);
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
	 * @return the trDate
	 */
	public String getTrDate() {
		return trDate;
	}

	/**
	 * @param trDate
	 *            the trDate to set
	 */
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}

	public Integer getbId() {
		return bId;
	}

	public void setbId(Integer bId) {
		this.bId = bId;
	}

}