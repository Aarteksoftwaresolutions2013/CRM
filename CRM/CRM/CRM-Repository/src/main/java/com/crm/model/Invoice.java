package com.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Invoice")
public class Invoice {

	@Id
	@Column(name = "BId")
	private Integer bId;

	@Column(name = "Id")
	private Integer id;

	@Column(name = "paymenttype")
	private String paymentType;

	@Column(name = "additionaldelegates")
	private Double additionalDelegates;

	@Column(name = "VatAmount")
	private Double vatAmount;

	@Column(name = "chkvat")
	private Boolean chkvat;

	@Column(name = "1stDelegFee")
	private Double firstDelegFee;

	@Column(name = "2ndDelegFee")
	private Double secondDelegFee;

	@Column(name = "3rdDelegFee")
	private Double thirdDelegFee;

	@Column(name = "Discount")
	private Double discount;

	@Column(name = "cancsurcharge")
	private Double cancsurCharge;

	@Column(name = "SchAddr")
	private String schAddr;

	@Column(name = "Website")
	private String website;

	@Column(name = "Total")
	private Double total;

	@Column(name = "4thDelegFee")
	private Double fourthDelegFee;

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

	@Column(name = "SoftSetup")
	private Double softSetup;

	@Column(name = "InvSentDate")
	private Date invSentDate;

	@Column(name = "PayReceiveDate")
	private Date payReceiveDate;

	@Transient
	private String payReceiveDateDummy;

	@Column(name = "PayReceiveBy")
	private String payReceiveBy;

	@Column(name = "AdditionalNotes")
	private String additionalNotes;

	@Column(name = "InvSentToSchFinance")
	private Boolean invSentToSchFinance;

	@Column(name = "BACSRefNo")
	private String bACSRefNo;

	@Column(name = "ReconsWithBank")
	private Boolean reconsWithBank;

	@Column(name = "PaidByBacs")
	private String paidByBacs;

	@Column(name = "LTBacs")
	private Boolean lTBacs;

	@Column(name = "CheckNo")
	private String checkNo;

	@Column(name = "AccNo")
	private String accNo;

	@Column(name = "SortCode")
	private String sortCode;

	@Column(name = "PaidByCheque")
	private String paidByCheque;

	@Column(name = "LTCheque")
	private Boolean lTCheque;

	@Column(name = "ChequeDate")
	private Date chequeDate;

	@Transient
	private String chequeDateDummy;

	@Column(name = "ChecqueDepDate")
	private Date checqueDepDate;

	@Transient
	private String checqueDepDateDummy;

	@Column(name = "BankReceiveDate")
	private Date bankReceiveDate;

	@Transient
	private String bankReceiveDateDummy;

	@Column(name = "RaisedInvInPayPal")
	private Boolean raisedInvInPayPal;

	@Column(name = "DateOfTransfToBank")
	private Date dateOfTransfToBank;

	@Transient
	private String dateOfTransfToBankDummy;

	@Column(name = "FlCost")
	private Double flCost;

	@Column(name = "TrainGoCost")
	private Double trainGoCost;

	@Column(name = "TaxiCost")
	private Double taxiCost;

	@Column(name = "HotelCost")
	private Double hotelCost;

	@Column(name = "LT")
	private Boolean LT;

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

	/**
	 * @return the checqueDepDateDummy
	 */
	public String getChecqueDepDateDummy() {
		return checqueDepDateDummy;
	}

	/**
	 * @param checqueDepDateDummy
	 *            the checqueDepDateDummy to set
	 */
	public void setChecqueDepDateDummy(String checqueDepDateDummy) {
		this.checqueDepDateDummy = checqueDepDateDummy;
	}

	/**
	 * @return the payReceiveDateDummy
	 */
	public String getPayReceiveDateDummy() {
		return payReceiveDateDummy;
	}

	/**
	 * @param payReceiveDateDummy
	 *            the payReceiveDateDummy to set
	 */
	public void setPayReceiveDateDummy(String payReceiveDateDummy) {
		this.payReceiveDateDummy = payReceiveDateDummy;
	}

	/**
	 * @return the additionalDelegates
	 */
	public Double getAdditionalDelegates() {
		return additionalDelegates;
	}

	/**
	 * @param additionalDelegates
	 *            the additionalDelegates to set
	 */
	public void setAdditionalDelegates(Double additionalDelegates) {
		this.additionalDelegates = additionalDelegates;
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
	 * @return the chkvat
	 */
	public Boolean getChkvat() {
		return chkvat;
	}

	/**
	 * @param chkvat
	 *            the chkvat to set
	 */
	public void setChkvat(Boolean chkvat) {
		this.chkvat = chkvat;
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
	 * @return the firstDelegFee
	 */
	public Double getFirstDelegFee() {
		return firstDelegFee;
	}

	/**
	 * @param firstDelegFee
	 *            the firstDelegFee to set
	 */
	public void setFirstDelegFee(Double firstDelegFee) {
		this.firstDelegFee = firstDelegFee;
	}

	/**
	 * @return the secondDelegFee
	 */
	public Double getSecondDelegFee() {
		return secondDelegFee;
	}

	/**
	 * @param secondDelegFee
	 *            the secondDelegFee to set
	 */
	public void setSecondDelegFee(Double secondDelegFee) {
		this.secondDelegFee = secondDelegFee;
	}

	/**
	 * @return the thirdDelegFee
	 */
	public Double getThirdDelegFee() {
		return thirdDelegFee;
	}

	/**
	 * @param thirdDelegFee
	 *            the thirdDelegFee to set
	 */
	public void setThirdDelegFee(Double thirdDelegFee) {
		this.thirdDelegFee = thirdDelegFee;
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
	 * @return the cancsurCharge
	 */
	public Double getCancsurCharge() {
		return cancsurCharge;
	}

	/**
	 * @param cancsurCharge
	 *            the cancsurCharge to set
	 */
	public void setCancsurCharge(Double cancsurCharge) {
		this.cancsurCharge = cancsurCharge;
	}

	/**
	 * @return the schAddr
	 */
	public String getSchAddr() {
		return schAddr;
	}

	/**
	 * @param schAddr
	 *            the schAddr to set
	 */
	public void setSchAddr(String schAddr) {
		this.schAddr = schAddr;
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
	 * @return the fourthDelegFee
	 */
	public Double getFourthDelegFee() {
		return fourthDelegFee;
	}

	/**
	 * @param fourthDelegFee
	 *            the fourthDelegFee to set
	 */
	public void setFourthDelegFee(Double fourthDelegFee) {
		this.fourthDelegFee = fourthDelegFee;
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
	 * @return the softSetup
	 */
	public Double getSoftSetup() {
		return softSetup;
	}

	/**
	 * @param softSetup
	 *            the softSetup to set
	 */
	public void setSoftSetup(Double softSetup) {
		this.softSetup = softSetup;
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
	public Boolean getInvSentToSchFinance() {
		return invSentToSchFinance;
	}

	/**
	 * @param invSentToSchFinance
	 *            the invSentToSchFinance to set
	 */
	public void setInvSentToSchFinance(Boolean invSentToSchFinance) {
		this.invSentToSchFinance = invSentToSchFinance;
	}

	/**
	 * @return the bACSRefNo
	 */
	public String getbACSRefNo() {
		return bACSRefNo;
	}

	/**
	 * @param bACSRefNo
	 *            the bACSRefNo to set
	 */
	public void setbACSRefNo(String bACSRefNo) {
		this.bACSRefNo = bACSRefNo;
	}

	/**
	 * @return the reconsWithBank
	 */
	public Boolean getReconsWithBank() {
		return reconsWithBank;
	}

	/**
	 * @param reconsWithBank
	 *            the reconsWithBank to set
	 */
	public void setReconsWithBank(Boolean reconsWithBank) {
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
	 * @return the lTBacs
	 */
	public Boolean getlTBacs() {
		return lTBacs;
	}

	/**
	 * @param lTBacs
	 *            the lTBacs to set
	 */
	public void setlTBacs(Boolean lTBacs) {
		this.lTBacs = lTBacs;
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
	 * @return the lTCheque
	 */
	public Boolean getlTCheque() {
		return lTCheque;
	}

	/**
	 * @param lTCheque
	 *            the lTCheque to set
	 */
	public void setlTCheque(Boolean lTCheque) {
		this.lTCheque = lTCheque;
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
	 * @return the dateOfTransfToBankDummy
	 */
	public String getDateOfTransfToBankDummy() {
		return dateOfTransfToBankDummy;
	}

	/**
	 * @param dateOfTransfToBankDummy
	 *            the dateOfTransfToBankDummy to set
	 */
	public void setDateOfTransfToBankDummy(String dateOfTransfToBankDummy) {
		this.dateOfTransfToBankDummy = dateOfTransfToBankDummy;
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
	 * @return the chequeDateDummy
	 */
	public String getChequeDateDummy() {
		return chequeDateDummy;
	}

	/**
	 * @param chequeDateDummy
	 *            the chequeDateDummy to set
	 */
	public void setChequeDateDummy(String chequeDateDummy) {
		this.chequeDateDummy = chequeDateDummy;
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
	 * @return the bankReceiveDateDummy
	 */
	public String getBankReceiveDateDummy() {
		return bankReceiveDateDummy;
	}

	/**
	 * @param bankReceiveDateDummy
	 *            the bankReceiveDateDummy to set
	 */
	public void setBankReceiveDateDummy(String bankReceiveDateDummy) {
		this.bankReceiveDateDummy = bankReceiveDateDummy;
	}

	/**
	 * @return the raisedInvInPayPal
	 */
	public Boolean getRaisedInvInPayPal() {
		return raisedInvInPayPal;
	}

	/**
	 * @param raisedInvInPayPal
	 *            the raisedInvInPayPal to set
	 */
	public void setRaisedInvInPayPal(Boolean raisedInvInPayPal) {
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
	 * @return the lT
	 */
	public Boolean getLT() {
		return LT;
	}

	/**
	 * @param lT
	 *            the lT to set
	 */
	public void setLT(Boolean lT) {
		LT = lT;
	}

}