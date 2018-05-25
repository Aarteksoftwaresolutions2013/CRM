package com.crm.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "confirmation")
public class Confirmation {

	@Id
	@Column(name = "BId")
	private Integer bId;

	@Column(name = "SchoolGenRol")
	private Boolean schoolGenRol;

	@Column(name = "SchConfirmDate")
	private Boolean schConfirmDate;

	@Column(name = "TrainerConfirmed")
	private Boolean trainerConfirmed;

	@Column(name = "RolRepRecieved")
	private Boolean rolRepRecieved;

	@Column(name = "RepPrinted")
	private Boolean repPrinted;

	@Column(name = "RepPosted")
	private Boolean repPosted;

	@Column(name = "PresentPrep")
	private Boolean presentPrep;

	@Column(name = "PresentOnlineDrive")
	private Boolean presentOnlineDrive;

	@Column(name = "OneDayRemind")
	private Boolean oneDayRemind;

	@Column(name = "OneWeekRemind")
	private Boolean oneWeekRemind;

	@Column(name = "SchoolAwareReq")
	private Boolean schoolAwareReq;

	@Column(name = "UserPasKnowSchool")
	private Boolean userPasKnowSchool;

	@Column(name = "DelegLaptop")
	private Boolean delegLaptop;

	@Column(name = "BringRolFFT")
	private Boolean bringRolFFT;

	@Column(name = "RolPecPrint")
	private Boolean rolPecPrint;

	@Column(name = "DelegRem1Day")
	private Boolean delegRem1Day;

	@Column(name = "DelegRem1Week")
	private Boolean delegRem1Week;

	@Column(name = "TrainRem1Week")
	private Boolean trainRem1Week;

	@Column(name = "LinkSent")
	private Boolean linkSent;

	@Column(name = "InfoSent")
	private Boolean infoSent;

	@Column(name = "InterestedAsset")
	private String interestedAsset;

	@Column(name = "CallbackAsset")
	private String callbackAsset;

	@Column(name = "AssetNotes")
	private String assetNotes;

	@Column(name = "JourneyPlan")
	private Boolean journeyPlan;

	@Column(name = "AttendanceConfirmed")
	private Boolean attendanceConfirmed;

	@Column(name = "inhouserequirementconfirmedon")
	private String inHouseRequirementConfirmedOn;

	@Column(name = "inhousenotes")
	private String inHouseNotes;

	@Column(name = "traininglocationrequirementconfirmedon")
	private String trainingLocationRequirementConfirmedOn;

	@Column(name = "traininglocationdelegateconfirmedon")
	private String trainingLocationDelegateConfirmedOn;

	@Column(name = "softwarelinksenton")
	private String softwareLinkSentOn;

	@Column(name = "softwareinfosenton")
	private String softwareInfoSentOn;

	@Column(name = "AttendanceConfirmedby")
	private String attendanceConfirmedBy;

	@Column(name = "receivedLocation")
	private Boolean receivedLocation;

	@Column(name = "trainingMaterialCreated")
	private Boolean trainingMaterialCreated;

	@Column(name = "informationOnSchools")
	private Boolean informationOnSchools;

	@Column(name = "hotelHasAProjector")
	private Boolean hotelHasAProjector;

	@Column(name = "hotelHasWifi")
	private Boolean hotelHasWifi;

	@Column(name = "hotelHasExtensionLeads")
	private Boolean hotelHasExtensionLeads;

	@Column(name = "hotelHasTea")
	private Boolean hotelHasTea;

	@Column(name = "hotelHasLunch")
	private Boolean hotelHasLunch;

	@Column(name = "hotelHasAfterNoonTea")
	private Boolean hotelHasAfterNoonTea;

	@Column(name = "contactsReceived")
	private Boolean contactsReceived;

	@Column(name = "contactsSigned")
	private String contactsSigned;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "confirmationLocationNotes")
	private String confirmationLocationNotes;

	@Column(name = "confirmationLocationTrainingMaterialCreated")
	private Boolean confirmationLocationTrainingMaterialCreated;

	@Column(name = "trainingMaterialSentOn")
	private String trainingMaterialSentOn;

	@Column(name = "emailHotelToInform")
	private Boolean emailHotelToInform;

	@Column(name = "confirmationTrainingMaterialReceivedOn")
	private String confirmationTrainingMaterialReceivedOn;

	@Column(name = "TrainerConfirmed2")
	private Boolean TrainerConfirmed2;

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
	 * @return the schoolGenRol
	 */
	public Boolean getSchoolGenRol() {
		return schoolGenRol;
	}

	/**
	 * @param schoolGenRol
	 *            the schoolGenRol to set
	 */
	public void setSchoolGenRol(Boolean schoolGenRol) {
		this.schoolGenRol = schoolGenRol;
	}

	/**
	 * @return the schConfirmDate
	 */
	public Boolean getSchConfirmDate() {
		return schConfirmDate;
	}

	/**
	 * @param schConfirmDate
	 *            the schConfirmDate to set
	 */
	public void setSchConfirmDate(Boolean schConfirmDate) {
		this.schConfirmDate = schConfirmDate;
	}

	/**
	 * @return the trainerConfirmed
	 */
	public Boolean getTrainerConfirmed() {
		return trainerConfirmed;
	}

	/**
	 * @param trainerConfirmed
	 *            the trainerConfirmed to set
	 */
	public void setTrainerConfirmed(Boolean trainerConfirmed) {
		this.trainerConfirmed = trainerConfirmed;
	}

	/**
	 * @return the rolRepRecieved
	 */
	public Boolean getRolRepRecieved() {
		return rolRepRecieved;
	}

	/**
	 * @param rolRepRecieved
	 *            the rolRepRecieved to set
	 */
	public void setRolRepRecieved(Boolean rolRepRecieved) {
		this.rolRepRecieved = rolRepRecieved;
	}

	/**
	 * @return the repPrinted
	 */
	public Boolean getRepPrinted() {
		return repPrinted;
	}

	/**
	 * @param repPrinted
	 *            the repPrinted to set
	 */
	public void setRepPrinted(Boolean repPrinted) {
		this.repPrinted = repPrinted;
	}

	/**
	 * @return the repPosted
	 */
	public Boolean getRepPosted() {
		return repPosted;
	}

	/**
	 * @param repPosted
	 *            the repPosted to set
	 */
	public void setRepPosted(Boolean repPosted) {
		this.repPosted = repPosted;
	}

	/**
	 * @return the presentPrep
	 */
	public Boolean getPresentPrep() {
		return presentPrep;
	}

	/**
	 * @param presentPrep
	 *            the presentPrep to set
	 */
	public void setPresentPrep(Boolean presentPrep) {
		this.presentPrep = presentPrep;
	}

	/**
	 * @return the presentOnlineDrive
	 */
	public Boolean getPresentOnlineDrive() {
		return presentOnlineDrive;
	}

	/**
	 * @param presentOnlineDrive
	 *            the presentOnlineDrive to set
	 */
	public void setPresentOnlineDrive(Boolean presentOnlineDrive) {
		this.presentOnlineDrive = presentOnlineDrive;
	}

	/**
	 * @return the oneDayRemind
	 */
	public Boolean getOneDayRemind() {
		return oneDayRemind;
	}

	/**
	 * @param oneDayRemind
	 *            the oneDayRemind to set
	 */
	public void setOneDayRemind(Boolean oneDayRemind) {
		this.oneDayRemind = oneDayRemind;
	}

	/**
	 * @return the oneWeekRemind
	 */
	public Boolean getOneWeekRemind() {
		return oneWeekRemind;
	}

	/**
	 * @param oneWeekRemind
	 *            the oneWeekRemind to set
	 */
	public void setOneWeekRemind(Boolean oneWeekRemind) {
		this.oneWeekRemind = oneWeekRemind;
	}

	/**
	 * @return the schoolAwareReq
	 */
	public Boolean getSchoolAwareReq() {
		return schoolAwareReq;
	}

	/**
	 * @param schoolAwareReq
	 *            the schoolAwareReq to set
	 */
	public void setSchoolAwareReq(Boolean schoolAwareReq) {
		this.schoolAwareReq = schoolAwareReq;
	}

	/**
	 * @return the userPasKnowSchool
	 */
	public Boolean getUserPasKnowSchool() {
		return userPasKnowSchool;
	}

	/**
	 * @param userPasKnowSchool
	 *            the userPasKnowSchool to set
	 */
	public void setUserPasKnowSchool(Boolean userPasKnowSchool) {
		this.userPasKnowSchool = userPasKnowSchool;
	}

	/**
	 * @return the delegLaptop
	 */
	public Boolean getDelegLaptop() {
		return delegLaptop;
	}

	/**
	 * @param delegLaptop
	 *            the delegLaptop to set
	 */
	public void setDelegLaptop(Boolean delegLaptop) {
		this.delegLaptop = delegLaptop;
	}

	/**
	 * @return the bringRolFFT
	 */
	public Boolean getBringRolFFT() {
		return bringRolFFT;
	}

	/**
	 * @param bringRolFFT
	 *            the bringRolFFT to set
	 */
	public void setBringRolFFT(Boolean bringRolFFT) {
		this.bringRolFFT = bringRolFFT;
	}

	/**
	 * @return the rolPecPrint
	 */
	public Boolean getRolPecPrint() {
		return rolPecPrint;
	}

	/**
	 * @param rolPecPrint
	 *            the rolPecPrint to set
	 */
	public void setRolPecPrint(Boolean rolPecPrint) {
		this.rolPecPrint = rolPecPrint;
	}

	/**
	 * @return the delegRem1Day
	 */
	public Boolean getDelegRem1Day() {
		return delegRem1Day;
	}

	/**
	 * @param delegRem1Day
	 *            the delegRem1Day to set
	 */
	public void setDelegRem1Day(Boolean delegRem1Day) {
		this.delegRem1Day = delegRem1Day;
	}

	/**
	 * @return the delegRem1Week
	 */
	public Boolean getDelegRem1Week() {
		return delegRem1Week;
	}

	/**
	 * @param delegRem1Week
	 *            the delegRem1Week to set
	 */
	public void setDelegRem1Week(Boolean delegRem1Week) {
		this.delegRem1Week = delegRem1Week;
	}

	/**
	 * @return the trainRem1Week
	 */
	public Boolean getTrainRem1Week() {
		return trainRem1Week;
	}

	/**
	 * @param trainRem1Week
	 *            the trainRem1Week to set
	 */
	public void setTrainRem1Week(Boolean trainRem1Week) {
		this.trainRem1Week = trainRem1Week;
	}

	/**
	 * @return the linkSent
	 */
	public Boolean getLinkSent() {
		return linkSent;
	}

	/**
	 * @param linkSent
	 *            the linkSent to set
	 */
	public void setLinkSent(Boolean linkSent) {
		this.linkSent = linkSent;
	}

	/**
	 * @return the infoSent
	 */
	public Boolean getInfoSent() {
		return infoSent;
	}

	/**
	 * @param infoSent
	 *            the infoSent to set
	 */
	public void setInfoSent(Boolean infoSent) {
		this.infoSent = infoSent;
	}

	/**
	 * @return the interestedAsset
	 */
	public String getInterestedAsset() {
		return interestedAsset;
	}

	/**
	 * @param interestedAsset
	 *            the interestedAsset to set
	 */
	public void setInterestedAsset(String interestedAsset) {
		this.interestedAsset = interestedAsset;
	}

	/**
	 * @return the callbackAsset
	 */
	public String getCallbackAsset() {
		return callbackAsset;
	}

	/**
	 * @param callbackAsset
	 *            the callbackAsset to set
	 */
	public void setCallbackAsset(String callbackAsset) {
		this.callbackAsset = callbackAsset;
	}

	/**
	 * @return the assetNotes
	 */
	public String getAssetNotes() {
		return assetNotes;
	}

	/**
	 * @param assetNotes
	 *            the assetNotes to set
	 */
	public void setAssetNotes(String assetNotes) {
		this.assetNotes = assetNotes;
	}

	/**
	 * @return the journeyPlan
	 */
	public Boolean getJourneyPlan() {
		return journeyPlan;
	}

	/**
	 * @param journeyPlan
	 *            the journeyPlan to set
	 */
	public void setJourneyPlan(Boolean journeyPlan) {
		this.journeyPlan = journeyPlan;
	}

	/**
	 * @return the attendanceConfirmed
	 */
	public Boolean getAttendanceConfirmed() {
		return attendanceConfirmed;
	}

	/**
	 * @param attendanceConfirmed
	 *            the attendanceConfirmed to set
	 */
	public void setAttendanceConfirmed(Boolean attendanceConfirmed) {
		this.attendanceConfirmed = attendanceConfirmed;
	}

	/**
	 * @return the inHouseRequirementConfirmedOn
	 */
	public String getInHouseRequirementConfirmedOn() {
		return inHouseRequirementConfirmedOn;
	}

	/**
	 * @param inHouseRequirementConfirmedOn
	 *            the inHouseRequirementConfirmedOn to set
	 */
	public void setInHouseRequirementConfirmedOn(
			String inHouseRequirementConfirmedOn) {
		this.inHouseRequirementConfirmedOn = inHouseRequirementConfirmedOn;
	}

	/**
	 * @return the inHouseNotes
	 */
	public String getInHouseNotes() {
		return inHouseNotes;
	}

	/**
	 * @param inHouseNotes
	 *            the inHouseNotes to set
	 */
	public void setInHouseNotes(String inHouseNotes) {
		this.inHouseNotes = inHouseNotes;
	}

	/**
	 * @return the trainingLocationRequirementConfirmedOn
	 */
	public String getTrainingLocationRequirementConfirmedOn() {
		return trainingLocationRequirementConfirmedOn;
	}

	/**
	 * @param trainingLocationRequirementConfirmedOn
	 *            the trainingLocationRequirementConfirmedOn to set
	 */
	public void setTrainingLocationRequirementConfirmedOn(
			String trainingLocationRequirementConfirmedOn) {
		this.trainingLocationRequirementConfirmedOn = trainingLocationRequirementConfirmedOn;
	}

	/**
	 * @return the trainingLocationDelegateConfirmedOn
	 */
	public String getTrainingLocationDelegateConfirmedOn() {
		return trainingLocationDelegateConfirmedOn;
	}

	/**
	 * @param trainingLocationDelegateConfirmedOn
	 *            the trainingLocationDelegateConfirmedOn to set
	 */
	public void setTrainingLocationDelegateConfirmedOn(
			String trainingLocationDelegateConfirmedOn) {
		this.trainingLocationDelegateConfirmedOn = trainingLocationDelegateConfirmedOn;
	}

	/**
	 * @return the softwareLinkSentOn
	 */
	public String getSoftwareLinkSentOn() {
		return softwareLinkSentOn;
	}

	/**
	 * @param softwareLinkSentOn
	 *            the softwareLinkSentOn to set
	 */
	public void setSoftwareLinkSentOn(String softwareLinkSentOn) {
		this.softwareLinkSentOn = softwareLinkSentOn;
	}

	/**
	 * @return the softwareInfoSentOn
	 */
	public String getSoftwareInfoSentOn() {
		return softwareInfoSentOn;
	}

	/**
	 * @param softwareInfoSentOn
	 *            the softwareInfoSentOn to set
	 */
	public void setSoftwareInfoSentOn(String softwareInfoSentOn) {
		this.softwareInfoSentOn = softwareInfoSentOn;
	}

	/**
	 * @return the attendanceConfirmedBy
	 */
	public String getAttendanceConfirmedBy() {
		return attendanceConfirmedBy;
	}

	/**
	 * @param attendanceConfirmedBy
	 *            the attendanceConfirmedBy to set
	 */
	public void setAttendanceConfirmedBy(String attendanceConfirmedBy) {
		this.attendanceConfirmedBy = attendanceConfirmedBy;
	}

	/**
	 * @return the receivedLocation
	 */
	public Boolean getReceivedLocation() {
		return receivedLocation;
	}

	/**
	 * @param receivedLocation
	 *            the receivedLocation to set
	 */
	public void setReceivedLocation(Boolean receivedLocation) {
		this.receivedLocation = receivedLocation;
	}

	/**
	 * @return the trainingMaterialCreated
	 */
	public Boolean getTrainingMaterialCreated() {
		return trainingMaterialCreated;
	}

	/**
	 * @param trainingMaterialCreated
	 *            the trainingMaterialCreated to set
	 */
	public void setTrainingMaterialCreated(Boolean trainingMaterialCreated) {
		this.trainingMaterialCreated = trainingMaterialCreated;
	}

	/**
	 * @return the informationOnSchools
	 */
	public Boolean getInformationOnSchools() {
		return informationOnSchools;
	}

	/**
	 * @param informationOnSchools
	 *            the informationOnSchools to set
	 */
	public void setInformationOnSchools(Boolean informationOnSchools) {
		this.informationOnSchools = informationOnSchools;
	}

	/**
	 * @return the hotelHasAProjector
	 */
	public Boolean getHotelHasAProjector() {
		return hotelHasAProjector;
	}

	/**
	 * @param hotelHasAProjector
	 *            the hotelHasAProjector to set
	 */
	public void setHotelHasAProjector(Boolean hotelHasAProjector) {
		this.hotelHasAProjector = hotelHasAProjector;
	}

	/**
	 * @return the hotelHasWifi
	 */
	public Boolean getHotelHasWifi() {
		return hotelHasWifi;
	}

	/**
	 * @param hotelHasWifi
	 *            the hotelHasWifi to set
	 */
	public void setHotelHasWifi(Boolean hotelHasWifi) {
		this.hotelHasWifi = hotelHasWifi;
	}

	/**
	 * @return the hotelHasExtensionLeads
	 */
	public Boolean getHotelHasExtensionLeads() {
		return hotelHasExtensionLeads;
	}

	/**
	 * @param hotelHasExtensionLeads
	 *            the hotelHasExtensionLeads to set
	 */
	public void setHotelHasExtensionLeads(Boolean hotelHasExtensionLeads) {
		this.hotelHasExtensionLeads = hotelHasExtensionLeads;
	}

	/**
	 * @return the hotelHasTea
	 */
	public Boolean getHotelHasTea() {
		return hotelHasTea;
	}

	/**
	 * @param hotelHasTea
	 *            the hotelHasTea to set
	 */
	public void setHotelHasTea(Boolean hotelHasTea) {
		this.hotelHasTea = hotelHasTea;
	}

	/**
	 * @return the hotelHasLunch
	 */
	public Boolean getHotelHasLunch() {
		return hotelHasLunch;
	}

	/**
	 * @param hotelHasLunch
	 *            the hotelHasLunch to set
	 */
	public void setHotelHasLunch(Boolean hotelHasLunch) {
		this.hotelHasLunch = hotelHasLunch;
	}

	/**
	 * @return the hotelHasAfterNoonTea
	 */
	public Boolean getHotelHasAfterNoonTea() {
		return hotelHasAfterNoonTea;
	}

	/**
	 * @param hotelHasAfterNoonTea
	 *            the hotelHasAfterNoonTea to set
	 */
	public void setHotelHasAfterNoonTea(Boolean hotelHasAfterNoonTea) {
		this.hotelHasAfterNoonTea = hotelHasAfterNoonTea;
	}

	/**
	 * @return the contactsReceived
	 */
	public Boolean getContactsReceived() {
		return contactsReceived;
	}

	/**
	 * @param contactsReceived
	 *            the contactsReceived to set
	 */
	public void setContactsReceived(Boolean contactsReceived) {
		this.contactsReceived = contactsReceived;
	}

	/**
	 * @return the contactsSigned
	 */
	public String getContactsSigned() {
		return contactsSigned;
	}

	/**
	 * @param contactsSigned
	 *            the contactsSigned to set
	 */
	public void setContactsSigned(String contactsSigned) {
		this.contactsSigned = contactsSigned;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the confirmationLocationNotes
	 */
	public String getConfirmationLocationNotes() {
		return confirmationLocationNotes;
	}

	/**
	 * @param confirmationLocationNotes
	 *            the confirmationLocationNotes to set
	 */
	public void setConfirmationLocationNotes(String confirmationLocationNotes) {
		this.confirmationLocationNotes = confirmationLocationNotes;
	}

	/**
	 * @return the confirmationLocationTrainingMaterialCreated
	 */
	public Boolean getConfirmationLocationTrainingMaterialCreated() {
		return confirmationLocationTrainingMaterialCreated;
	}

	/**
	 * @param confirmationLocationTrainingMaterialCreated
	 *            the confirmationLocationTrainingMaterialCreated to set
	 */
	public void setConfirmationLocationTrainingMaterialCreated(
			Boolean confirmationLocationTrainingMaterialCreated) {
		this.confirmationLocationTrainingMaterialCreated = confirmationLocationTrainingMaterialCreated;
	}

	/**
	 * @return the trainingMaterialSentOn
	 */
	public String getTrainingMaterialSentOn() {
		return trainingMaterialSentOn;
	}

	/**
	 * @param trainingMaterialSentOn
	 *            the trainingMaterialSentOn to set
	 */
	public void setTrainingMaterialSentOn(String trainingMaterialSentOn) {
		this.trainingMaterialSentOn = trainingMaterialSentOn;
	}

	/**
	 * @return the emailHotelToInform
	 */
	public Boolean getEmailHotelToInform() {
		return emailHotelToInform;
	}

	/**
	 * @param emailHotelToInform
	 *            the emailHotelToInform to set
	 */
	public void setEmailHotelToInform(Boolean emailHotelToInform) {
		this.emailHotelToInform = emailHotelToInform;
	}

	/**
	 * @return the confirmationTrainingMaterialReceivedOn
	 */
	public String getConfirmationTrainingMaterialReceivedOn() {
		return confirmationTrainingMaterialReceivedOn;
	}

	/**
	 * @param confirmationTrainingMaterialReceivedOn
	 *            the confirmationTrainingMaterialReceivedOn to set
	 */
	public void setConfirmationTrainingMaterialReceivedOn(
			String confirmationTrainingMaterialReceivedOn) {
		this.confirmationTrainingMaterialReceivedOn = confirmationTrainingMaterialReceivedOn;
	}

	/**
	 * @return the trainerConfirmed2
	 */
	public Boolean getTrainerConfirmed2() {
		return TrainerConfirmed2;
	}

	/**
	 * @param trainerConfirmed2
	 *            the trainerConfirmed2 to set
	 */
	public void setTrainerConfirmed2(Boolean trainerConfirmed2) {
		TrainerConfirmed2 = trainerConfirmed2;
	}

}