package com.crm.pojo;

import static com.crm.utils.DateUtils.convertTimeStampToString;
import static com.crm.utils.DateUtils.convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS;
import static com.crm.utils.DateUtils.convertYYYY_MM_DDToDD_MM_YYYYDateFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crm.model.Booking;
import com.crm.model.Confirmation;
import com.crm.model.CourierTracking;
import com.crm.model.DelegateList;
import com.crm.model.InvNotesTab;
import com.crm.model.Invoice;
import com.crm.model.LogMaster;
import com.crm.model.SalesPerson;

public class RecordsDTO {
	


	private Booking booking = new Booking();

	private Invoice invoice = new Invoice();

	private Confirmation confirmation = new Confirmation();

	private CourierTracking courierTracking = new CourierTracking();

	private List<DelegateList> delegateList = new ArrayList<DelegateList>();

	private InvNotesTab invNotesTab = new InvNotesTab();

	private LogMaster logMaster = new LogMaster();

	private SalesPerson salesPerson = new SalesPerson();
	
	
    public RecordsDTO() {

	}

	public RecordsDTO(Object[] object) {

		booking.setbId((Integer) object[0]);
		booking.setCalledDate(object[1]!=null?convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[1]).toString()):null);
		booking.setCalledUser((String) object[2]);
		booking.setCallerNotes((String) object[3]);
		booking.setTitle((String) object[4]);
		booking.setfName((String) object[5]);
		booking.setScName((String) object[6]);
		booking.setBookingPhase((String) object[7]);
		booking.setTrainingName((String) object[8]);
		booking.setTrDateDummy(object[9] != null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[9].toString()) : null);
		booking.setSubmitDateTime(object[10].toString().split("[.]")[0]
				.toString());
		booking.setTrainingLocation((String) object[11]);
		booking.setStatus((String) object[12]);
		booking.setDelegNum((Integer) object[13]);
		booking.setTel((String) object[14]);
		booking.setEmail((String) object[15]);
		booking.setNotes((String) object[16]);
		booking.setSecondDeleg((String) object[17]);
		booking.setThirdDeleg((String) object[18]);
		booking.setFourthDeleg((String) object[19]);
		booking.setInvEmail((String) object[20]);
		booking.setSecondDelegEmail((String)object[21]);
		booking.setThirdDelegEmail((String) object[22]);
		booking.setFourthDelegEmail((String) object[23]);
		booking.setCallBackTime(object[24]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[24])): null);
		booking.setStartTime(object[25] != null ? convertTimeStampToString(object[25]): null);
		booking.setEndTime(object[26]!= null ?convertTimeStampToString(object[26]) : null);
		booking.setFlCost((Double) object[27]);
		booking.setTrainGoCost((Double) object[28]);
		booking.setTaxiCost((Double) object[29]);
		booking.setHotelCost((Double) object[30]);
		booking.setConsultant((String) object[31]);
		invoice.setbId((Integer) object[32]);
		invoice.setVatAmount((Double) object[33]);
		invoice.setChkvat((Boolean) object[34]);
		invoice.setLT((Boolean) object[35]);
		invoice.setAdditionalDelegates((Double) object[36]);
		invoice.setId((Integer) object[37]);
		invoice.setFirstDelegFee((Double) object[38]);
		invoice.setSecondDelegFee((Double) object[39]);
		invoice.setThirdDelegFee((Double) object[40]);
		invoice.setDiscount((Double) object[41]);
		invoice.setCancsurCharge((Double) object[42]);
		invoice.setSchAddr((String) object[43]);
		invoice.setWebsite((String) object[44]);
		invoice.setTotal((Double) object[45]);
		invoice.setFourthDelegFee((Double) object[46]);
		invoice.setVenue((String) object[47]);
		invoice.setDate(object[48]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[48])) : null);
		invoice.setPoNo((String) object[49]);
		invoice.setTrainCost((Double) object[50]);
		invoice.setTravelCost((Double) object[51]);
		invoice.setSoftCost((Double) object[52]);
		invoice.setInvSentDate((Date) object[53]);
		invoice.setPayReceiveDateDummy(object[54]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat( object[54].toString()) : null);
		invoice.setPayReceiveBy((String) object[55]);
		invoice.setAdditionalNotes((String) object[56]);
		invoice.setInvSentToSchFinance((Boolean) object[57]);
		invoice.setbACSRefNo((String) object[58]);
		invoice.setReconsWithBank((Boolean) object[59]);
		invoice.setPaidByBacs((String) object[60]);
		invoice.setlTBacs((Boolean) object[61]);
		invoice.setCheckNo((String) object[62]);
		invoice.setAccNo((String) object[63]);
		invoice.setSortCode((String) object[64]);
		invoice.setPaidByCheque((String) object[65]);
		invoice.setlTCheque((Boolean) object[66]);
		invoice.setChequeDateDummy(object[67]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[67].toString()) : null);
		invoice.setChecqueDepDateDummy(object[68]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[68].toString()): null);
		invoice.setBankReceiveDateDummy(object[69]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[69].toString()) : null);
		invoice.setRaisedInvInPayPal((Boolean) object[70]);
		invoice.setDateOfTransfToBankDummy(object[71]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[71].toString()) : null);
		invoice.setPaymentType((String) object[72]);
		booking.setHear((String) object[73]);
		booking.setHearOther((String) object[74]);
		booking.setTypeOfCall((String) object[75]);
		salesPerson.setsName((String) object[76]);
		booking.setDemoDate(object[77]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[77])) : null);
	}

	public RecordsDTO(Object[] object, boolean isStatus) {

		booking.setbId((Integer) object[0]);
		booking.setCalledDate(object[1] !=null ?convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[1])): null);
		booking.setCalledUser((String) object[2]);
		booking.setCallerNotes((String) object[3]);
		booking.setTitle((String) object[4]);
		booking.setfName((String) object[5]);
		booking.setScName((String) object[6]);
		booking.setBookingPhase((String) object[7]);
		booking.setTraining((Integer) object[8]);
		booking.setTrainingName((String) object[9]);
		booking.setTrDateDummy(object[10] !=null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[10].toString()) : null);
		booking.setSubmitDateTime(object[11] !=null ? convertTimeStampToString(object[11]) : null);
		booking.setTrLocation((Integer) object[12]);
		booking.setTrainingLocation((String) object[13]);
		booking.setStatus((String) object[14]);
		booking.setDelegNum((Integer) object[15]);
		booking.setTel((String) object[16]);
		booking.setEmail((String) object[17]);
		booking.setNotes((String) object[18]);
		booking.setSecondDeleg((String) object[19]);
		booking.setThirdDeleg((String) object[20]);
		booking.setFourthDeleg((String) object[21]);
		booking.setInvEmail((String) object[22]);
		booking.setSecondDelegEmail((String) object[23]);
		booking.setThirdDelegEmail((String) object[24]);
		booking.setFourthDelegEmail((String) object[25]);
		booking.setCallBackTime(object[26]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[26])): null);
		booking.setStartTime(object[27] != null ? convertTimeStampToString(object[27]): null);
		booking.setEndTime(object[28]!= null ?convertTimeStampToString(object[28]) : null);
		booking.setFlCost((Double) object[29]);
		booking.setTrainGoCost((Double) object[30]);
		booking.setTaxiCost((Double) object[31]);
		booking.setHotelCost((Double) object[32]);
		booking.setConsultant((String) object[33]);
		invoice.setbId((Integer) object[34]);
		invoice.setVatAmount((Double) object[35]);
		invoice.setChkvat((Boolean) object[36]);
		invoice.setLT((Boolean) object[37]);
		invoice.setAdditionalDelegates((Double) object[38]);
		invoice.setId((Integer) object[39]);
		invoice.setFirstDelegFee((Double) object[40]);
		invoice.setSecondDelegFee((Double) object[41]);
		invoice.setThirdDelegFee((Double) object[42]);
		invoice.setDiscount((Double) object[43]);
		invoice.setCancsurCharge((Double) object[44]);
		invoice.setSchAddr((String) object[45]);
		invoice.setWebsite((String) object[46]);
		invoice.setTotal((Double) object[47]);
		invoice.setFourthDelegFee((Double) object[48]);
		invoice.setVenue((String) object[49]);
		invoice.setDate(object[50]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[50])) : null);
		invoice.setPoNo((String) object[51]);
		invoice.setTrainCost((Double) object[52]);
		invoice.setTravelCost((Double) object[53]);
		invoice.setSoftCost((Double) object[54]);
		invoice.setInvSentDate((Date) object[55]);
		invoice.setPayReceiveDateDummy(object[56]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat( object[56].toString()) : null);
		invoice.setPayReceiveBy((String) object[57]);
		invoice.setAdditionalNotes((String) object[58]);
		invoice.setInvSentToSchFinance((Boolean) object[59]);
		invoice.setbACSRefNo((String) object[60]);
		invoice.setReconsWithBank((Boolean) object[61]);
		invoice.setPaidByBacs((String) object[62]);
		invoice.setlTBacs((Boolean) object[63]);
		invoice.setCheckNo((String) object[64]);
		invoice.setAccNo((String) object[65]);
		invoice.setSortCode((String) object[66]);
		invoice.setPaidByCheque((String) object[67]);
		invoice.setlTCheque((Boolean) object[68]);
		invoice.setChequeDateDummy(object[69]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[69].toString()) : null);
		invoice.setChecqueDepDateDummy(object[70]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[70].toString()): null);
		invoice.setBankReceiveDateDummy(object[71]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[71].toString()) : null);
		invoice.setRaisedInvInPayPal((Boolean) object[72]);
		invoice.setDateOfTransfToBankDummy(object[73]!= null ? convertYYYY_MM_DDToDD_MM_YYYYDateFormat(object[73].toString()) : null);
		invoice.setPaymentType((String) object[74]);
		booking.setHear((String) object[75]);
		booking.setHearOther((String) object[76]);
		booking.setTypeOfCall((String) object[77]);
		salesPerson.setsName((String) object[78]);
		booking.setDemoDate(object[79]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[79])) : null);
		booking.setsId((Integer) object[80]);
		booking.setBookingSession((String) object[81]);
		confirmation.setbId((Integer) object[82]);
		confirmation.setSchoolGenRol((Boolean) object[83]);
		confirmation.setSchConfirmDate((Boolean) object[84]);
		confirmation.setTrainerConfirmed((Boolean)object[85]);
		confirmation.setTrainerConfirmed2((Boolean)object[86]);
		confirmation.setAttendanceConfirmed((Boolean)object[87]);
		confirmation.setAttendanceConfirmedBy((String)object[88]);
		confirmation.setRolRepRecieved((Boolean)object[89]);
		confirmation.setRepPrinted((Boolean)object[90]);
		confirmation.setRepPosted((Boolean)object[91]);
		confirmation.setPresentPrep((Boolean)object[92]);
		confirmation.setPresentOnlineDrive((Boolean)object[93]);
		confirmation.setOneDayRemind((Boolean)object[94]);
		confirmation.setOneWeekRemind((Boolean)object[95]);
		confirmation.setSchoolAwareReq((Boolean)object[96]);
		confirmation.setUserPasKnowSchool((Boolean)object[97]);
		confirmation.setDelegLaptop((Boolean)object[98]);
		confirmation.setBringRolFFT((Boolean)object[99]);
		confirmation.setRolPecPrint((Boolean)object[100]);
		confirmation.setDelegRem1Day((Boolean)object[101]);
		confirmation.setDelegRem1Week((Boolean)object[102]);
		confirmation.setTrainRem1Week((Boolean)object[103]);
		confirmation.setLinkSent((Boolean)object[104]);
		confirmation.setInfoSent((Boolean)object[105]);
		confirmation.setInterestedAsset((String)object[106]);
		confirmation.setCallbackAsset(object[107]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[107])) : null);
		confirmation.setAssetNotes((String)object[108]);
		confirmation.setJourneyPlan((Boolean)object[109]);
		confirmation.setInHouseRequirementConfirmedOn(object[110]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[110])) : null);
		confirmation.setInHouseNotes((String)object[111]);
		confirmation.setTrainingLocationRequirementConfirmedOn(object[112]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[112])) : null);
		confirmation.setTrainingLocationDelegateConfirmedOn(object[113]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[113])) : null);
		confirmation.setSoftwareLinkSentOn(object[114]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[114])) : null);
		confirmation.setSoftwareInfoSentOn(object[115]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[115])) : null);
		booking.setHear((String)object[116]);
		booking.setHearOther((String)object[117]);
		booking.setReminder((String)object[118]);
		confirmation.setReceivedLocation((Boolean)object[119]);
		confirmation.setTrainingMaterialCreated((Boolean)object[120]);
		confirmation.setInformationOnSchools((Boolean)object[121]);
		confirmation.setHotelHasAProjector((Boolean)object[122]);
		confirmation.setHotelHasWifi((Boolean)object[123]);
		confirmation.setHotelHasExtensionLeads((Boolean)object[124]);
		confirmation.setHotelHasTea((Boolean)object[125]);
		confirmation.setHotelHasLunch((Boolean)object[126]);
		confirmation.setHotelHasAfterNoonTea((Boolean)object[127]);
		confirmation.setPrice((BigDecimal)object[128]);
		/*bigdecimal*/
		confirmation.setContactsReceived((Boolean)object[129]);
		confirmation.setContactsSigned(object[130]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[130])) : null);
		confirmation.setConfirmationLocationNotes((String)object[131]);
		confirmation.setConfirmationLocationTrainingMaterialCreated((Boolean)object[132]);
		confirmation.setTrainingMaterialSentOn(object[133]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[133])) : null);
		confirmation.setEmailHotelToInform((Boolean)object[134]);
		confirmation.setConfirmationTrainingMaterialReceivedOn(object[135]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[135])) : null);
		courierTracking.setSendDate(object[136]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[136])) : null);
		courierTracking.setId((Integer)object[137]);
		courierTracking.setTrackingId((String)object[138]);
		courierTracking.setCourierCompany((String)object[139]);
		courierTracking.setWebsiteLink((String)object[140]);
		courierTracking.setDeliveryConfirmedBy((String)object[141]);
		courierTracking.setReceivedDateAndTime(object[142]!= null ? convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(convertTimeStampToString(object[142])) : null);
		courierTracking.setComments((String)object[143]);
		booking.setBookingUser((String)object[144]);
		invNotesTab.setbId((Integer)object[145]);
		invNotesTab.setInNotes((String)object[146]);
		invNotesTab.setExpectations((String)object[147]);	
	}

	/**
	 * @return the booking
	 */
	public Booking getBooking() {
		return booking;
	}

	/**
	 * @param booking
	 *            the booking to set
	 */
	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice
	 *            the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the confirmation
	 */
	public Confirmation getConfirmation() {
		return confirmation;
	}

	/**
	 * @param confirmation
	 *            the confirmation to set
	 */
	public void setConfirmation(Confirmation confirmation) {
		this.confirmation = confirmation;
	}

	/**
	 * @return the courierTracking
	 */
	public CourierTracking getCourierTracking() {
		return courierTracking;
	}

	/**
	 * @param courierTracking
	 *            the courierTracking to set
	 */
	public void setCourierTracking(CourierTracking courierTracking) {
		this.courierTracking = courierTracking;
	}

	/**
	 * @return the delegateList
	 */
	public List<DelegateList> getDelegateList() {
		return delegateList;
	}

	/**
	 * @param delegateList
	 *            the delegateList to set
	 */
	public void setDelegateList(List<DelegateList> delegateList) {
		this.delegateList = delegateList;
	}

	/**
	 * @return the invNotesTab
	 */
	public InvNotesTab getInvNotesTab() {
		return invNotesTab;
	}

	/**
	 * @param invNotesTab
	 *            the invNotesTab to set
	 */
	public void setInvNotesTab(InvNotesTab invNotesTab) {
		this.invNotesTab = invNotesTab;
	}

	/**
	 * @return the logMaster
	 */
	public LogMaster getLogMaster() {
		return logMaster;
	}

	/**
	 * @param logMaster
	 *            the logMaster to set
	 */
	public void setLogMaster(LogMaster logMaster) {
		this.logMaster = logMaster;
	}

	/**
	 * @return the salesPerson
	 */
	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	/**
	 * @param salesPerson
	 *            the salesPerson to set
	 */
	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

}