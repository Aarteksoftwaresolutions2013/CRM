package com.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoicemettingroom")
public class InvoiceMeetingRoom {

	@Id
	@Column(name = "meetingroomid")
	private Integer meetingRoomId;

	@Column(name = "id")
	private Integer id;

	@Column(name = "costofmeetingroom")
	private Double costOfMeetingRoom;

	@Column(name = "coffee1time")
	private String coffee1Time;

	@Column(name = "coffee1cost")
	private Double coffee1Cost;

	@Column(name = "coffee2time")
	private String coffee2Time;

	@Column(name = "coffee2cost")
	private Double coffee2Cost;

	@Column(name = "coffee3time")
	private String coffee3Time;

	@Column(name = "coffee3cost")
	private Double coffee3Cost;

	@Column(name = "coffeeunlimited")
	private String coffeeUnlimited;

	@Column(name = "coffeeunlimitedcost")
	private Double coffeeUnlimitedCost;

	@Column(name = "lunch1time")
	private String lunch1Time;

	@Column(name = "lunch1cost")
	private Double lunch1Cost;

	@Column(name = "cancsurcharge")
	private Double cancSurCharge;

	@Column(name = "Discount")
	private Double discount;

	@Column(name = "vatamount")
	private Double vatAmount;

	@Column(name = "chkvat")
	private Integer chkVat;

	@Column(name = "companyaddr")
	private String companyAddr;

	@Column(name = "website")
	private String website;

	@Column(name = "innotes")
	private String inNotes;

	@Column(name = "Total")
	private Double total;

	@Column(name = "Venue")
	private String venue;

	@Column(name = "Date")
	private String date;

	@Column(name = "PoNo")
	private String poNo;

	@Column(name = "TrainCost")
	private Double trainCost;

	@Column(name = "TravelCost")
	private Double travelCost;

	@Column(name = "SoftCost")
	private Double softCost;

	@Column(name = "InvSentDate")
	private Date invSentDate;

	@Column(name = "PayReceiveDate")
	private Date payReceiveDate;

	@Column(name = "PayReceiveBy")
	private String payReceiveBy;

	@Column(name = "AdditionalNotes")
	private String additionalNotes;

	@Column(name = "InvSentToSchFinance")
	private Integer invSentToSchFinance;

	@Column(name = "BACSRefNo")
	private String bacsRefNo;

	@Column(name = "ReconsWithBank")
	private Integer reconsWithBank;

	@Column(name = "PaidByBacs")
	private String paidByBacs;

	@Column(name = "LTBacs")
	private Integer ltBacs;

	@Column(name = "CheckNo")
	private String checkNo;

	@Column(name = "AccNo")
	private String accNo;

	@Column(name = "SortCode")
	private String sortCode;

	@Column(name = "PaidByCheque")
	private String paidByCheque;

	@Column(name = "LTCheque")
	private Integer ltCheque;

	@Column(name = "ChequeDate")
	private Date chequeDate;

	@Column(name = "ChecqueDepDate")
	private Date checqueDepDate;

	@Column(name = "BankReceiveDate")
	private Date bankReceiveDate;

	@Column(name = "RaisedInvInPayPal")
	private Integer raisedInvInPayPal;

	@Column(name = "DateOfTransfToBank")
	private Date dateOfTransfToBank;

	@Column(name = "FlCost")
	private Double flCost;

	@Column(name = "TrainGoCost")
	private Double trainGoCost;

	@Column(name = "TaxiCost")
	private Double taxiCost;

	@Column(name = "HotelCost")
	private Double hotelCost;

	@Column(name = "paymenttype")
	private String paymentType;

	/**
	 * @return the meetingRoomId
	 */
	public Integer getMeetingRoomId() {
		return meetingRoomId;
	}

	/**
	 * @param meetingRoomId
	 *            the meetingRoomId to set
	 */
	public void setMeetingRoomId(Integer meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

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
	 * @return the costOfMeetingRoom
	 */
	public Double getCostOfMeetingRoom() {
		return costOfMeetingRoom;
	}

	/**
	 * @param costOfMeetingRoom
	 *            the costOfMeetingRoom to set
	 */
	public void setCostOfMeetingRoom(Double costOfMeetingRoom) {
		this.costOfMeetingRoom = costOfMeetingRoom;
	}

	/**
	 * @return the coffee1Time
	 */
	public String getCoffee1Time() {
		return coffee1Time;
	}

	/**
	 * @param coffee1Time
	 *            the coffee1Time to set
	 */
	public void setCoffee1Time(String coffee1Time) {
		this.coffee1Time = coffee1Time;
	}

	/**
	 * @return the coffee1Cost
	 */
	public Double getCoffee1Cost() {
		return coffee1Cost;
	}

	/**
	 * @param coffee1Cost
	 *            the coffee1Cost to set
	 */
	public void setCoffee1Cost(Double coffee1Cost) {
		this.coffee1Cost = coffee1Cost;
	}

	/**
	 * @return the coffee2Time
	 */
	public String getCoffee2Time() {
		return coffee2Time;
	}

	/**
	 * @param coffee2Time
	 *            the coffee2Time to set
	 */
	public void setCoffee2Time(String coffee2Time) {
		this.coffee2Time = coffee2Time;
	}

	/**
	 * @return the coffee2Cost
	 */
	public Double getCoffee2Cost() {
		return coffee2Cost;
	}

	/**
	 * @param coffee2Cost
	 *            the coffee2Cost to set
	 */
	public void setCoffee2Cost(Double coffee2Cost) {
		this.coffee2Cost = coffee2Cost;
	}

	/**
	 * @return the coffee3Time
	 */
	public String getCoffee3Time() {
		return coffee3Time;
	}

	/**
	 * @param coffee3Time
	 *            the coffee3Time to set
	 */
	public void setCoffee3Time(String coffee3Time) {
		this.coffee3Time = coffee3Time;
	}

	/**
	 * @return the coffee3Cost
	 */
	public Double getCoffee3Cost() {
		return coffee3Cost;
	}

	/**
	 * @param coffee3Cost
	 *            the coffee3Cost to set
	 */
	public void setCoffee3Cost(Double coffee3Cost) {
		this.coffee3Cost = coffee3Cost;
	}

	/**
	 * @return the coffeeUnlimited
	 */
	public String getCoffeeUnlimited() {
		return coffeeUnlimited;
	}

	/**
	 * @param coffeeUnlimited
	 *            the coffeeUnlimited to set
	 */
	public void setCoffeeUnlimited(String coffeeUnlimited) {
		this.coffeeUnlimited = coffeeUnlimited;
	}

	/**
	 * @return the coffeeUnlimitedCost
	 */
	public Double getCoffeeUnlimitedCost() {
		return coffeeUnlimitedCost;
	}

	/**
	 * @param coffeeUnlimitedCost
	 *            the coffeeUnlimitedCost to set
	 */
	public void setCoffeeUnlimitedCost(Double coffeeUnlimitedCost) {
		this.coffeeUnlimitedCost = coffeeUnlimitedCost;
	}

	/**
	 * @return the lunch1Time
	 */
	public String getLunch1Time() {
		return lunch1Time;
	}

	/**
	 * @param lunch1Time
	 *            the lunch1Time to set
	 */
	public void setLunch1Time(String lunch1Time) {
		this.lunch1Time = lunch1Time;
	}

	/**
	 * @return the lunch1Cost
	 */
	public Double getLunch1Cost() {
		return lunch1Cost;
	}

	/**
	 * @param lunch1Cost
	 *            the lunch1Cost to set
	 */
	public void setLunch1Cost(Double lunch1Cost) {
		this.lunch1Cost = lunch1Cost;
	}

	/**
	 * @return the cancSurCharge
	 */
	public Double getCancSurCharge() {
		return cancSurCharge;
	}

	/**
	 * @param cancSurCharge
	 *            the cancSurCharge to set
	 */
	public void setCancSurCharge(Double cancSurCharge) {
		this.cancSurCharge = cancSurCharge;
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
	 * @return the vatAmount
	 */
	public Double getVatAmount() {
		return vatAmount;
	}

	/**
	 * @param vatAmount
	 *            the vatAmount to set
	 */
	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	/**
	 * @return the chkVat
	 */
	public Integer getChkVat() {
		return chkVat;
	}

	/**
	 * @param chkVat
	 *            the chkVat to set
	 */
	public void setChkVat(Integer chkVat) {
		this.chkVat = chkVat;
	}

	/**
	 * @return the companyAddr
	 */
	public String getCompanyAddr() {
		return companyAddr;
	}

	/**
	 * @param companyAddr
	 *            the companyAddr to set
	 */
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the inNotes
	 */
	public String getInNotes() {
		return inNotes;
	}

	/**
	 * @param inNotes
	 *            the inNotes to set
	 */
	public void setInNotes(String inNotes) {
		this.inNotes = inNotes;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * @param venue
	 *            the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the poNo
	 */
	public String getPoNo() {
		return poNo;
	}

	/**
	 * @param poNo
	 *            the poNo to set
	 */
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	/**
	 * @return the trainCost
	 */
	public Double getTrainCost() {
		return trainCost;
	}

	/**
	 * @param trainCost
	 *            the trainCost to set
	 */
	public void setTrainCost(Double trainCost) {
		this.trainCost = trainCost;
	}

	/**
	 * @return the travelCost
	 */
	public Double getTravelCost() {
		return travelCost;
	}

	/**
	 * @param travelCost
	 *            the travelCost to set
	 */
	public void setTravelCost(Double travelCost) {
		this.travelCost = travelCost;
	}

	/**
	 * @return the softCost
	 */
	public Double getSoftCost() {
		return softCost;
	}

	/**
	 * @param softCost
	 *            the softCost to set
	 */
	public void setSoftCost(Double softCost) {
		this.softCost = softCost;
	}

	/**
	 * @return the invSentDate
	 */
	public Date getInvSentDate() {
		return invSentDate;
	}

	/**
	 * @param invSentDate
	 *            the invSentDate to set
	 */
	public void setInvSentDate(Date invSentDate) {
		this.invSentDate = invSentDate;
	}

	/**
	 * @return the payReceiveDate
	 */
	public Date getPayReceiveDate() {
		return payReceiveDate;
	}

	/**
	 * @param payReceiveDate
	 *            the payReceiveDate to set
	 */
	public void setPayReceiveDate(Date payReceiveDate) {
		this.payReceiveDate = payReceiveDate;
	}

	/**
	 * @return the payReceiveBy
	 */
	public String getPayReceiveBy() {
		return payReceiveBy;
	}

	/**
	 * @param payReceiveBy
	 *            the payReceiveBy to set
	 */
	public void setPayReceiveBy(String payReceiveBy) {
		this.payReceiveBy = payReceiveBy;
	}

	/**
	 * @return the additionalNotes
	 */
	public String getAdditionalNotes() {
		return additionalNotes;
	}

	/**
	 * @param additionalNotes
	 *            the additionalNotes to set
	 */
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	/**
	 * @return the invSentToSchFinance
	 */
	public Integer getInvSentToSchFinance() {
		return invSentToSchFinance;
	}

	/**
	 * @param invSentToSchFinance
	 *            the invSentToSchFinance to set
	 */
	public void setInvSentToSchFinance(Integer invSentToSchFinance) {
		this.invSentToSchFinance = invSentToSchFinance;
	}

	/**
	 * @return the bacsRefNo
	 */
	public String getBacsRefNo() {
		return bacsRefNo;
	}

	/**
	 * @param bacsRefNo
	 *            the bacsRefNo to set
	 */
	public void setBacsRefNo(String bacsRefNo) {
		this.bacsRefNo = bacsRefNo;
	}

	/**
	 * @return the reconsWithBank
	 */
	public Integer getReconsWithBank() {
		return reconsWithBank;
	}

	/**
	 * @param reconsWithBank
	 *            the reconsWithBank to set
	 */
	public void setReconsWithBank(Integer reconsWithBank) {
		this.reconsWithBank = reconsWithBank;
	}

	/**
	 * @return the paidByBacs
	 */
	public String getPaidByBacs() {
		return paidByBacs;
	}

	/**
	 * @param paidByBacs
	 *            the paidByBacs to set
	 */
	public void setPaidByBacs(String paidByBacs) {
		this.paidByBacs = paidByBacs;
	}

	/**
	 * @return the ltBacs
	 */
	public Integer getLtBacs() {
		return ltBacs;
	}

	/**
	 * @param ltBacs
	 *            the ltBacs to set
	 */
	public void setLtBacs(Integer ltBacs) {
		this.ltBacs = ltBacs;
	}

	/**
	 * @return the checkNo
	 */
	public String getCheckNo() {
		return checkNo;
	}

	/**
	 * @param checkNo
	 *            the checkNo to set
	 */
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * @param accNo
	 *            the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * @return the sortCode
	 */
	public String getSortCode() {
		return sortCode;
	}

	/**
	 * @param sortCode
	 *            the sortCode to set
	 */
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	/**
	 * @return the paidByCheque
	 */
	public String getPaidByCheque() {
		return paidByCheque;
	}

	/**
	 * @param paidByCheque
	 *            the paidByCheque to set
	 */
	public void setPaidByCheque(String paidByCheque) {
		this.paidByCheque = paidByCheque;
	}

	/**
	 * @return the ltCheque
	 */
	public Integer getLtCheque() {
		return ltCheque;
	}

	/**
	 * @param ltCheque
	 *            the ltCheque to set
	 */
	public void setLtCheque(Integer ltCheque) {
		this.ltCheque = ltCheque;
	}

	/**
	 * @return the chequeDate
	 */
	public Date getChequeDate() {
		return chequeDate;
	}

	/**
	 * @param chequeDate
	 *            the chequeDate to set
	 */
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	/**
	 * @return the checqueDepDate
	 */
	public Date getChecqueDepDate() {
		return checqueDepDate;
	}

	/**
	 * @param checqueDepDate
	 *            the checqueDepDate to set
	 */
	public void setChecqueDepDate(Date checqueDepDate) {
		this.checqueDepDate = checqueDepDate;
	}

	/**
	 * @return the bankReceiveDate
	 */
	public Date getBankReceiveDate() {
		return bankReceiveDate;
	}

	/**
	 * @param bankReceiveDate
	 *            the bankReceiveDate to set
	 */
	public void setBankReceiveDate(Date bankReceiveDate) {
		this.bankReceiveDate = bankReceiveDate;
	}

	/**
	 * @return the raisedInvInPayPal
	 */
	public Integer getRaisedInvInPayPal() {
		return raisedInvInPayPal;
	}

	/**
	 * @param raisedInvInPayPal
	 *            the raisedInvInPayPal to set
	 */
	public void setRaisedInvInPayPal(Integer raisedInvInPayPal) {
		this.raisedInvInPayPal = raisedInvInPayPal;
	}

	/**
	 * @return the dateOfTransfToBank
	 */
	public Date getDateOfTransfToBank() {
		return dateOfTransfToBank;
	}

	/**
	 * @param dateOfTransfToBank
	 *            the dateOfTransfToBank to set
	 */
	public void setDateOfTransfToBank(Date dateOfTransfToBank) {
		this.dateOfTransfToBank = dateOfTransfToBank;
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
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

}
