package com.crm.pojo;

import javax.persistence.Transient;

public class DashboardFilterDTO {

	private Integer limit;

	private Integer trainingLocation;

	private String year;

	private String dates;

	private String trainingBookingEnquiry;
	
	private String schoolNames;

	private String fullName;

	private Integer course;

	private String courseBookingEnquiry;

	private String software;

	private String softwareBookingEnquiry;

	private String months;

	private String notConfirmedLocations;

	private String notConfirmedInHouse;

	private String payedBy;

	private Integer invoiceNumber;

	private String email;

	private String telephone;

	private Integer status;

	private Integer noOfItems;

	private Integer pageNo;
	
	@Transient
	private String invoiceNumberDummy;
	
	

	
	/**
	 * @return the schoolNames
	 */
	public String getSchoolNames() {
		return schoolNames;
	}

	/**
	 * @param schoolNames the schoolNames to set
	 */
	public void setSchoolNames(String schoolNames) {
		this.schoolNames = schoolNames;
	}

	/**
	 * @return the invoiceNumberDummy
	 */
	public String getInvoiceNumberDummy() {
		return invoiceNumberDummy;
	}

	/**
	 * @param invoiceNumberDummy the invoiceNumberDummy to set
	 */
	public void setInvoiceNumberDummy(String invoiceNumberDummy) {
		this.invoiceNumberDummy = invoiceNumberDummy;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the trainingLocation
	 */
	public Integer getTrainingLocation() {
		return trainingLocation;
	}

	/**
	 * @param trainingLocation
	 *            the trainingLocation to set
	 */
	public void setTrainingLocation(Integer trainingLocation) {
		this.trainingLocation = trainingLocation;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the dates
	 */
	public String getDates() {
		return dates;
	}

	/**
	 * @param dates
	 *            the dates to set
	 */
	public void setDates(String dates) {
		this.dates = dates;
	}

	/**
	 * @return the trainingBookingEnquiry
	 */
	public String getTrainingBookingEnquiry() {
		return trainingBookingEnquiry;
	}

	/**
	 * @param trainingBookingEnquiry
	 *            the trainingBookingEnquiry to set
	 */
	public void setTrainingBookingEnquiry(String trainingBookingEnquiry) {
		this.trainingBookingEnquiry = trainingBookingEnquiry;
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
	 * @return the course
	 */
	public Integer getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Integer course) {
		this.course = course;
	}

	/**
	 * @return the courseBookingEnquiry
	 */
	public String getCourseBookingEnquiry() {
		return courseBookingEnquiry;
	}

	/**
	 * @param courseBookingEnquiry
	 *            the courseBookingEnquiry to set
	 */
	public void setCourseBookingEnquiry(String courseBookingEnquiry) {
		this.courseBookingEnquiry = courseBookingEnquiry;
	}

	/**
	 * @return the software
	 */
	public String getSoftware() {
		return software;
	}

	/**
	 * @param software
	 *            the software to set
	 */
	public void setSoftware(String software) {
		this.software = software;
	}

	/**
	 * @return the softwareBookingEnquiry
	 */
	public String getSoftwareBookingEnquiry() {
		return softwareBookingEnquiry;
	}

	/**
	 * @param softwareBookingEnquiry
	 *            the softwareBookingEnquiry to set
	 */
	public void setSoftwareBookingEnquiry(String softwareBookingEnquiry) {
		this.softwareBookingEnquiry = softwareBookingEnquiry;
	}

	/**
	 * @return the months
	 */
	public String getMonths() {
		return months;
	}

	/**
	 * @param months
	 *            the months to set
	 */
	public void setMonths(String months) {
		this.months = months;
	}

	/**
	 * @return the notConfirmedLocations
	 */
	public String getNotConfirmedLocations() {
		return notConfirmedLocations;
	}

	/**
	 * @param notConfirmedLocations
	 *            the notConfirmedLocations to set
	 */
	public void setNotConfirmedLocations(String notConfirmedLocations) {
		this.notConfirmedLocations = notConfirmedLocations;
	}

	/**
	 * @return the notConfirmedInHouse
	 */
	public String getNotConfirmedInHouse() {
		return notConfirmedInHouse;
	}

	/**
	 * @param notConfirmedInHouse
	 *            the notConfirmedInHouse to set
	 */
	public void setNotConfirmedInHouse(String notConfirmedInHouse) {
		this.notConfirmedInHouse = notConfirmedInHouse;
	}

	/**
	 * @return the payedBy
	 */
	public String getPayedBy() {
		return payedBy;
	}

	/**
	 * @param payedBy
	 *            the payedBy to set
	 */
	public void setPayedBy(String payedBy) {
		this.payedBy = payedBy;
	}

	/**
	 * @return the invoiceNumber
	 */
	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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
	 * @return the noOfItems
	 */
	public Integer getNoOfItems() {
		return noOfItems;
	}

	/**
	 * @param noOfItems
	 *            the noOfItems to set
	 */
	public void setNoOfItems(Integer noOfItems) {
		this.noOfItems = noOfItems;
	}

	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}