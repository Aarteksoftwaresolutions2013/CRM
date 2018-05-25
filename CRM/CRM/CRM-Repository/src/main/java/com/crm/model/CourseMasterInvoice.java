package com.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "corsemasterinvoice")
public class CourseMasterInvoice {

	@Id
	@Column(name = "coursesmasterid")
	private Integer coursesMasterId;

	@Column(name = "id")
	private Integer id;

	@Column(name = "firstdelegfee")
	private Double firstDelegFee;

	@Column(name = "seconddelegfee")
	private Double secondDelegFee;

	@Column(name = "thirdDelegFee")
	private Double thirdDelegFee;

	@Column(name = "costoftraining")
	private Double costOfTraining;

	@Column(name = "invoiceaddress")
	private String invoiceAddress;

	@Column(name = "website")
	private String website;

	@Column(name = "innotes")
	private String innotes;

	@Column(name = "vatamount")
	private Double vatAmount;

	@Column(name = "total")
	private Double total;

	@Column(name = "fourthDelegFee")
	private Double fourthDelegFee;

	@Column(name = "venue")
	private String venue;

	@Column(name = "date")
	private String date;

	@Column(name = "pono")
	private String poNo;

	@Column(name = "invsentdate")
	private Date invSentDate;

	@Column(name = "payreceivedate")
	private Date payReceiveDate;

	@Column(name = "payreceiveby")
	private String payReceiveBy;

	@Column(name = "additionalnotes")
	private String additionalNotes;

	@Column(name = "invsenttoschfinance")
	private Integer invSentToSchFinance;

	@Column(name = "bascrefno")
	private String bascRefNo;

	@Column(name = "reconswithbank")
	private Integer reconsWithBank;

	@Column(name = "paidbybacs")
	private String paidByBacs;

	@Column(name = "ltbacs")
	private Integer ltBacs;

	@Column(name = "checkno")
	private String checkNo;

	@Column(name = "accno")
	private String accNo;

	@Column(name = "sortcode")
	private String sortCode;

	@Column(name = "paidbycheque")
	private String paidByCheque;

	@Column(name = "ltcheque")
	private Integer ltCheque;

	@Column(name = "chequedate")
	private Date chequeDate;

	@Column(name = "checquedepdate")
	private Date checqueDepDate;

	@Column(name = "bankreceivedate")
	private Date bankReceiveDate;

	@Column(name = "raisedinvinpaypal")
	private Integer raisedInvInPaypal;

	@Column(name = "dateoftransftobank")
	private Date dateOfTransfToBank;

	@Column(name = "flcost")
	private Double flCost;

	@Column(name = "traingocost")
	private Double trainGoCost;

	@Column(name = "taxicost")
	private Double taxiCost;

	@Column(name = "hotelcost")
	private Double hotelCost;

	@Column(name = "cancsurcharge")
	private Double cancSurCharge;

	@Column(name = "additionaldelegates")
	private Double additionalDelegates;

	/**
	 * @return the coursesMasterId
	 */
	public Integer getCoursesMasterId() {
		return coursesMasterId;
	}

	/**
	 * @param coursesMasterId
	 *            the coursesMasterId to set
	 */
	public void setCoursesMasterId(Integer coursesMasterId) {
		this.coursesMasterId = coursesMasterId;
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
	 * @return the costOfTraining
	 */
	public Double getCostOfTraining() {
		return costOfTraining;
	}

	/**
	 * @param costOfTraining
	 *            the costOfTraining to set
	 */
	public void setCostOfTraining(Double costOfTraining) {
		this.costOfTraining = costOfTraining;
	}

	/**
	 * @return the invoiceAddress
	 */
	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	/**
	 * @param invoiceAddress
	 *            the invoiceAddress to set
	 */
	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
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
	 * @return the innotes
	 */
	public String getInnotes() {
		return innotes;
	}

	/**
	 * @param innotes
	 *            the innotes to set
	 */
	public void setInnotes(String innotes) {
		this.innotes = innotes;
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
	 * @return the bascRefNo
	 */
	public String getBascRefNo() {
		return bascRefNo;
	}

	/**
	 * @param bascRefNo
	 *            the bascRefNo to set
	 */
	public void setBascRefNo(String bascRefNo) {
		this.bascRefNo = bascRefNo;
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
	 * @return the raisedInvInPaypal
	 */
	public Integer getRaisedInvInPaypal() {
		return raisedInvInPaypal;
	}

	/**
	 * @param raisedInvInPaypal
	 *            the raisedInvInPaypal to set
	 */
	public void setRaisedInvInPaypal(Integer raisedInvInPaypal) {
		this.raisedInvInPaypal = raisedInvInPaypal;
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

}
