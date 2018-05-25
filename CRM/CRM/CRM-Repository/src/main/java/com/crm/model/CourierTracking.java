package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "couriertracking")
public class CourierTracking {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "bid")
	private Integer bId;

	@Column(name = "senddate")
	private String sendDate;

	@Column(name = "trackingid")
	private String trackingId;

	@Column(name = "couriercompany")
	private String courierCompany;

	@Column(name = "websitelink")
	private String websiteLink;

	@Column(name = "deliveryconfirmedby")
	private String deliveryConfirmedBy;

	@Column(name = "receiveddateandtime")
	private String receivedDateAndTime;

	@Column(name = "comments")
	private String comments;

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
	 * @return the sendDate
	 */
	public String getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate
	 *            the sendDate to set
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * @return the courierCompany
	 */
	public String getCourierCompany() {
		return courierCompany;
	}

	/**
	 * @param courierCompany
	 *            the courierCompany to set
	 */
	public void setCourierCompany(String courierCompany) {
		this.courierCompany = courierCompany;
	}

	/**
	 * @return the websiteLink
	 */
	public String getWebsiteLink() {
		return websiteLink;
	}

	/**
	 * @param websiteLink
	 *            the websiteLink to set
	 */
	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	/**
	 * @return the deliveryConfirmedBy
	 */
	public String getDeliveryConfirmedBy() {
		return deliveryConfirmedBy;
	}

	/**
	 * @param deliveryConfirmedBy
	 *            the deliveryConfirmedBy to set
	 */
	public void setDeliveryConfirmedBy(String deliveryConfirmedBy) {
		this.deliveryConfirmedBy = deliveryConfirmedBy;
	}

	/**
	 * @return the receivedDateAndTime
	 */
	public String getReceivedDateAndTime() {
		return receivedDateAndTime;
	}

	/**
	 * @param receivedDateAndTime
	 *            the receivedDateAndTime to set
	 */
	public void setReceivedDateAndTime(String receivedDateAndTime) {
		this.receivedDateAndTime = receivedDateAndTime;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}