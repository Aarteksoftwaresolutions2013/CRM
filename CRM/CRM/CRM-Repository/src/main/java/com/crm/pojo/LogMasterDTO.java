package com.crm.pojo;

public class LogMasterDTO {

	private String UserName;

	private String Comment;

	private String entrydate;

	private String bookingid;

	/**
	 * @param userName
	 * @param comment
	 * @param entrydate
	 * @param bookingid
	 */
	public LogMasterDTO(String userName, String comment, String entrydate,
			String bookingid) {
		this.UserName = userName;
		this.Comment = comment;
		this.entrydate = entrydate;
		this.bookingid = bookingid;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.UserName = userName;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return Comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.Comment = comment;
	}

	/**
	 * @return the entrydate
	 */
	public String getEntrydate() {
		return entrydate;
	}

	/**
	 * @param entrydate
	 *            the entrydate to set
	 */
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	/**
	 * @return the bookingid
	 */
	public String getBookingid() {
		return bookingid;
	}

	/**
	 * @param bookingid
	 *            the bookingid to set
	 */
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

}