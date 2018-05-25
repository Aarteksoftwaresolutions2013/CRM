package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesperson")
public class SalesPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SId")
	private Integer sId;

	@Column(name = "SName")
	private String sName;

	@Column(name = "SurName")
	private String surName;

	@Column(name = "Email")
	private String email;

	@Column(name = "SkypeID")
	private String skypeID;

	@Column(name = "Status")
	private Boolean status;

	@Column(name = "EntryDate")
	private String entryDate;

	@Column(name = "DialByURL")
	private String dialByURL;

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
	 * @return the sName
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * @param sName
	 *            the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @param surName
	 *            the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
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
	 * @return the skypeID
	 */
	public String getSkypeID() {
		return skypeID;
	}

	/**
	 * @param skypeID
	 *            the skypeID to set
	 */
	public void setSkypeID(String skypeID) {
		this.skypeID = skypeID;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the dialByURL
	 */
	public String getDialByURL() {
		return dialByURL;
	}

	/**
	 * @param dialByURL
	 *            the dialByURL to set
	 */
	public void setDialByURL(String dialByURL) {
		this.dialByURL = dialByURL;
	}

}