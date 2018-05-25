package com.crm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="donation")
public class Donation {
	
	@Id
	@Column(name="id")
	  private Integer id;
	
	@Column(name="name")
	  private String name;
	
	@Column(name="companyname")
	  private String companyName;
	
	@Column(name="tel")
	  private String tel;
	
	@Column(name="email")
	  private String email;
	
	@Column(name="donationinfo")
	  private String donationInfo;
	
	@Column(name="collectiondate")
	  private String collectionDate;
	
	@Column(name="submitdatetime")
	  private String submitDateTime;
	
	@Column(name="certificateposted")
	  private String certificatePosted;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the donationInfo
	 */
	public String getDonationInfo() {
		return donationInfo;
	}

	/**
	 * @param donationInfo the donationInfo to set
	 */
	public void setDonationInfo(String donationInfo) {
		this.donationInfo = donationInfo;
	}

	/**
	 * @return the collectionDate
	 */
	public String getCollectionDate() {
		return collectionDate;
	}

	/**
	 * @param collectionDate the collectionDate to set
	 */
	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

	/**
	 * @return the submitDateTime
	 */
	public String getSubmitDateTime() {
		return submitDateTime;
	}

	/**
	 * @param submitDateTime the submitDateTime to set
	 */
	public void setSubmitDateTime(String submitDateTime) {
		this.submitDateTime = submitDateTime;
	}

	/**
	 * @return the certificatePosted
	 */
	public String getCertificatePosted() {
		return certificatePosted;
	}

	/**
	 * @param certificatePosted the certificatePosted to set
	 */
	public void setCertificatePosted(String certificatePosted) {
		this.certificatePosted = certificatePosted;
	}

}
