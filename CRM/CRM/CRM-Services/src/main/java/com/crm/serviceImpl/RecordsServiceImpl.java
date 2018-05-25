package com.crm.serviceImpl;

import static com.crm.utils.DateUtils.convertDD_MM_YYYYToYYYY_MM_DDDateFormat;
import static com.crm.utils.DateUtils.convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.joda.time.DateTime;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

//import com.couchbase.client.Diagnostics;
import com.crm.model.Booking;
import com.crm.model.Confirmation;
import com.crm.model.CourierTracking;
import com.crm.model.DelegateList;
import com.crm.model.InvNotesTab;
import com.crm.model.Invoice;
import com.crm.model.LogMaster;
import com.crm.pojo.InvoiceDTO;
import com.crm.pojo.RecordsDTO;
import com.crm.repository.IBookingRepository;
import com.crm.repository.IConfirmationRepository;
import com.crm.repository.ICourierTrackingRepository;
import com.crm.repository.IDelegateListRepository;
import com.crm.repository.IInvNotesTabRepository;
import com.crm.repository.IInvoiceRepository;
import com.crm.repository.ILogMasterRepository;
import com.crm.service.IRecordsService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class RecordsServiceImpl implements IRecordsService {

	// private static final Log logger =
	// LogFactory.getLog(RecordsServiceImpl.class);

	final Logger logger = Logger.getLogger(RecordsServiceImpl.class);

	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private IConfirmationRepository confirmationRepository;

	@Autowired
	private ICourierTrackingRepository courierTrackingRepository;

	@Autowired
	private IDelegateListRepository delegateListRepository;

	@Autowired
	private IInvNotesTabRepository invNotesTabRepository;

	@Autowired
	private IInvoiceRepository invoiceRepository;

	@Autowired
	private ILogMasterRepository logMasterRepository;

	// ADDITIONAL FEES IN INVOICE
	public static int invoiceAdditionalDelegates = 0;
	public static int invoiceAdditionalFeesPerDelegate = 0;
	public static int invoiceTotalAdditionalFees = 0;
	public static String ordrDate = "";
	private static int bidInvoice;
	private static int bid;
	private int delegatesNumInvoice = 0;
	private int amountInvoice = 0;

	// 2015/12/17
	// String strFutureDate = "2015/12/17";

	String strFutureDate = "2015/12/17";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate futureDate = LocalDate.parse(strFutureDate, formatter);

	/**
	 * Method for save records page
	 * 
	 * @param recordsDTO
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void saveRecordsPage(RecordsDTO recordsDTO) {

		Booking booking = recordsDTO.getBooking();
		booking.setCallBackTime((booking.getCallBackTime() != null && booking.getCallBackTime() != "")
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(booking.getCallBackTime()) : null);
		booking.setTrDate(booking.getTrDateDummy() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(booking.getTrDateDummy()) : null);
		booking.setDemoDate((booking.getDemoDate() != null && booking.getDemoDate() != "")
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(booking.getDemoDate()) : null);
		booking.setCalledDate((booking.getCalledDate() != null && booking.getCalledDate() != "")
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(booking.getCalledDate()) : null);
		

		if (StringUtils.isEmpty(booking.getbId())) {
			List<Integer> bookMaxId = bookingRepository.findMaxId();
			booking.setbId(bookMaxId.get(0) + 1);
			bookingRepository.saveBooking(booking);
		} else {
			bookingRepository.updateBooking(booking);
		}

		Confirmation confirmation = recordsDTO.getConfirmation();
		confirmation.setInHouseRequirementConfirmedOn(confirmation.getInHouseRequirementConfirmedOn() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(confirmation.getInHouseRequirementConfirmedOn())
				: null);
		confirmation.setTrainingLocationRequirementConfirmedOn(
				confirmation.getTrainingLocationRequirementConfirmedOn() != null
						? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(
								confirmation.getTrainingLocationRequirementConfirmedOn())
						: null);
		confirmation
				.setTrainingLocationDelegateConfirmedOn(confirmation.getTrainingLocationDelegateConfirmedOn() != null
						? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(
								confirmation.getTrainingLocationDelegateConfirmedOn())
						: null);
		confirmation.setContactsSigned(confirmation.getContactsSigned() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(confirmation.getContactsSigned()) : null);
		confirmation.setTrainingMaterialSentOn(confirmation.getTrainingMaterialSentOn() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(confirmation.getTrainingMaterialSentOn()) : null);
		confirmation.setConfirmationTrainingMaterialReceivedOn(
				confirmation.getConfirmationTrainingMaterialReceivedOn() != null
						? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(
								confirmation.getConfirmationTrainingMaterialReceivedOn())
						: null);
		confirmation.setCallbackAsset(confirmation.getCallbackAsset() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(confirmation.getCallbackAsset()) : null);
		confirmation.setSoftwareInfoSentOn(confirmation.getSoftwareInfoSentOn() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(confirmation.getSoftwareInfoSentOn()) : null);
		confirmation.setSoftwareLinkSentOn(confirmation.getSoftwareLinkSentOn() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(confirmation.getSoftwareLinkSentOn()) : null);
		if (StringUtils.isEmpty(confirmation.getbId())) {
			List<Integer> conMaxId = confirmationRepository.findMaxId();
			confirmation.setbId(conMaxId.get(0) + 1);

			confirmationRepository.saveConfirmation(confirmation);
		} else {
			confirmationRepository.updateConfirmation(confirmation);
		}

		CourierTracking courierTracking = recordsDTO.getCourierTracking();
		courierTracking.setSendDate(courierTracking.getSendDate() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(courierTracking.getSendDate()) : null);
		courierTracking.setReceivedDateAndTime(courierTracking.getReceivedDateAndTime() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(courierTracking.getReceivedDateAndTime()) : null);
		if (StringUtils.isEmpty(courierTracking.getId())) {
			List<Integer> cotMaxId = courierTrackingRepository.findMaxId();
			courierTracking.setId(cotMaxId.get(0) + 1);

			courierTrackingRepository.saveCourierTracking(courierTracking);
		} else {
			courierTrackingRepository.updateCourierTracking(courierTracking);
		}

		List<DelegateList> delegateList = recordsDTO.getDelegateList();
		for (DelegateList deleList : delegateList) {
			if (StringUtils.isEmpty(deleList.getId())) {
				List<Integer> delMaxId = delegateListRepository.findMaxId();
				deleList.setId(delMaxId.get(0) + 1);

				delegateListRepository.saveDelegateList(deleList);
			} else {
				delegateListRepository.updateDelegateList(deleList);
			}
		}

		InvNotesTab invNotesTab = recordsDTO.getInvNotesTab();
		if (StringUtils.isEmpty(invNotesTab.getbId())) {
			List<Integer> invMaxId = invNotesTabRepository.findMaxId();
			invNotesTab.setbId(invMaxId.get(0) + 1);

			invNotesTabRepository.saveInvNotesTab(invNotesTab);
		} else {
			invNotesTabRepository.updateInvNotesTab(invNotesTab);
		}

		Invoice invoice = recordsDTO.getInvoice();
		invoice.setPayReceiveDate(invoice.getPayReceiveDateDummy() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(invoice.getPayReceiveDateDummy()) : null);
		invoice.setChecqueDepDate(invoice.getChecqueDepDateDummy() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(invoice.getChecqueDepDateDummy()) : null);
		invoice.setBankReceiveDate(invoice.getBankReceiveDateDummy() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(invoice.getBankReceiveDateDummy()) : null);
		invoice.setChequeDate(invoice.getChequeDateDummy() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(invoice.getChequeDateDummy()) : null);
		invoice.setDateOfTransfToBank(invoice.getDateOfTransfToBankDummy() != null
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(invoice.getDateOfTransfToBankDummy()) : null);
		invoice.setDate((invoice.getDate() != null && invoice.getDate() != "")
				? convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(invoice.getDate()) : null);
		invoice.setDiscount(0.0);
		if (StringUtils.isEmpty(invoice.getId())) {
			if (!StringUtils.isEmpty(invoice.getPaymentType())) {
				invoice.setId(bookingRepository.getInvoiceNumber(invoice.getPaymentType()));
				invoice.setbId(booking.getbId());
				invoiceRepository.saveInvoice(invoice);
			} else {
				if (!StringUtils.isEmpty(invoice.getId())) {
					invoiceRepository.updateInvoice(invoice);
				} else {
					if (!StringUtils.isEmpty(invoice.getbId())) {
						invoice.setId(0);
						invoiceRepository.updateInvoice(invoice);
					}

				}
			}
		} else {
			invoiceRepository.updateInvoice(invoice);
		}

		LogMaster logMaster = recordsDTO.getLogMaster();
		if (StringUtils.isEmpty(logMaster.getId()) && logMaster.getComment() != "") {
			List<Integer> logMaxId = logMasterRepository.findMaxId();
			logMaster.setId(logMaxId.get(0) + 1);

			logMasterRepository.saveLogMaster(logMaster);
		} else {
			if (!StringUtils.isEmpty(logMaster.getId())) {
				logMasterRepository.updateLogMaster(logMaster);
			}
		}
	}

	@SuppressWarnings("unused")
	private String normaliseDiscount(RecordsDTO recordsDTO) {
		String normalDisc = "";
		Invoice invoice = recordsDTO.getInvoice();
		normalDisc = Double.toString(invoice.getDiscount());
		if (normalDisc.length() > 0) {
			normalDisc = normalDisc.substring(1, normalDisc.length() - 1);
		}
		if (normalDisc == null) {
			normalDisc = "0";
		}
		return normalDisc;
	}

	@SuppressWarnings("unused")
	private boolean hasAdditionalDelegates(RecordsDTO recordsDTO) {

		Invoice invoice = recordsDTO.getInvoice();

		try {
			if (invoice.getAdditionalDelegates() > 0) {
				if (invoice.getAdditionalDelegates() > 0) {
					return true;
				}
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private boolean isFutureDate() {

		LocalDate submitDate = LocalDate.now();

		// LocalDateTime.parse(booking.getSubmitDateTime());

		if (submitDate.isAfter(futureDate) || submitDate.equals(futureDate)) {
			return true;
		}
		return false;
	}

	private void templateChange(RecordsDTO recordsDTO) throws IOException, InvalidFormatException, XmlException {

		String disc;
		String paymentType = "";
		String discount = "";
		// Booking booking = new Booking();
		Booking booking = recordsDTO.getBooking();
		Invoice invoice = recordsDTO.getInvoice();
		disc = Double.toString((invoice.getDiscount() != null) ? invoice.getDiscount() : 0.0);
		if (disc.length() > 0) {
			discount = disc.toString();
		}

		String first = Double.toString((invoice.getFirstDelegFee() != null) ? invoice.getFirstDelegFee() : 0.0);
		String second = Double.toString((invoice.getSecondDelegFee() != null) ? invoice.getSecondDelegFee() : 0.0);
		String third = Double.toString((invoice.getThirdDelegFee() != null) ? invoice.getThirdDelegFee() : 0.0);
		String fourth = Double.toString((invoice.getFourthDelegFee() != null) ? invoice.getFourthDelegFee() : 0.0);
		String soft = Double.toString((invoice.getSoftCost() != null) ? invoice.getSoftCost() : 0.0);
		String softSetup = Double.toString((invoice.getSoftSetup() != null) ? invoice.getSoftSetup() : 0.0);
		String travel = Double.toString((invoice.getTravelCost() != null) ? invoice.getTravelCost() : 0.0);
		String training = Double.toString((invoice.getTrainCost() != null) ? invoice.getTrainCost() : 0.0);
		String additionaldelegates = ((invoice.getAdditionalNotes() != null) ? invoice.getAdditionalNotes() : null);
		String total = Double.toString((invoice.getTotal() != null) ? invoice.getTotal() : 0.0);

		String firstVat = "";
		String secondVat = "";
		String thirdVat = "";
		String fourthVat = "";

		String firstGross = "";
		String secondGross = "";
		String thirdGross = "";
		String fourthGross = "";
		String finalVat = Double.toString((invoice.getVatAmount() != null) ? invoice.getVatAmount() : 0.0);
		String finalVatPercentage = "20%";

		try {

			Double firstFee = 0.0;
			Double secondFee = 0.0;
			Double thirdFee = 0.0;
			Double fourthFee = 0.0;

			Double doufirstVat = 0.0;
			Double dousecondVat = 0.0;
			Double douthirdVat = 0.0;
			Double doufourthVat = 0.0;
			Double doufirstGross = 0.0;
			Double dousecondGross = 0.0;
			Double douthirdGross = 0.0;
			Double doufourthGross = 0.0;
			Double doufinalVat = 0.0;

			String startDate = "2013/10/02";

			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate strtDate = LocalDate.parse(startDate, formatters);

			LocalDate submitDateTime = LocalDate.parse(booking.getSubmitDateTime());

			// StartDt < dtpsubmitdate.Value

			if (strtDate.isBefore(submitDateTime)) {
				doufirstVat = firstFee * 20 / 100;
				dousecondVat = secondFee * 20 / 100;
				douthirdVat = thirdFee * 20 / 100;
				doufourthVat = fourthFee * 20 / 100;

				doufirstGross = firstFee + doufirstVat;
				dousecondGross = secondFee + dousecondVat;
				douthirdGross = thirdFee + douthirdVat;
				doufourthGross = fourthFee + doufourthVat;
			} else {
				doufirstVat = 0.0;
				int intDoufirstVat = doufirstVat.intValue();
				dousecondVat = 0.0;
				int intDousecondVat = doufirstVat.intValue();
				douthirdVat = 0.0;
				int intthirdVat = doufirstVat.intValue();
				doufourthVat = 0.0;
				int intfourthVat = doufirstVat.intValue();

				doufirstGross = firstFee;
				dousecondGross = secondFee;
				douthirdGross = thirdFee;
				doufourthGross = fourthFee;
			}

			finalVat = Double.toString((invoice.getVatAmount() != null) ? invoice.getVatAmount() : 0.0);
			firstGross = doufirstGross.toString();
			secondGross = dousecondGross.toString();
			thirdGross = douthirdGross.toString();
			fourthGross = doufourthGross.toString();

			firstVat = doufirstVat.toString();
			secondVat = dousecondVat.toString();
			thirdVat = douthirdVat.toString();
			fourthVat = doufourthVat.toString();

		} catch (Exception e) {

		}

		if (!firstVat.contains(".")) {
			firstVat += ".00";
		}

		try {
			if (firstVat.substring(firstVat.indexOf(".") + 1, firstVat.length() - (firstVat.indexOf(".") + 1))
					.length() == 1) {
				firstVat += "0";
			}
		} catch (Exception ex) {
		}

		if (!secondVat.contains(".")) {
			secondVat += ".00";
		}

		try {
			if (secondVat.substring(secondVat.indexOf(".") + 1, secondVat.length() - (secondVat.indexOf(".") + 1))
					.length() == 1) {
				secondVat += "0";
			}
		} catch (Exception ex) {
		}

		if (!thirdVat.contains(".")) {
			thirdVat += ".00";
		}

		try {
			if (thirdVat.substring(thirdVat.indexOf(".") + 1, thirdVat.length() - (thirdVat.indexOf(".") + 1))
					.length() == 1) {
				thirdVat += "0";
			}
		} catch (Exception ex) {
		}

		if (!fourthVat.contains(".")) {
			fourthVat += ".00";
		}

		try {
			if (fourthVat.substring(fourthVat.indexOf(".") + 1, fourthVat.length() - (fourthVat.indexOf(".") + 1))
					.length() == 1) {
				fourthVat += "0";
			}
		} catch (Exception ex) {
		}

		if (!firstGross.contains(".")) {
			firstGross += ".00";
		}

		try {
			if (firstGross.substring(firstGross.indexOf(".") + 1, firstGross.length() - (firstGross.indexOf(".") + 1))
					.length() == 1) {
				firstGross += "0";
			}
		} catch (Exception ex) {
		}

		if (!secondGross.contains(".")) {
			secondGross += ".00";
		}

		try {
			if (secondGross
					.substring(secondGross.indexOf(".") + 1, secondGross.length() - (secondGross.indexOf(".") + 1))
					.length() == 1) {
				secondGross += "0";
			}
		} catch (Exception ex) {
		}

		if (!thirdGross.contains(".")) {
			thirdGross += ".00";
		}

		try {
			if (thirdGross.substring(thirdGross.indexOf(".") + 1, thirdGross.length() - (thirdGross.indexOf(".") + 1))
					.length() == 1) {
				thirdGross += "0";
			}
		} catch (Exception ex) {
		}

		if (!fourthGross.contains(".")) {
			fourthGross += ".00";
		}

		try {
			if (fourthGross
					.substring(fourthGross.indexOf(".") + 1, fourthGross.length() - (fourthGross.indexOf(".") + 1))
					.length() == 1) {
				fourthGross += "0";
			}
		} catch (Exception ex) {
		}

		if (!finalVat.contains(".")) {
			finalVat += ".00";
		}

		try {
			if (finalVat.substring(finalVat.indexOf(".") + 1, finalVat.length() - (finalVat.indexOf(".") + 1))
					.length() == 1) {
				finalVat += "0";
			}
		} catch (Exception ex) {
		}

		if (!total.contains(".")) {
			total += ".00";
		}

		try {
			if (total.substring(total.indexOf(".") + 1, total.length() - (total.indexOf(".") + 1)).length() == 1) {
				total += "0";
			}
		} catch (Exception ex) {
		}

		if (!training.contains(".")) {
			training += ".00";
		}

		try {
			if (training.substring(training.indexOf(".") + 1, training.length() - (training.indexOf(".") + 1))
					.length() == 1) {
				training += "0";
			}
		} catch (Exception ex) {
		}

		if (!travel.contains(".")) {
			travel += ".00";
		}

		try {
			if (travel.substring(travel.indexOf(".") + 1, travel.length() - (travel.indexOf(".") + 1)).length() == 1) {
				travel += "0";
			}
		} catch (Exception ex) {
		}

		if (!soft.contains(".")) {
			soft += ".00";
		}

		if (!softSetup.contains(".")) {
			softSetup += ".00";
		}

		try {
			if (soft.substring(soft.indexOf(".") + 1, soft.length() - (soft.indexOf(".") + 1)).length() == 1) {
				soft += "0";
			}
		} catch (Exception ex) {
		}

		try {
			if (softSetup.substring(softSetup.indexOf(".") + 1, softSetup.length() - (softSetup.indexOf(".") + 1))
					.length() == 1) {
				softSetup += "0";
			}
		} catch (Exception ex) {
		}

		if (!first.contains(".")) {
			first += ".00";
		}

		try {
			if (first.substring(first.indexOf(".") + 1, first.length() - (first.indexOf(".") + 1)).length() == 1) {
				first += "0";
			}
		} catch (Exception ex) {
		}

		if (!second.contains(".")) {
			second += ".00";
		}

		try {
			if (second.substring(second.indexOf(".") + 1, second.length() - (second.indexOf(".") + 1)).length() == 1) {
				second += "0";
			}
		} catch (Exception ex) {
		}

		if (!third.contains(".")) {
			third += ".00";
		}

		try {
			if (third.substring(third.indexOf(".") + 1, third.length() - (third.indexOf(".") + 1)).length() == 1) {
				third += "0";
			}
		} catch (Exception ex) {
		}

		if (!fourth.contains(".")) {
			fourth += ".00";
		}

		try {
			if (fourth.substring(fourth.indexOf(".") + 1, fourth.length() - (fourth.indexOf(".") + 1)).length() == 1) {
				fourth += "0";
			}
		} catch (Exception ex) {
		}

		if (discount != null) {
			if (!discount.contains(".")) {
				discount += ".00";
			}
		}

		try {
			if (discount.substring(discount.indexOf(".") + 1, discount.length() - (discount.indexOf(".") + 1))
					.length() == 1) {
				discount += "0";
			}
		} catch (Exception ex) {
		}

		// Server Path
		 String filePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\Pdf Images\\Invoice_t_lt.docx";
		
		//String imagesPath ="K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\";
		// Local path
		//String filePath = "D:\\Deepika\\crm\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t_lt.docx";
		//String filePath = "K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t_lt.docx";

		XWPFDocument doc = new XWPFDocument(OPCPackage.open(filePath));
		
		PdfWriter writer  = null ;
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		try {
		//String localPdfPath = "K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_" +booking.getScName() +" " + invoice.getId()+"_.pdf";
		String serverPdfPath = "C:\\Invoicing\\Invoice_" +booking.getScName() +" " + invoice.getId()+"_.pdf";	
		writer = PdfWriter.getInstance(document, new FileOutputStream(serverPdfPath));
		Rectangle ps = writer.getPageSize();
		document.open();
		String ServerImage = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\Pdf Images\\footer.png";
		//String LocalImage = "K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\footer.png";
		Image image1 = Image.getInstance(ServerImage);
		image1.setAbsolutePosition(0, 0);
		image1.scaleAbsolute(600, 70);
		document.add(image1);
		
		Image image2 = Image.getInstance("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\Pdf Images\\header.png");
		image2.setAbsolutePosition(0f, 800f);
		image2.scaleAbsolute(600, 70);
		document.add(image2);
	
		Image image3 = Image.getInstance("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\Pdf Images\\RaiseOnline.png");
		image3.setAbsolutePosition(60f, 740f);
		image3.scaleAbsolute(350, 50);
	
			document.add(image3);
		} catch (DocumentException e2) {
			e2.printStackTrace();
		}
		
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
		Chunk chunk = new Chunk("223 Marsh Wall\n" + "Snowdon House \n" + "London E14 9PG\n" + "T: 0844 504 5812\n"
				+ "F: 0844 504 5813\n" + "finance@raiseonlinetraining.co.uk\n" + "www.raiseonlinetraining.co.uk",
				smallBold);
		Paragraph para1 = new Paragraph(chunk);
		para1.setAlignment(Paragraph.ALIGN_RIGHT);
		para1.setSpacingBefore(40);
		para1.setSpacingAfter(40);
		para1.setIndentationRight(60);
		try {
			document.add(para1);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		PdfPTable table1 = new PdfPTable(3);
		
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(10);
		float[] columnWidths1 = { 1f };
		
		try {
			table2.setWidths(columnWidths1);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		PdfPCell outerTableCell1 = new PdfPCell(new Paragraph("Outer Table :Cell 1"));
		outerTableCell1.setFixedHeight(230f);
		PdfPTable nestedTable = new PdfPTable(3);
		
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10);
		
		PdfPCell ncell1 =  new PdfPCell();
		PdfPCell ncell3 =  new PdfPCell();
		  
		PdfPCell cell8 = new PdfPCell(new Paragraph(""));
		cell8.setBorder(PdfPCell.NO_BORDER);
		Font cell9Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
			
		PdfPCell cell9 = new PdfPCell();
		
		Font nestedCell2Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
		
		StringBuffer tableHTML = new StringBuffer();
		for (XWPFParagraph p : doc.getParagraphs()) {
			XmlCursor cursor = p.getCTP().newCursor();
			cursor.selectPath(
					"declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//*/w:txbxContent/w:p/w:r");
			List<XmlObject> ctrsintxtbx = new ArrayList<XmlObject>();
			while (cursor.hasNextSelection()) {
				cursor.toNextSelection();
				XmlObject obj = cursor.getObject();
				ctrsintxtbx.add(obj);
			}
			for (XmlObject obj : ctrsintxtbx) {
				CTR ctr = CTR.Factory.parse(obj.toString());
				XWPFRun bufferrun = new XWPFRun(ctr, (IRunBody) p);
				String text = bufferrun.getText(0);
				
				if (text != null) {
					
					if (text.contains("_INNUMBER_") && invoice.getId() != 0) {
						text = text.replace("_INNUMBER_", invoice.getId().toString());
					}

					text = text.replace("_ORDR_", "Order");

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDateTime submitDateTime = LocalDateTime.now();

					String inDates = dtf.format(submitDateTime);

					if (text.contains("_INDATE_")) {
						text = text.replace("_INDATE_", inDates);
					}

					if (text.contains("_PONO_") && invoice.getPoNo() != null) {
						text = text.replace("_PONO_", invoice.getPoNo());
					}

					if (text.contains("_ADDR_") && invoice.getSchAddr() != null) {
						
						text = text.replace("_ADDR_", invoice.getSchAddr());
					}

					
					if (text.contains("_ORDERDATE_")) {
						Date date = (booking.getTrDateDummy() != null
								? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(booking.getTrDateDummy()) : null);

						if (booking.getTraining() != null || booking.getTrLocation() != null) {
							if (booking.getTraining().toString().contains("Software")
									|| booking.getTraining().toString().contains("EYFSonline")) {

								text = text.replace("_ORDR_", "Order");
								text = text.replace("_ORDERDATE_", date.toString());
								
							} else {

								date = (booking.getTrDateDummy() != null
										? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(booking.getTrDateDummy()) : null);

								if (booking.getTrLocation() != null) {
									if (booking.getTrLocation().toString().contains("In-house")) {
										text = text.replace("_ORDR_", "Training");
																				
										text = text.replace("_ORDERDATE_",
												new SimpleDateFormat("dd/MM/yyyy").format(date).toString());
									}
								}
								
								text = text.replace("_ORDR_", "Training");
								text = text.replace("_ORDERDATE_",
										new SimpleDateFormat("dd/MM/yyyy").format(date).toString());
								text = text.replace("_ORDERDATE_", "");
							}
						}
						
						Font cell1Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
						Paragraph paragraph = new Paragraph(invoice.getSchAddr(), cell1Font);
						paragraph.setIndentationRight(20);
						paragraph.setLeading(15,0);   
						
						PdfPCell cell1 = new PdfPCell(paragraph);
						cell1.setPaddingLeft(25);
						cell1.setFixedHeight(150f);
						cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

						PdfPCell cell2 = new PdfPCell(new Paragraph(""));
						cell2.setBorder(PdfPCell.NO_BORDER);
						Font cell3Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
						PdfPCell cell3 = new PdfPCell(new Paragraph(
								"Invoice No.:               " + invoice.getId() + "\n\n" + 
								"Invoice Date:             " + inDates + "\n\n" + 
								"Training Date:           " +	new SimpleDateFormat("dd/MM/yyyy").format(date).toString() +"\n\n" + 
								"PO No.:                     "	+ invoice.getPoNo() + "\n\n"+
								"VAT No:                   173 5662 90\n\n", cell3Font));
						
						cell3.setFixedHeight(150f);
						cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell3.setPaddingLeft(25);
						table1.addCell(cell1);
						table1.addCell(cell2);
						table1.addCell(cell3);
						
						table1.setSpacingAfter(10);
						try {
							table1.setWidths(new float[] { 2f, 0.1f, 2f });
							document.add(table1);
						} catch (DocumentException e) {
							e.printStackTrace();
						}
					}
					bufferrun.setText(text, 0);
				}
				obj.set(bufferrun.getCTR());
			}
			
			
			CTBody ctbody = doc.getDocument().getBody();
			XmlCursor xmlcursor = ctbody.newCursor();
			QName qnameTbl = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tbl", "w");
			QName qnameFallback = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Fallback",
					"mc");

			List<CTTbl> allCTTbls = new ArrayList<CTTbl>();

			while (xmlcursor.hasNextToken()) {

				XmlCursor.TokenType tokentype = xmlcursor.toNextToken();
				if (tokentype.isStart()) {
					if (qnameTbl.equals(xmlcursor.getName())) {
						if (xmlcursor.getObject() instanceof CTTbl) {
							allCTTbls.add((CTTbl) xmlcursor.getObject());
						} else if (xmlcursor.getObject() instanceof XmlAnyTypeImpl) {
							allCTTbls.add(CTTbl.Factory.parse(xmlcursor.getObject().toString()));
						}
					} else if (qnameFallback.equals(xmlcursor.getName())) {
						xmlcursor.toEndToken();
					}
				}
			}

			for (CTTbl cTTbl : allCTTbls) {

				for (CTRow cTRow : cTTbl.getTrList()) {

					for (CTTc cTTc : cTRow.getTcList()) {

						for (CTP cTP : cTTc.getPList()) {
							for (CTR cTR : cTP.getRList()) {
								for (CTText cTText : cTR.getTList()) {
									tableHTML.append(cTText.getStringValue() + "\n");
									String text = cTText.getStringValue();
									if (text != null) {
										if (text.contains("Our invoice no") && invoice.getId() != 0) {
											text = text.replace("Our invoice no", invoice.getId().toString());
										}
										
										if (text.contains("_BANK_") && invoice.getPaymentType() != null) {
											
											if(invoice.getPaymentType().equals("L")){
											text = text.replace("_BANK_", "HSBC");
											}
											if (invoice.getPaymentType().equals("S")){
												text = text.replace("_BANK_","Santander");	
											}
										}
										
										if (text.contains("_ACT NO._") && invoice.getAccNo() != null) {
											text = text.replace("_ACT NO._", invoice.getAccNo().toString());
										}
										
										if (text.contains("_SORT CODE_") && invoice.getSortCode() != null) {
											text = text.replace("_SORT CODE_", invoice.getSortCode().toString());
										}
										
										try {
											try {
												if (invoice.getCancsurCharge().toString() == null
														|| invoice.getCancsurCharge().toString() == ""
														|| invoice.getCancsurCharge().toString() == "0"
														|| invoice.getCancsurCharge().toString() == "0.00"
														|| invoice.getCancsurCharge().toString() == "0.0") {

													text = text.replace("_CAN_", "");
													text = text.replace("_CANCOST_", "");
													// d.Tables.ElementAtOrDefault(3).Rows.ElementAt(6).Remove();
												} else {
													text = text.replace("_CAN_", "Cancellation Surcharge");

													String cansurtext = ((invoice.getCancsurCharge() != null)
															? invoice.getCancsurCharge() : null).toString();
													if (!cansurtext.contains(".")) {
														cansurtext += ".00";
													}
													try {

														if (cansurtext
																.substring(cansurtext.indexOf(".") + 1,
																		cansurtext.length()
																				- (cansurtext.indexOf(".") + 1))
																.length() == 1) {
															cansurtext += "0";
														}

													} catch (Exception e) {

													}
													text = text.replace("_CANCOST_", cansurtext);
												}
											} catch (Exception e) {
												// d.Tables.ElementAtOrDefault(3).Rows.ElementAt(6).Remove();
											}

											if (hasAdditionalDelegates(recordsDTO)) {

												if (invoiceTotalAdditionalFees <= 0) {
													invoiceAdditionalDelegates = ((booking.getDelegNum() != null)
															? booking.getDelegNum() : null) - 10;
													invoiceAdditionalFeesPerDelegate = (int) (((invoice
															.getAdditionalDelegates()) / invoiceAdditionalDelegates));
												}
												if (invoiceAdditionalDelegates <= 0 && invoiceTotalAdditionalFees > 0) {

													invoiceAdditionalDelegates = 0;
													training = ((training) + invoiceTotalAdditionalFees) + "";
													invoiceTotalAdditionalFees = 0;
													invoiceAdditionalFeesPerDelegate = 0;
													// d.Tables.ElementAtOrDefault(3).Rows.ElementAt(4).Remove();

													if (!training.contains(".")) {
														training += ".00";
													}
													try {

														if (training
																.substring(training.indexOf(".") + 1,
																		training.length() - (training.indexOf(".") + 1))
																.length() == 1) {
															training += "0";
														}

													} catch (Exception e) {

													}
												} else {
													String strInvoiceTotalAdditionalFees = "";
													strInvoiceTotalAdditionalFees = Integer
															.toString(invoiceTotalAdditionalFees);
													if (!strInvoiceTotalAdditionalFees.contains(".")) {
														strInvoiceTotalAdditionalFees += ".00";
													} else {
														if (strInvoiceTotalAdditionalFees.split(".")[1].length() == 1) {
															strInvoiceTotalAdditionalFees += "0";
														}
													}

													
												}

											} else {
												// d.Tables.ElementAtOrDefault(3).Rows.ElementAt(4).Remove();
											}

										} catch (Exception e) {
											// d.Tables.ElementAtOrDefault(3).Rows.ElementAt(4).Remove();
										}
										String strInvoiceTotalAdditionalFees = "";
										strInvoiceTotalAdditionalFees = Integer
												.toString(invoiceTotalAdditionalFees);
										if (!strInvoiceTotalAdditionalFees.contains(".")) {
											strInvoiceTotalAdditionalFees += ".00";
										} else {
											if (strInvoiceTotalAdditionalFees.split(".")[1].length() == 1) {
												strInvoiceTotalAdditionalFees += "0";
											}
										}
										text = text.replace("_ORDER0_", "");
										text = text.replace("_FIRSTDELEG0_", "Additional "
												+ invoiceAdditionalDelegates + " delegates at �"
												+ invoiceAdditionalFeesPerDelegate + " per delegate");
										text = text.replace("_PRICE0_", strInvoiceTotalAdditionalFees);
										
										DateTime rep = new DateTime();

										if (booking.getTraining().toString().contains("Software")
												|| booking.getTraining().toString().contains("EYFSonline")) {

											text = text.replace("_ORDR_", "Order");
											text = text.replace("_ORDERDATE_",
													new SimpleDateFormat("dd/MM/yyyy").format(rep).toString());
											if (isFutureDate() && bidInvoice == bid) {
												if (text != null && booking.getTraining() != null) {
													text = text.replace("_ORDER_",
															booking.getTraining().toString() + " " + amountInvoice
																	+ " pp * " + delegatesNumInvoice + " delegates");
												}

											} else {

												if (text != null && booking.getTraining() != null) {
													text = text.replace("_ORDER_", booking.getTraining().toString());
												}
											}

											if (text != null && booking.getfName() != null) {
												text = text.replace("_FIRSTDELEG_", booking.getfName());
											}

											double dousoft = 0.0;
											double dousoftSetup = 0.0;
											double dousoftvat = 0.0;
											String strDousoft = Double.toString(
													(invoice.getSoftCost() != null) ? invoice.getSoftCost() : 0.0);
											String strDouSoftSetup = Double.toString(
													(invoice.getSoftSetup() != null) ? invoice.getSoftSetup() : 0.0);
											String strDouSoftvat = Double.toString(
													(invoice.getVatAmount() != null) ? invoice.getVatAmount() : 0.0);

											text = text.replace("_PRICE_", (strDousoft + strDouSoftSetup));

											try {
												dousoftvat = (dousoft + dousoftSetup) * 20 / 100;
												String softvat = Double.toString(dousoftvat);

												double dousoftgross = dousoft + dousoftSetup + dousoftvat;
												String softGross = Double.toString(dousoftgross);

												if (!softvat.contains(".")) {
													softvat += ".00";
												}
												try {
													if (softvat
															.substring(softvat.indexOf(".") + 1,
																	softvat.length() - (softvat.indexOf(".") + 1))
															.length() == 1) {
														softvat += "0";
													}
												} catch (Exception e) {

												}

												if (!softGross.contains(".")) {
													softGross += ".00";
												}
												try {
													if (softGross
															.substring(softGross.indexOf(".") + 1,
																	softGross.length() - (softGross.indexOf(".") + 1))
															.length() == 1) {
														softGross += "0";
													}
												} catch (Exception e) {

												}

												text = text.replace("_FVAT_", finalVatPercentage);
												text = text.replace("_FGROSS_", softGross);

											} catch (Exception e) {
												text = text.replace("_FVAT_", addzero());
												text = text.replace("_FGROSS_", addzero());
											}
											if (discount.length() > 0) {
												text = text.replace("_TVAT_", addzero());
											} else {
												text = text.replace("_TVAT_", "");
											}

											text = text.replace("_DISCOUNT_", "");
											text = text.replace("_DISCI_", "");
											text = text.replace("_DVAT_", "");
											text = text.replace("_DGROSS_", "");
											text = text.replace("_ORDER1_", "");
											text = text.replace("_SECDELEG_", "");
											text = text.replace("_PRICE1_", "");
											text = text.replace("_KVAT_", "");
											text = text.replace("_KGROSS_", "");
											text = text.replace("_PRICE1N_", "");
											text = text.replace("_ORDER2_", "");
											text = text.replace("_THIRDDELEG_", "");
											text = text.replace("_PRICE2_", "");
											text = text.replace("_ORDER3_", "");
											text = text.replace("_FOURTHDELEG_", "");
											text = text.replace("_PRICE3_", "");
											text = text.replace("_MVAT_", "");
											text = text.replace("_MGROSS_", "");
											text = text.replace("_PRICE2N_", "");
											text = text.replace("_TOTSUM_", total);
											text = text.replace("_TOTVAT_", finalVat);

											try {

												Double douDiscount12 = 0.0;

												Double douSoft12 = 0.0;
												Double douSoftSetup12 = 0.0;

												Double additional12 = (double) invoiceTotalAdditionalFees;

												Double doutotal12 = 0.0;
												doutotal12 = douSoft12 - Math.abs(douDiscount12) + additional12
														+ douSoftSetup12;

												String total12 = doutotal12.toString();

												if (!total12.contains(".")) {
													total12 += ".00";
												}
												try {

													if (total12
															.substring(total12.indexOf(".") + 1,
																	total12.length() - (total12.indexOf(".") + 1))
															.length() == 1) {
														total12 += "0";
													}

												} catch (Exception e) {

												}

												text = text.replace("_TOTNET_", total12);
											} catch (Exception e) {
												text = text.replace("_TOTNET_", soft);
											}

											if (discount.length() > 0) {

												text = text.replace("_TRAVELCOST_", "Discount");
												text = text.replace("_TRACOST_", "-" + discount);
												text = text.replace("_TVAT_", addzero());
												text = text.replace("_TGROSS_", "-" + discount);
												text = text.replace("_DISC_", "-" + discount);
											} else {

												text = text.replace("_TRAVELCOST_", "");
												text = text.replace("_TRACOST_", "");
												text = text.replace("_TVAT_", "");
												text = text.replace("_TGROSS_", "");
												text = text.replace("_DISC_", addzero());
											}
											
											
											Phrase firstLine = new Phrase("Details\n ", boldFont);
											Phrase secondLine = new Phrase( booking.getTraining().toString() + " " + amountInvoice
														+ " pp * " + delegatesNumInvoice + " delegates" + booking.getfName() +"\n" 
													    + "" + "Additional "+ invoiceAdditionalDelegates + " delegates at �"
														+ invoiceAdditionalFeesPerDelegate + " per delegate\n" 
														+ booking.getTraining().toString() + " "+ booking.getSecondDeleg() + "\n" 
														/*+ booking.getTraining().toString() + " "
														+ booking.getThirdDeleg() + "\n"*/
														+ "Cancellation Surcharge"+ "\n"
														+ "Discount\n\n"
														+ "Invoice is due for immediate payment.\n\n"
															, normalFont );
											ncell1.addElement(firstLine );
											ncell1.addElement(secondLine );
											
											 Phrase pharseHeading = new Phrase("Unit Price\n ", boldFont );
											 Phrase pharseDetail = new Phrase(first + "\n\n" 
											 + strInvoiceTotalAdditionalFees +"\n\n" 
											/* + discount + "\n"*/
											 + discount + "\n" 
											 + invoice.getCancsurCharge() + "\n"
											 + discount + "\n\n\n\n\n\n\n\n\n", nestedCell2Font);
											 ncell3.addElement(pharseHeading);
											  ncell3.addElement(pharseDetail);
										} else {

											// Date date = ((booking.getTrDate()
											// != null) ?booking.getTrDate() :
											// null);

											Date date = (booking.getTrDateDummy() != null
													? convertDD_MM_YYYYToYYYY_MM_DDDateFormat(booking.getTrDateDummy())
													: null);
											text = text.replace("_ORDR_", "Training");
											text = text.replace("_ORDERDATE_",
													new SimpleDateFormat("dd/MM/yyyy").format(date).toString());

											text = text.replace("_ORDERDATE_", "");
											if (!booking.getTrLocation().toString().contains("In-house")) {

												if (booking.getDelegNum() != null) {
													switch (booking.getDelegNum()) {

													case 0:
														if (isFutureDate() && bidInvoice == bid) {
															text = text.replace("_ORDER_",
																	booking.getTraining().toString() + " "
																			+ amountInvoice + " pp * "
																			+ delegatesNumInvoice + " delegates");
														} else {
															if (text != null && booking.getTraining() != null) {
																text = text.replace("_ORDER_",
																		booking.getTraining().toString());
															}
														}

														if (text != null && booking.getfName() != null) {
															text = text.replace("_FIRSTDELEG_", booking.getfName());
														}
														text = text.replace("_PRICE_", first);
														text = text.replace("_FVAT_", firstVat);
														text = text.replace("_FGROSS_", firstGross);
														text = text.replace("_DISCOUNT_", "");
														text = text.replace("_DISCI_", "");
														text = text.replace("_DVAT_", "");
														text = text.replace("_DGROSS_", "");
														text = text.replace("_ORDER1_", "");
														text = text.replace("_SECDELEG_", "");
														text = text.replace("_PRICE1_", "");
														text = text.replace("_KVAT_", "");
														text = text.replace("_KGROSS_", "");
														text = text.replace("_PRICE1N_", "");
														text = text.replace("_ORDER2_", "");
														text = text.replace("_THIRDDELEG_", "");
														text = text.replace("_PRICE2_", "");
														text = text.replace("_MVAT_", "");
														text = text.replace("_MGROSS_", "");
														text = text.replace("_PRICE2N_", "");
														text = text.replace("_MVAT_", "");
														text = text.replace("_TOTSUM_", total);
														text = text.replace("_TOTVAT_", finalVat);

															Double douDiscount12 = 0.0;
															// Double.TryParse(discount,
															// out
															// douDiscount12);

															Double doufirst12 = 0.0;
															// Double.TryParse(first,
															// out doufirst12);

															Double doutotal12 = 0.0;
															Double cansur = 0.0;

															Double additional12 = (double) invoiceTotalAdditionalFees;

															// Double.TryParse(txtCanSur.Text,
															// out cansur);
															// Double.TryParse(txtCanSur.Text,
															// out cansur);
															doutotal12 = doufirst12 - Math.abs(douDiscount12) + cansur
																	+ additional12;

															String total12 = doutotal12.toString();
															try {
															if (!total12.contains(".")) {
																total12 += ".00";
															}
															try {

																if (total12
																		.substring(total12.indexOf(".") + 1,
																				total12.length()
																						- (total12.indexOf(".") + 1))
																		.length() == 1) {
																	total12 += "0";
																}
															} catch (Exception e) {

															}

															text = text.replace("_TOTNET_", total12);

														} catch (Exception e) {
															text = text.replace("_TOTNET_", first);
														}

														if (discount.length() > 0) {

															text = text.replace("_TRAVELCOST_", "Discount");
															text = text.replace("_TRACOST_", "-" + discount);
															text = text.replace("_TVAT_", addzero());
															text = text.replace("_TGROSS_", "-" + discount);
															text = text.replace("_DISC_", "-" + discount);

														} else {
															text = text.replace("_TRAVELCOST_", "");
															text = text.replace("_TRACOST_", "");
															text = text.replace("_TVAT_", "");
															text = text.replace("_TGROSS_", "");
															text = text.replace("_DISC_", addzero());
														}
														
														Phrase firstLine = new Phrase("Details\n ", boldFont );
														Phrase secondLine = new Phrase(booking.getTraining().toString() + " " + amountInvoice
																	+ " pp * " + delegatesNumInvoice + " delegates" + booking.getfName() +"\n" 
																    + "" + "Additional "+ invoiceAdditionalDelegates + " delegates at �"
																	+ invoiceAdditionalFeesPerDelegate + " per delegate\n" 
																	+ booking.getTraining().toString() + " "+ booking.getSecondDeleg() + "\n" 
																	/*+ booking.getTraining().toString() + " "
																	+ booking.getThirdDeleg() + "\n"*/
																	+ "Cancellation Surcharge"+ "\n"
																	+ "Discount\n\n\n"
																	+ "Invoice is due for immediate payment.\n"
																	, normalFont );
														
														ncell1.addElement(firstLine );
														ncell1.addElement(secondLine );
														
														 Phrase pharseHeading = new Phrase("Unit Price\n ", boldFont );
														 Phrase pharseDetail = new Phrase(first + "\n\n" 
														 + strInvoiceTotalAdditionalFees+"\n\n" 
														 + discount + "\n"
														/* + discount + "\n" */
														 + invoice.getCancsurCharge() + "\n" 
														 + discount + "\n\n\n\n\n\n\n\n\n", nestedCell2Font);
														 ncell3.addElement(pharseHeading);
														 ncell3.addElement(pharseDetail);
														 
														cell9 = new PdfPCell(
																new Paragraph(
														" Discount Amount:             " +"-"+ discount +"\n\n" 
														+ "Total Net Amount:           "+ total12 +"\n\n"
														+ "Total VAT Amount:           " + finalVat + "\n\n" 
														+ "Gross Total:                " + total +"\n\n",	cell9Font));
												 
														break;

													case 1:
														double netAmount;
														netAmount = ((invoice.getFirstDelegFee() != null)
																? invoice.getFirstDelegFee() : 0.0)
																+ ((invoice.getSecondDelegFee() != null)
																		? invoice.getSecondDelegFee() : 0.0)
																- ((invoice.getDiscount() != null)
																		? invoice.getDiscount() : 0.0)
																+ ((invoice.getCancsurCharge() != null)
																		? invoice.getCancsurCharge() : 0.0)
																+ invoiceTotalAdditionalFees;
														String strNetAmount = Double.toString(netAmount);
														if (!strNetAmount.contains(".")) {
															strNetAmount += ".00";
														}
														if (text != null && booking.getfName() != null) {
															text = text.replace("_FIRSTDELEG_", booking.getfName());
														}
														if (isFutureDate() && bidInvoice == bid) {
															if (booking.getTraining() != null) {
																text = text.replace("_ORDER_",
																		booking.getTraining().toString() + " "
																				+ amountInvoice + " pp * "
																				+ delegatesNumInvoice + " delegates");
															}

														} else {
															if (text != null && booking.getfName() != null) {
																text = text.replace("_FIRSTDELEG_", booking.getfName());
															}
														}
															
														text = text.replace("_PRICE_", first);
														text = text.replace("_FVAT_", firstVat);
														text = text.replace("_FGROSS_", firstGross);
														if (text != null && booking.getTraining() != null
																&& booking.getSecondDeleg() != null) {
															text = text.replace("_TRAVELCOST_",
																	booking.getTraining().toString() + " "
																			+ booking.getSecondDeleg());
														}
														text = text.replace("_TRACOST_", second);
														text = text.replace("_TVAT_", secondVat);
														text = text.replace("_TGROSS_", secondGross);
														text = text.replace("_ORDER1_", "");
														text = text.replace("_SECDELEG_", "");
														text = text.replace("_PRICE1_", "");
														text = text.replace("_KVAT_", "");
														text = text.replace("_KGROSS_", "");
														text = text.replace("_PRICE1N_", "");

														text = text.replace("_ORDER2_", "");
														text = text.replace("_THIRDDELEG_", "");
														text = text.replace("_PRICE2_", "");
														text = text.replace("_MVAT_", "");
														text = text.replace("_MGROSS_", "");
														text = text.replace("_PRICE2N_", "");

														text = text.replace("_TOTNET_", strNetAmount);
														text = text.replace("_TOTSUM_", total);
														text = text.replace("_TOTVAT_", finalVat);

														if (discount.length() > 0) {

															text = text.replace("_DISCOUNT_", "Discount");
															text = text.replace("_DISCI_", "-" + discount);
															text = text.replace("_DVAT_", addzero());
															text = text.replace("_DGROSS_", "-" + discount);
															text = text.replace("_DISC_", "-" + discount);
														} else {
															text = text.replace("_DISCOUNT_", "");
															text = text.replace("_DISCI_", "");
															text = text.replace("_DVAT_", "");
															text = text.replace("_DGROSS_", "");
															text = text.replace("_DISC_", addzero());
														}
														
														Phrase firstLine1 = new Phrase("Details\n ", boldFont );
														Phrase secondLine1= new Phrase( booking.getTraining().toString() + " " + amountInvoice
																	+ " pp * " + delegatesNumInvoice + " delegates" + booking.getfName() +"\n" 
																    + "" + "Additional "+ invoiceAdditionalDelegates + " delegates at �"
																	+ invoiceAdditionalFeesPerDelegate + " per delegate\n" 
																	+ booking.getTraining().toString() + " "+ booking.getSecondDeleg() + "\n" 
																	
																	+ "Cancellation Surcharge"+ "\n"
																	+ "Discount \n\n"
																	+ "Invoice is due for immediate payment.\n\n"
																	, normalFont );
														
														 ncell1.addElement(firstLine1 );
														 ncell1.addElement(secondLine1 );
													
														 Phrase pharseHeading1 = new Phrase("Unit Price\n\n ", boldFont );
														 Phrase pharseDetail1 = new Phrase(first + "\n\n" 
														 + strInvoiceTotalAdditionalFees+"\n" 
														 + discount + "\n"
														
														 + invoice.getCancsurCharge() + "\n" 
														 + "-"+discount + "\n\n\n\n\n\n\n\n\n", nestedCell2Font);
														  ncell3.addElement(pharseHeading1);
														  ncell3.addElement(pharseDetail1);
														 
														 cell9 = new PdfPCell(new Paragraph(
																  "Discount Amount:             " +"-"+ discount +"\n\n" 
																+ "Total Net Amount:             "+ strNetAmount +"\n\n"
																+ "Total VAT Amount:          " + finalVat + "\n\n" 
																+ "Gross Total:                       " + total +"\n\n",	cell9Font));
														 break;

													case 2:
														double netAmount1;
														netAmount1 = ((invoice.getFirstDelegFee() != null)
																? invoice.getFirstDelegFee() : 0.0)
																+ ((invoice.getSecondDelegFee() != null)
																		? invoice.getSecondDelegFee() : 0.0)
																+ ((invoice.getThirdDelegFee() != null)
																		? invoice.getThirdDelegFee() : 0.0)
																- ((invoice.getDiscount() != null)
																		? invoice.getDiscount() : 0.0)
																+ ((invoice.getCancsurCharge() != null)
																		? invoice.getCancsurCharge() : 0.0)
																+ invoiceTotalAdditionalFees;
														String strNetAmount1 = Double.toString(netAmount1);

														if (!strNetAmount1.contains(".")) {
															strNetAmount1 += ".00";
														}
														if (isFutureDate() && bidInvoice == bid) {
															if (booking.getTraining() != null) {
																text = text.replace("_ORDER_",
																		booking.getTraining().toString() + " "
																				+ amountInvoice + " pp * "
																				+ delegatesNumInvoice + " delegates");
															}

														} else {
															if (text != null && booking.getTraining() != null) {
																text = text.replace("_ORDER_",
																		booking.getTraining().toString());
															}
														}

														if (text != null && booking.getfName() != null) {
															text = text.replace("_FIRSTDELEG_", booking.getfName());
														}
														text = text.replace("_PRICE_", first);
														text = text.replace("_FVAT_", firstVat);
														text = text.replace("_FGROSS_", firstGross);
														if (text != null && booking.getTraining() != null
																&& booking.getSecondDeleg() != null) {
															text = text.replace("_TRAVELCOST_",
																	booking.getTraining().toString() + " "
																			+ booking.getSecondDeleg());
														}
														text = text.replace("_TRACOST_", second);
														text = text.replace("_TVAT_", secondVat);
														text = text.replace("_TGROSS_", secondGross);
														if (text != null && booking.getTraining() != null
																&& booking.getThirdDeleg() != null) {
															text = text.replace("_DISCOUNT_",
																	booking.getTraining().toString() + " "
																			+ booking.getThirdDeleg());
														}
														text = text.replace("_DISCI_", third);
														text = text.replace("_DVAT_", thirdVat);
														text = text.replace("_DGROSS_", thirdGross);
														text = text.replace("_SECDELEG_", "");

														text = text.replace("_ORDER2_", "");
														text = text.replace("_THIRDDELEG_", "");
														text = text.replace("_PRICE2_", "");
														text = text.replace("_MVAT_", "");
														text = text.replace("_MGROSS_", "");
														text = text.replace("_PRICE2N_", "");

														text = text.replace("_TOTNET_", strNetAmount1);
														text = text.replace("_TOTSUM_", total);
														text = text.replace("_TOTVAT_", finalVat);

														if (discount.length() > 0) {
															text = text.replace("_ORDER1_", "Discount");
															text = text.replace("_PRICE1_", "-" + discount);
															text = text.replace("_KVAT_", addzero());
															text = text.replace("_KGROSS_", "-" + discount);
															text = text.replace("_PRICE1N_", addzero());
															text = text.replace("_DISC_", "-" + discount);
														} else {
															text = text.replace("_ORDER1_", "");
															text = text.replace("_PRICE1_", "");
															text = text.replace("_KVAT_", "");
															text = text.replace("_KGROSS_", "");
															text = text.replace("_PRICE1N_", "");
															text = text.replace("_DISC_", addzero());
														}
																												 
														Phrase firstLine2 = new Phrase("Details\n ", boldFont );
														Phrase secondLine2 = new Phrase( booking.getTraining().toString() + " " + amountInvoice
																	+ " pp * " + delegatesNumInvoice + " delegates" + booking.getfName() +"\n" 
																    + "" + "Additional "+ invoiceAdditionalDelegates + " delegates at �"
																	+ invoiceAdditionalFeesPerDelegate + " per delegate\n" 
																	+ booking.getTraining().toString() + " "+ booking.getSecondDeleg() + "\n" 
																	/*+ booking.getTraining().toString() + " "
																	+ booking.getThirdDeleg() + "\n"*/
																	+ "Cancellation Surcharge"+ "\n"
																	+ "Discount\n\n\n" 
																	+ "Invoice is due for\n",
																	normalFont );
														
														ncell1.addElement(firstLine2 );
														ncell1.addElement(secondLine2 );
														
														 Phrase pharseHeading2 = new Phrase("Unit Price\n ", boldFont );
														 Phrase pharseDetail2 = new Phrase(first + "\n\n" 
														 + strInvoiceTotalAdditionalFees+"\n\n" 
														 + discount + "\n"
														/* + discount + "\n" */
														 + invoice.getCancsurCharge() + "\n" 
														 + discount + "\n\n\n\n\n\n\n\n\n", nestedCell2Font);
														 ncell3.addElement(pharseHeading2);
														 ncell3.addElement(pharseDetail2);
														  
														 cell9 = new PdfPCell(new Paragraph(
																	" Discount Amount:             " +"-"+ discount +"\n\n" 
																	+ "Total Net Amount:           "+ strNetAmount1 +"\n\n"
																	+ "Total VAT Amount:           " + finalVat + "\n\n" 
																	+ "Gross Total:                " + total +"\n\n",	cell9Font));
															
														break;

													case 3:

														double netAmount2;
														netAmount2 = ((invoice.getFirstDelegFee() != null)
																? invoice.getFirstDelegFee() : 0.0)
																+ ((invoice.getSecondDelegFee() != null)
																		? invoice.getSecondDelegFee() : 0.0)
																+ ((invoice.getThirdDelegFee() != null)
																		? invoice.getThirdDelegFee() : 0.0)
																+ ((invoice.getFourthDelegFee() != null)
																		? invoice.getFourthDelegFee() : 0.0)
																- ((invoice.getDiscount() != null)
																		? invoice.getDiscount() : 0.0)
																+ ((invoice.getCancsurCharge() != null)
																		? invoice.getCancsurCharge() : 0.0)
																+ invoiceTotalAdditionalFees;
														String strNetAmount2 = Double.toString(netAmount2);

														if (text != null && booking.getfName() != null) {
															text = text.replace("_FIRSTDELEG_", booking.getfName());
														}  
														
														if (!strNetAmount2.contains(".")) {
															strNetAmount2 += ".00";
														}
														if (isFutureDate() && bidInvoice == bid) {
															if (booking.getTraining() != null) {
																text = text.replace("_ORDER_",
																		booking.getTraining().toString() + " "
																				+ amountInvoice + " pp * "
																				+ delegatesNumInvoice + " delegates");

															}

														} else {
															if (text != null && booking.getTraining() != null) {
																text = text.replace("_ORDER_",
																		booking.getTraining().toString());
															}
														}

														if (text != null && booking.getfName() != null) {
															text = text.replace("_FIRSTDELEG_", booking.getfName());
														}
														text = text.replace("_PRICE_", first);
														text = text.replace("_FVAT_", firstVat);
														text = text.replace("_FGROSS_", firstGross);
														if (text != null && booking.getTraining() != null
																&& booking.getSecondDeleg() != null) {
															text = text.replace("_TRAVELCOST_",
																	booking.getTraining().toString() + " "
																			+ booking.getSecondDeleg());
														}
														text = text.replace("_TRACOST_", second);
														text = text.replace("_TVAT_", secondVat);
														text = text.replace("_TGROSS_", secondGross);
														if (text != null && booking.getTraining() != null
																&& booking.getThirdDeleg() != null) {
															text = text.replace("_DISCOUNT_",
																	booking.getTraining().toString() + " "
																			+ booking.getThirdDeleg());
														}
														text = text.replace("_DISCI_", third);
														text = text.replace("_DVAT_", thirdVat);
														text = text.replace("_DGROSS_", thirdGross);
														if (text != null && booking.getTraining() != null) {
															text = text.replace("_ORDER1_",
																	booking.getTraining().toString());
														}
														if (text != null && booking.getSecondDeleg() != null) {
															text = text.replace("_SECDELEG_", booking.getSecondDeleg());
														}

														text = text.replace("_PRICE1_", fourth);
														text = text.replace("_KVAT_", fourthVat);
														text = text.replace("_KGROSS_", fourthGross);
														text = text.replace("_PRICE1N_", fourth);
														text = text.replace("_THIRDDELEG_", "");
														text = text.replace("_PRICE2_", "");

														text = text.replace("_PRICE2N_", "");
														text = text.replace("_TOTNET_", strNetAmount2);
														text = text.replace("_TOTSUM_", total);
														text = text.replace("_TOTVAT_", finalVat);

														if (discount.length() > 0) {
															text = text.replace("_ORDER2_", "Discount");
															text = text.replace("_PRICE2_", "-" + discount);
															text = text.replace("_PRICE2N_", addzero());
															text = text.replace("_MVAT_", addzero());
															text = text.replace("_MGROSS_", "-" + discount);
															text = text.replace("_DISC_", discount);
														} else {
															text = text.replace("_ORDER2_", "");
															text = text.replace("_PRICE2_", "");
															text = text.replace("_MVAT_", "");
															text = text.replace("_MGROSS_", "");
															text = text.replace("_PRICE2N_", "");
															text = text.replace("_DISC_", addzero());
														}
													
														Phrase firstLine3 = new Phrase("Details\n ", boldFont );
														Phrase secondLine3 = new Phrase( booking.getTraining().toString() + " " + amountInvoice
																	+ " pp * " + delegatesNumInvoice + " delegates" + booking.getfName() +"\n" 
																    + "" + "Additional "+ invoiceAdditionalDelegates + " delegates at �"
																	+ invoiceAdditionalFeesPerDelegate + " per delegate\n" 
																	+ booking.getTraining().toString() + " "+ booking.getSecondDeleg() + "\n" 
																	/*+ booking.getTraining().toString() + " "
																	+ booking.getThirdDeleg() + "\n"*/
																	+ "Cancellation Surcharge"+ "\n"
																	+ "Discount\n\n\n" 
																	+ "Invoice is due for\n",
																	normalFont );
														
														ncell1.addElement(firstLine3 );
														ncell1.addElement(secondLine3 );
														
														 Phrase pharseHeading3 = new Phrase("Unit Price\n ", boldFont );
														 Phrase pharseDetail3 = new Phrase(first + "\n\n" 
														 + strInvoiceTotalAdditionalFees+"\n\n" 
														 + discount + "\n"
														/* + discount + "\n" */
														 + invoice.getCancsurCharge() + "\n" 
														 + discount + "\n\n\n\n\n\n\n\n\n", nestedCell2Font);
														 ncell3.addElement(pharseHeading3);
														 ncell3.addElement(pharseDetail3);
														
														 cell9 = new PdfPCell(new Paragraph(
																	" Discount Amount:             " +"-"+ discount +"\n\n" 
																	+ "Total Net Amount:           "+ strNetAmount2 +"\n\n"
																	+ "Total VAT Amount:           " + finalVat + "\n\n" 
																	+ "Gross Total:                " + total +"\n\n",	cell9Font));
														 
														break;
													}
												}

											} else {

												text = text.replace("_ORDR_", "Training");
												// docFile.replace("_ORDERDATE_",
												// date.ToShortDateString());
												text = text.replace("_ORDERDATE_", date.toString());
												if (isFutureDate() && bidInvoice == bid) {
													if (booking.getTraining() != null) {

													}
													text = text.replace("_ORDER_",
															booking.getTraining().toString() + " " + amountInvoice
																	+ " pp * " + delegatesNumInvoice + " delegates");
												} else {
													if (text != null && booking.getTraining() != null) {
														text = text.replace("_ORDER_",
																booking.getTraining().toString());
													}
												}

												if (text != null && booking.getfName() != null) {
													text = text.replace("_FIRSTDELEG_", booking.getfName());
												}

												try {
													double doutraining = 0.0;
													double doutrainingvat = 0.0;
													// double.TryParse(training,
													// out doutraining);
													doutrainingvat = booking.getTraining() * 20 / 100;
													String trainingvat = Double.toString(doutrainingvat);

													double doutraininggross = doutraining + doutrainingvat;
													String trainingGross = Double.toString(doutraininggross);

													if (!trainingvat.contains(".")) {
														trainingvat += ".00";
													}
													try {
														if (trainingvat
																.substring(trainingvat.indexOf(".") + 1,
																		trainingvat.length()
																				- (trainingvat.indexOf(".") + 1))
																.length() == 1) {
															trainingvat += "0";
														}

													} catch (Exception e) {

													}
													if (!trainingGross.contains(".")) {
														trainingGross += ".00";
													}
													try {

														if (trainingGross
																.substring(trainingGross.indexOf(".") + 1,
																		trainingGross.length()
																				- (trainingGross.indexOf(".") + 1))
																.length() == 1) {
															trainingGross += "0";
														}

													} catch (Exception e) {

													}

													text = text.replace("_FVAT_", trainingvat);
													text = text.replace("_FGROSS_", trainingGross);

												} catch (Exception e) {
													text = text.replace("_FVAT_", addzero());
													text = text.replace("_FGROSS_", training);
												}

												text = text.replace("_PRICE_", training);
												text = text.replace("_FVAT_", addzero());

												text = text.replace("_TRAVELCOST_", "Travel Cost");
												text = text.replace("_TRACOST_", travel);
												text = text.replace("_TVAT_", addzero());
												text = text.replace("_TGROSS_", travel);

												String totNetA = Double.toString(
														((booking.getTraining() != null) ? booking.getTraining() : 0)
																+ ((booking.getTraining() != null)
																		? booking.getTraining() : 0));
												String totNetToPut = "";
												String cutOut = "";

												if (totNetA.contains(".") && totNetA.toString().length() > 2) {
													cutOut = totNetA;
													int checkRem = 0;
													cutOut = cutOut.substring(cutOut.length() - 2, 2);

													if (!cutOut.contains(".")) {
														cutOut = cutOut.substring(0, 1);
														// int.TryParse(cutOut,
														// out checkRem);
														if (checkRem == 0) {
															totNetToPut = totNetA;
														} else {
															totNetToPut = totNetA + "0";
														}
													} else {
														totNetToPut = totNetA + "0";
													}
												} else {
													totNetToPut = totNetA + ".00";
												}
												text = text.replace("_ORDER1_", "");
												text = text.replace("_SECDELEG_", "");
												text = text.replace("_PRICE1_", "");
												text = text.replace("_KVAT_", "");
												text = text.replace("_KGROSS_", "");
												text = text.replace("_PRICE1N_", "");

												text = text.replace("_ORDER2_", "");
												text = text.replace("_THIRDDELEG_", "");
												text = text.replace("_PRICE2_", "");
												text = text.replace("_MVAT_", "");
												text = text.replace("_MGROSS_", "");
												text = text.replace("_PRICE2N_", "");
												text = text.replace("_MVAT_", "");

												

													Double douDiscount12 = 0.0;
													// Double.TryParse(discount,
													// out douDiscount12);

													Double doutotNetToPut12 = 0.0;
													// Double.TryParse(totNetToPut,
													// out
													// doutotNetToPut12);

													Double doutotal12 = 0.0;
													doutotal12 = doutotNetToPut12 - Math.abs(douDiscount12);
													doutotal12 = doutotal12 + invoiceTotalAdditionalFees;
													String total12 = Double.toString(doutotal12);
													try {
													if (!total12.contains(".")) {
														total12 += ".00";
													}
													try {
														if (total12
																.substring(total12.indexOf(".") + 1,
																		total12.length() - (total12.indexOf(".") + 1))
																.length() == 1) {
															total12 += "0";
														}
													} catch (Exception e) {

													}
													text = text.replace("_TOTNET_", total12);
												} catch (Exception e) {
													text = text.replace("_TOTNET_", totNetToPut);
												}

												text = text.replace("_TOTSUM_", total);
												text = text.replace("_TOTVAT_", finalVat);

												if (discount.length() > 0) {
													text = text.replace("_DISCOUNT_", "Discount");
													text = text.replace("_DISCI_", "-" + discount);
													text = text.replace("_DVAT_", addzero());
													text = text.replace("_DGROSS_", "-" + discount);
													text = text.replace("_DISC_", "-" + discount);

												} else {
													text = text.replace("_DISCOUNT_", "");
													text = text.replace("_DISCI_", "");
													text = text.replace("_DVAT_", "");
													text = text.replace("_DGROSS_", "");
													text = text.replace("_DISC_", addzero());

												}
																								
												Phrase firstLine4 = new Phrase("Details\n ", boldFont );
												Phrase secondLine4 = new Phrase(
														 	 booking.getTraining().toString() + " " + amountInvoice
															+ " pp * " + delegatesNumInvoice + " delegates" + booking.getfName() +"\n" 
														    + "" + "Additional "+ invoiceAdditionalDelegates + " delegates at �"
															+ invoiceAdditionalFeesPerDelegate + " per delegate\n\n" 
															+ booking.getTraining().toString() + " "+ booking.getSecondDeleg() + "\n" 
															/*+ booking.getTraining().toString() + " "
															+ booking.getThirdDeleg() + "\n"*/
															+ "Cancellation Surcharge"+ "\n"
															+ "Discount\n\n\n" 
															+ "Invoice is due for \n",
															normalFont );
												
												ncell1.addElement(firstLine4 );
												ncell1.addElement(secondLine4 );
												
												Phrase pharseHeading4 = new Phrase("Unit Price\n ", boldFont );
												 Phrase pharseDetail4 = new Phrase( first + "\n\n" 
												 + strInvoiceTotalAdditionalFees+"\n\n" 
												 + discount + "\n"
												/* + discount + "\n" */
												 + invoice.getCancsurCharge() + "\n" 
												 + discount + "\n\n\n\n\n\n\n\n\n", nestedCell2Font);
												  ncell3.addElement(pharseHeading4);
												  ncell3.addElement(pharseDetail4);
												
												cell9 = new PdfPCell(new Paragraph(
														" Discount Amount:             " +"-"+ discount +"\n\n" 
														+ "Total Net Amount:           "+ total12 +"\n\n"
														+ "Total VAT Amount:           " + finalVat + "\n\n" 
														+ "Gross Total:                " + total +"\n\n",	cell9Font));
											 
											}
										}
									}
									cTText.set(text);
								}
							}
						}
					}
				}
			}
		}

		// Local path
		//String fileStorgePath = "D:\\Deepika\\crm\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_" +booking.getScName() +" "+ invoice.getId()+ "_.docx";
		//String fileStorgePath = "K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_" + booking.getScName() +" "+ invoice.getId()+ "_.docx";
		// Server Path
		 String fileStorgePath ="C:\\Invoicing\\Invoice_" + booking.getScName() +" "+ invoice.getId()+ "_.docx";

		doc.write(new FileOutputStream(fileStorgePath));
		
		
		try {
						  ncell1.setVerticalAlignment(Element.ALIGN_LEFT);
						  //ncell1.setPaddingLeft(10);
			
						  ncell1.setFixedHeight(200f);
						  //ncell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						  ncell1.setBorder(PdfPCell.NO_BORDER);
						  PdfPCell ncell2 = new PdfPCell(new Paragraph("",normalFont));
						  ncell2.setVerticalAlignment(Element.ALIGN_LEFT);
						 // ncell2.setPaddingLeft(10);
						  ncell2.setFixedHeight(200f);
						  ncell2.setBorder(PdfPCell.NO_BORDER);
						  //Font nestedCell2Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
						  //PdfPCell ncell3 = new PdfPCell(new Paragraph("Unit Price\n\n" + first + "\n\n" + "_PRICE0_\n\n" + discount + "\n\n" + discount + "\n\n", nestedCell2Font));
						  
						  ncell3.setVerticalAlignment(Element.ALIGN_RIGHT);
						  ncell3.setPaddingLeft(50);
						  ncell3.setFixedHeight(200f);
						//ncell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
						  ncell3.setBorder(PdfPCell.NO_BORDER);
						  nestedTable.addCell(ncell1);
						  nestedTable.addCell(ncell2);
						  nestedTable.addCell(ncell3);
					      outerTableCell1.addElement(nestedTable);
			              table2.addCell(outerTableCell1);
					      document.add(table2);

			 
			PdfPTable table3 = new PdfPTable(5);
			float[] columnWidths2 = { 2f, 0.1f, 2f, 0.1f, 2f };
			table3.setWidths(columnWidths2);
			Font cell5Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
			
			PdfPCell cell5 = new PdfPCell();
			 Phrase phraseHeading = new Phrase("Please send a cheque payable to: \n ", boldFont );
			 Phrase phraseDetails = new Phrase(
					"Please send a cheque payable to:\n" + "RAISEonline Training\n" + "and send it to:\n"
							+ "RAISEonline Training\n" + "225 Marsh Wall\n" + "Snowdown House\n" + "London E14 9FW\n",
							cell5Font);
			 
			cell5.addElement(phraseHeading);
			cell5.addElement(phraseDetails); 
			cell5.setFixedHeight(200f);
			cell5.setVerticalAlignment(Element.ALIGN_LEFT);
			cell5.setPaddingTop(10);
			cell5.setPaddingLeft(10);
			
			//cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell cell6 = new PdfPCell(new Paragraph(""));
			cell6.setBorder(PdfPCell.NO_BORDER);
			Font cell6Font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
			
			String invoiceDetail = (invoice.getPaymentType() != null) ? invoice.getPaymentType() : "";
		
			if(invoice.getPaymentType().equals("L")){
				    invoiceDetail =  invoiceDetail.replace(invoiceDetail, "HSBC");
			}
			if (invoice.getPaymentType().equals("S")){
					invoiceDetail = invoiceDetail.replace(invoiceDetail, "Santander");
			}
			
			PdfPCell cell7 = new PdfPCell();
			 Phrase pharseHeadng = new Phrase("BACS Payment Details \n ", boldFont );
			 Phrase pharseDetails = new Phrase("Name of the Bank:\t"
					+ invoiceDetail + "\n" + "Payable to:\t RAISEonline Training\n" + "Ref:\t "+invoice.getId()+"\n"
					+ "Account Number:\t "+invoice.getAccNo()+"\n" + "Sort Code:\t "+invoice.getSortCode()+"\n" + "Thank you", cell6Font);
			
			 cell7.addElement(pharseHeadng);
			 cell7.addElement(pharseDetails );
			
			cell7.setPaddingLeft(10);
			 
			cell7.setFixedHeight(200f);
			//cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			cell9.setFixedHeight(200f);
			cell9.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell9.setPaddingTop(25);
			cell9.setPaddingLeft(10);
			table3.addCell(cell5);
			table3.addCell(cell6);
			table3.addCell(cell7);
			table3.addCell(cell8);
			table3.addCell(cell9);
			document.add(table3);

			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void sendEMailThroughOUTLOOK_LT() {
		Booking booking = new Booking();
		boolean outlookIsOn = false;

		// ================= system process kill and again start remaining
		/*
		 * for(proc.destroy() : Runtime.getRuntime().) {
		 */
		String taskList = null;
		try {
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((taskList = input.readLine()) != null) {
				System.out.println(taskList); // <-- Parse data here.
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}

		if (taskList.contains("OUTLOOK.EXE")) {
			outlookIsOn = true;
		}
		/* } */

		if (!outlookIsOn) {
			// System.Diagnostics.Process.Start("OUTLOOK.EXE");
			String outLookExe;
			try {
				Process p = Runtime.getRuntime().exec("OUTLOOK.EXE");
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((outLookExe = input.readLine()) != null) {
					System.out.println(outLookExe); // <-- Parse data here.
				}
				input.close();
			} catch (Exception err) {
				err.printStackTrace();
			}
		}

		String to_per = booking.getInvEmail();
		String cc_per = booking.getEmail();

		/*
		 * Outlook.Application objOtlk = new Outlook.Application();
		 * 
		 * Outlook.MailItem objMailItm =
		 * (Outlook.MailItem)(objOtlk.CreateItem(Outlook.OlItemType.olMailItem))
		 * ;
		 * 
		 * objMailItm.To = to_per;
		 * 
		 * objMailItm.CC = cc_per;
		 * 
		 * objMailItm.Importance = Outlook.OlImportance.olImportanceHigh;
		 * 
		 * objMailItm.Subject = cmbCourse.Text.ToString() + " - Invoice :" +
		 * txtInNumber.Text; String subjectInfo = "";
		 */

	}

	private String addzero() {
		// return "0.00".PadLeft(8);
		// String str = StringUtils.leftPad("0.00", 8);

		char padding = '-';
		String str = "";
		String pad = "0.00";
		for (int i = 0; i < 8; i++) {
			pad += padding;
		}
		return pad + str;

		// return String.format("0.00", 8);
	}

	/*
	 * private boolean isFutureDate() { DateTime submitDateTime =
	 * dtpsubmitdate.Value; if (submitDateTime >= FUTUREDATE) { return true; }
	 * return false; }
	 */

	public void btnConfEmailClick(Booking booking) throws Exception {
		System.out.println("inside btn ... ");

		// Booking booking = recordsDTO.getBooking();

		// C# inbuild function
		// btnSave.PerformClick();

		String parBuilder = "";
		// string trainingId = getTrainingID().ToString(); VB commented

		String title = booking.getTitle();

		// System.out.println( "Get Booking title "+title);

		booking.getfName();
		// System.out.println(booking.getfName());
		booking.getScName();
		// System.out.println(booking.getScName());

		booking.getSecondDeleg();
		// System.out.println(booking.getSecondDeleg());
		booking.getThirdDeleg();
		// System.out.println( booking.getThirdDeleg());
		booking.getFourthDeleg();
		// System.out.println(booking.getFourthDeleg());
		booking.getEmail();
		// System.out.println(booking.getEmail());
		booking.getTel();
		// System.out.println(booking.getTel());

		// string varTrLocationForMail = cmbTrLocation.Text.ToString();
		// ===================== check getter
		// String trLocationForMail = booking.getTrLocation().toString();
		String trLocationForMail = "2";

		// string varTrLocation = getLocationId().ToString();
		// ===================== check getter both are same
		// String trLocation = booking.getTrLocation().toString();
		String trLocation = "17";

		System.out.println("location " + trLocation);

		booking.getInvEmail();
		System.out.println(booking.getInvEmail());
		// string varTrainDateForMail = dtpTrDate.Value.Day.ToString() + "/" +
		// dtpTrDate.Value.Month.ToString() + "/" +
		// dtpTrDate.Value.Year.ToString();
		// String trainDateForMail = booking.getTrDate().toString(); // Check
		// date and break a date

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); String
		 * trainDateForMail = sdf.format(booking.getTrDate());
		 */

		String trainDateForMail = booking.getTrDate().toString(); // For API
		// String trainDateForMail= booking.getTrDateDummy().toString(); // For
		// UI

		// System.out.println(" trainDateForMail "+trainDateForMail);

		String trainingName = booking.getTrainingName();
		// System.out.println(trainingName);

		if (trainingName.contains("&")) {
			trainingName = trainingName.replace("&", "%26"); // Check replace
																// Value
		}

		// string varTrainSoft = getTrainingID().ToString();
		String trainSoft = booking.getTraining().toString();
		// System.out.println(trainSoft);
		String BookEnq = booking.getStatus().toString();
		// System.out.println(BookEnq);
		String message = strCheck1(booking.getNotes());
		// System.out.println(message);
		booking.getSecondDelegEmail();
		// System.out.println(booking.getSecondDelegEmail());
		booking.getThirdDelegEmail();
		// System.out.println(booking.getThirdDelegEmail());
		booking.getFourthDelegEmail();
		// System.out.println(booking.getFourthDelegEmail());

		System.out.println("End .......... Finish");

		// String url =
		// "http://www.raise-onlinetraining.com/conf_email_prog.php?";

		SendConfirmationMail(booking, parBuilder, title, trLocationForMail, trLocation, trainDateForMail, trainingName,
				trainSoft, BookEnq, message);

		String messageForUsr = "Confirmation email hase been sent to " + booking.getTitle() + " " + booking.getfName()
				+ "\n" + "To: " + booking.getEmail();

		// MessageBox.Show(messageForUsr, "Email has been sent",
		// MessageBoxButtons.OK, MessageBoxIcon.Information);
		// JOptionPane.showMessageDialog(messageForUsr, "Email has been sent.");

		// Message Box Show
		Toolkit.getDefaultToolkit().beep();
		JOptionPane optionPane = new JOptionPane(messageForUsr, JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog("Email has been sent.");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}

	public void SendConfirmationMail(Booking booking, String parBuilder, String title, String trLocationForMail,
			String trLocation, String trainDateForMail, String trainingName, String trainSoft, String bookEnq,
			String message) throws Exception {
		System.out.println("Calling getFile Content Method... " + booking.getTitle());
		getHTMLfIlecontents(booking, parBuilder, title, trLocationForMail, trLocation, trainDateForMail, trainingName,
				trainSoft, bookEnq, message);
	}

	// getHTMLfIlecontents no argument method in desktop but we are declare with
	// argument 21/03/2018
	private void getHTMLfIlecontents(Booking booking, String parBuilder, String title, String trLocationForMail,
			String trLocation, String trainDateForMail, String trainingName, String trainSoft, String bookEnq,
			String message) throws Exception {
		System.out.println(booking.getTitle());
		String htmlFileEmail = "";

		/*
		 * switch (trainSoft) {
		 * 
		 * case "1": if (trLocation == "1") { htmlFileEmail =
		 * fileGetContents("In-house_1.htm"); } else { htmlFileEmail =
		 * fileGetContents("Any_location_1.htm"); } break;
		 * 
		 * case "2": htmlFileEmail = fileGetContents("2.htm");
		 * 
		 * break;
		 * 
		 * case "3": if (trLocation == "1") { htmlFileEmail =
		 * fileGetContents("In-house_3.htm"); } else { htmlFileEmail =
		 * fileGetContents("Any_location_3.htm"); } // break; // htmlFileEmail =
		 * file_get_contents("3.htm"); break; case "4": htmlFileEmail =
		 * fileGetContents("4.htm"); break;
		 * 
		 * case "5": if (trLocation == "1") { htmlFileEmail =
		 * fileGetContents("In-house_5.htm"); } else { htmlFileEmail =
		 * fileGetContents("Any_location_5.htm"); } // htmlFileEmail =
		 * file_get_contents("1.htm"); break; case "6": if (trLocation == "1") {
		 * htmlFileEmail = fileGetContents("In-house_6.htm"); } else {
		 * htmlFileEmail = fileGetContents("Any_location_6.htm"); } break;
		 * 
		 * case "28": if (trLocation == "1") { htmlFileEmail =
		 * fileGetContents("In-house_28.htm"); } else { htmlFileEmail =
		 * fileGetContents("Any_location_28.htm"); } break;
		 * 
		 * case "7": htmlFileEmail = fileGetContents("7.htm"); break;
		 * 
		 * case "8": htmlFileEmail = fileGetContents("8.htm"); break;
		 * 
		 * case "9": htmlFileEmail = fileGetContents("9.htm"); break;
		 * 
		 * case "10": htmlFileEmail = fileGetContents("10.htm"); break;
		 * 
		 * case "11": htmlFileEmail = fileGetContents("11.htm"); break;
		 * 
		 * case "12": htmlFileEmail = fileGetContents("12.htm"); break;
		 * 
		 * case "13": htmlFileEmail = fileGetContents("13.htm"); break;
		 * 
		 * case "14": if (bookEnq == "Booking") { htmlFileEmail =
		 * fileGetContents("14_b.htm"); } else { htmlFileEmail =
		 * fileGetContents("14.htm"); } break; case "15": if (bookEnq ==
		 * "Booking") { htmlFileEmail = fileGetContents("15_b.htm"); } else {
		 * htmlFileEmail = fileGetContents("15.htm"); } break; case "16":
		 * htmlFileEmail = fileGetContents("16.htm"); break; case "17":
		 * htmlFileEmail = fileGetContents("17.htm"); break; case "18":
		 * htmlFileEmail = fileGetContents("18.htm"); break; case "19":
		 * htmlFileEmail = fileGetContents("19.htm"); break; case "20":
		 * htmlFileEmail = fileGetContents("10.htm"); break; case "21":
		 * htmlFileEmail = fileGetContents("10.htm"); break;
		 * 
		 * case "22": if (bookEnq == "Booking") { htmlFileEmail =
		 * fileGetContents("22_b.htm"); } else { htmlFileEmail =
		 * fileGetContents("22.htm"); } break; case "23": htmlFileEmail =
		 * fileGetContents("23.htm"); break; case "24": htmlFileEmail =
		 * fileGetContents("23.htm"); break; case "25": htmlFileEmail =
		 * fileGetContents("25.htm"); break; }
		 */

		String bodyOfMessage = contentsChange(htmlFileEmail, booking, trLocationForMail, trainDateForMail, trainingName,
				trainSoft, bookEnq, message);

		List<String> mailAddresses = new ArrayList();
		String subject;
		String msgHtml = "";

		if (trainSoft == "14" || trainSoft == "15" || trainSoft == "22") {
			if (bookEnq == "Booking") {
				mailAddresses.add(booking.getEmail());
				if (trLocation == "1") {
					subject = "Please respond to this email";
				} else {
					subject = "Thank you for your " + bookEnq.toLowerCase();
				}
				// System.out.println("bodyOfMessage ..... 111 "+bodyOfMessage);
				// $mail->Body= $bodyofmessage;
				msgHtml = bodyOfMessage;
				// System.out.println("msgHtml ..... 111 "+msgHtml);
				sendMail(mailAddresses, subject, msgHtml, "info@raiseonlinetraining.co.uk");
			} else {
				mailAddresses.add(booking.getEmail());
				subject = "Thank you for your " + bookEnq.toLowerCase();
				// $mail->Body= $bodyofmessage;
				// System.out.println("bodyOfMessage ..... 222 "+bodyOfMessage);
				msgHtml = bodyOfMessage;
				// System.out.println("msgHtml ..... 222 "+msgHtml);
				sendMail(mailAddresses, subject, msgHtml, "info@raiseonlinetraining.co.uk");
				// mail($varEmail, 'Thank you for your '.strtolower($BookEnq),
				// $bodyofmessage, $headers);
			}
		}

		else {
			mailAddresses.add(booking.getEmail());
			if (booking.getSecondDelegEmail() != "") {
				mailAddresses.add(booking.getSecondDelegEmail());
			}

			if (booking.getThirdDelegEmail() != "") {
				mailAddresses.add(booking.getThirdDelegEmail());
			}

			if (booking.getFourthDelegEmail() != "") {
				mailAddresses.add(booking.getFourthDelegEmail());
			}

			if (bookEnq == "Booking" && trLocation == "1") {
				subject = "Please respond to this email";
			} else {
				subject = "Thank you for your " + bookEnq.toLowerCase();
			}

			// System.out.println("bodyOfMessage ..... 333 "+bodyOfMessage);
			msgHtml = bodyOfMessage;
			// System.out.println("msgHtml ..... 333 "+msgHtml);
			sendMail(mailAddresses, subject, msgHtml, "info@raise-onlinetraining.com");
		}
	}

	private String contentsChange(String htmlFileEmail, Booking booking, String trLocationForMail,
			String trainDateForMail, String trainingName, String trainSoft, String bookEnq, String message)
			throws IOException {
		String secondDelegHtml = "";
		String thirdDelegHtml = "";
		String fourthDelegHtml = "";
		String combinedname = "";
		secondDelegHtml = "<tr><td width=170 style='width:127.5pt;background:white;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";"
				+ "color:#1F497D;background:white'>2nd Delegate</span></p></td><td style='background:white;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";color:#1F497D;background:white'>" + booking.getSecondDeleg()
				+ "</span></p> </td></tr>";
		secondDelegHtml += "<tr><td width=170 style='width:127.5pt;background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";"
				+ "color:#1F497D;background:white'>2nd Delegate E-mail</span></p></td><td style='background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";color:#1F497D;background:white'>" + booking.getSecondDelegEmail()
				+ "</span></p> </td></tr>";
		thirdDelegHtml = "<tr><td width=170 style='width:127.5pt;background:white;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";"
				+ "color:#1F497D;background:white'>3rd Delegate</span></p></td><td style='background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";color:#1F497D;background:white'>" + booking.getThirdDeleg()
				+ "</span></p> </td></tr>";
		thirdDelegHtml += "<tr><td width=170 style='width:127.5pt;background:white;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";"
				+ "color:#1F497D;background:white'>3rd Delegate E-mail</span></p></td><td style='background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";color:#1F497D;background:white'>" + booking.getThirdDelegEmail()
				+ "</span></p> </td></tr>";
		fourthDelegHtml = "<tr><td width=170 style='width:127.5pt;background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";"
				+ "color:#1F497D;background:white'>Fourth Delegate</span></p></td><td style='background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";color:#1F497D;background:white'>" + booking.getFourthDeleg()
				+ "</span></p> </td></tr>";
		fourthDelegHtml += "<tr><td width=170 style='width:127.5pt;background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";"
				+ "color:#1F497D;background:white'>Fourth Delegate E-mail</span></p></td><td style='background:#F9F9F9;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p style='line-height:115%'><span style='font-family:"
				+ "Arial" + "," + "sans-serif" + ";color:#1F497D;background:white'>" + booking.getFourthDelegEmail()
				+ "</span></p> </td></tr>";

		combinedname = booking.getfName();

		if (booking.getSecondDeleg() != "" && booking.getSecondDeleg() != null) {
			combinedname = combinedname + ',' + booking.getSecondDeleg();
		}
		if (booking.getThirdDeleg() != "" && booking.getThirdDeleg() != null) {
			combinedname = combinedname + ',' + booking.getThirdDeleg();
		}
		if (booking.getFourthDeleg() != "" && booking.getFourthDeleg() != null) {
			combinedname = combinedname + ',' + booking.getFourthDeleg();
		}

		// System.out.println("combinedname ... "+combinedname);

		htmlFileEmail = strReplace("_Delegate_Name_", booking.getTitle() + " " + combinedname, htmlFileEmail);

		// System.out.println("htmlFileEmail " + htmlFileEmail);

		htmlFileEmail = strReplace("%booking/enquiry%", bookEnq.toLowerCase(), htmlFileEmail);
		htmlFileEmail = strReplace("%TITLE%", booking.getTitle(), htmlFileEmail);
		htmlFileEmail = strReplace("%FULLNAME%", booking.getfName(), htmlFileEmail);
		htmlFileEmail = strReplace("%SCHOOLNAME%", booking.getScName(), htmlFileEmail);
		htmlFileEmail = strReplace("%MAIL%", booking.getEmail(), htmlFileEmail);
		htmlFileEmail = strReplace("%INVOICE EMAIL%", booking.getInvEmail(), htmlFileEmail);
		htmlFileEmail = strReplace("%TRAININGLOC%", trLocationForMail, htmlFileEmail);
		htmlFileEmail = strReplace("%TELEPHONE%", booking.getTel(), htmlFileEmail);
		htmlFileEmail = strReplace("%DATE%", trainDateForMail, htmlFileEmail);
		htmlFileEmail = strReplace("%TRAINING%", trainingName, htmlFileEmail);
		htmlFileEmail = strReplace("%MESSAGE%", message, htmlFileEmail);

		if (booking.getSecondDeleg() != "" && booking.getSecondDeleg() != null) {
			htmlFileEmail = strReplace("%SECOND_DELEGATE%", secondDelegHtml, htmlFileEmail);
		} else {
			htmlFileEmail = strReplace("%SECOND_DELEGATE%", "", htmlFileEmail);
		}
		if (booking.getThirdDeleg() != "" && booking.getThirdDeleg() != null) {
			htmlFileEmail = strReplace("%THIRD_DELEGATE%", thirdDelegHtml, htmlFileEmail);
		} else {
			htmlFileEmail = strReplace("%THIRD_DELEGATE%", "", htmlFileEmail);
		}
		if (booking.getFourthDeleg() != "" && booking.getFourthDeleg() != null) {
			htmlFileEmail = strReplace("%FOURTH_DELEGATE%", fourthDelegHtml, htmlFileEmail);
		} else {
			htmlFileEmail = strReplace("%FOURTH_DELEGATE%", "", htmlFileEmail);
		}

		/*
		 * final String FILENAME = "E:\\htm files\\2.htm"; java.io.File file =
		 * new java.io.File(FILENAME); // Remaining Encoding.Default
		 * 
		 * BufferedWriter bw = null; FileWriter fw = null;
		 * 
		 * try { // true = append file fw = new
		 * FileWriter(file.getAbsoluteFile(), true); bw = new
		 * BufferedWriter(fw); bw.write(htmlFileEmail);
		 * System.out.println("Done"); } catch (IOException e) {
		 * e.printStackTrace(); } finally { try { if (bw != null) bw.close(); }
		 * catch (IOException ex) { ex.printStackTrace(); } }
		 */

		/*
		 * if(htmlFileEmail.contains("Mr")){ String strHtmlFileEmail[] =
		 * htmlFileEmail.split("Mr");
		 * 
		 * for(int i = 0 ; i<strHtmlFileEmail.length; i++ ) { htmlFileEmail =
		 * htmlFileEmail + strHtmlFileEmail[i]; } }
		 */

		System.out.println("htmlFileEmail before return line ........  " + htmlFileEmail);

		return htmlFileEmail;
	}

	public static String fileGetContents(String fileName) throws IOException {

		// return File.ReadAllText("C:\\htm files\\" + filename + "",
		// Encoding.Default); <--- VB Code
		// Stream<String> stream = Files.lines( Paths.get(filePath),
		// StandardCharsets.UTF_8)

		System.out.println("fileName ... " + fileName);
		String filePAth = "E:\\htm files\\" + fileName;
		java.io.File file = new java.io.File(filePAth + fileName + " "); // Remaining
																			// Encoding.Default

		/*
		 * if(!file.exists()){ file.createNewFile(); }
		 */

		FileReader fileReader = new FileReader(filePAth);
		String fileContents = "";
		int i;
		while ((i = fileReader.read()) != -1) {
			char ch = (char) i;
			fileContents = fileContents + ch;
		}
		System.out.println("fileContents [[[[[[[  ...  " + fileContents);
		// String fileContent = new String(data, "UTF-8");
		// System.out.println(fileContent);

		return fileContents;
	}

	public static String strReplace(String whatToReplace, String whatIsNewWord, String whereToReplace) {
		// String str = whereToReplace.replace(whatToReplace, whatIsNewWord);
		return whereToReplace.replace(whatToReplace, whatIsNewWord);
	}

	private String strCheck1(String str) {
		try {
			if (str.contains("/'")) {
				str = str.replace("/'", "'");
			}
			if (str.contains("\'")) {
				str = str.replace("\'", "'");
			}
		} catch (Exception ex) {
		}
		return str;
	}

	public static boolean sendMail(List<String> mailaddresses, String subject, String msgHtml, String from) {

		System.out.println("inside send mail method");
		// Need ID Password
		final String userName = "vikasharle143@gmail.com";
		final String password = "";

		String smtp = "smtp.gmail.com";
		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtp);
			properties.put("mail.smtp.port", 587);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// creates a new session with an authenticator

			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			}; // change accordingly

			Session session = Session.getInstance(properties, auth);
			// Session session = Session.getInstance(properties);

			// creates a new e-mail message
			MimeMessage msg = new MimeMessage(session);
			System.out.println("this is for the message " + msg);
			msg.setFrom(new InternetAddress(userName)); // change accordingly
														// input from value

			System.out.println("mailaddresses.size()...." + mailaddresses.size());

			// For multiple address bind and send mail
			/*
			 * InternetAddress[] toAddresses = new
			 * InternetAddress[mailaddresses.size()]; for (int i = 0; i <
			 * mailaddresses.size(); i++) { toAddresses[i] = new
			 * InternetAddress(mailaddresses.get(i));
			 * System.out.println("inside loop  "+toAddresses[i]); }
			 */
			InternetAddress[] toAddresses = { new InternetAddress("vikasharle143@gmail.com") };

			// 'To means other.......... 'From' means our'
			msg.setRecipients(Message.RecipientType.TO, toAddresses);

			// Send mail from BCC
			// msg.setRecipients(Message.RecipientType.BCC,from);
			System.out.println("toAddresses ... " + toAddresses);
			msg.setSubject(subject, "text/html; charset=UTF-8");
			System.out.println("subject of the mail " + subject);

			// msg.setText(msgHtml);
			msg.setContent(msgHtml, "text/html; charset=UTF-8");

			System.out.println("set content of the mail " + msgHtml);

			// set message BODY
			// MimeBodyPart mimebodypart = new MimeBodyPart();

			// attach message BODY
			// MimeMultipart mimemultipart = new MimeMultipart();
			// mimemultipart.addBodyPart(mimebodypart);

			// attach content
			// mimebodypart.setDataHandler(new DataHandler(new
			// ByteArrayDataSource(msgHtml, "text/html")));
			// mimemultipart.addBodyPart(mimebodypart);
			// msg.setContent(mimemultipart);

			// Send message
			Transport.send(msg);
			System.out.println("message sent successfully....");

		} catch (Exception e) {
			System.out.println();
			e.getMessage();
			e.printStackTrace();
		}
		return true;
	}

	public void createAndSendInvoice(RecordsDTO recordsDTO) throws IOException, InvalidFormatException, XmlException {

		System.out.println("before logger SOP...");
		logger.debug("my debug");
		logger.info("info");
		logger.fatal("my fatal");

		String paymentType = "";
		boolean savePaymentType = true;

		// Booking booking = new Booking();
		Booking booking = recordsDTO.getBooking();
		Invoice invoice = recordsDTO.getInvoice();

		if (invoice.getId() == null || invoice.getId() == 0 || invoice.getId() == 0.00) {

			System.out.println("Invoice Id..........." + invoice.getId());
			/*
			 * InvoicePaymentOption ipo = new InvoicePaymentOption();
			 * ipo.ShowDialog();
			 */

		} else {

			try {
				paymentType = invoice.getPaymentType();
			} catch (Exception ex) {

			}

			if (paymentType == "") {
				System.out.println("Because there is no payment type, Returning");
				return;
			}
			System.out.println("Going to check Status SelectedItem" + booking.getStatus());

			if (booking.getStatus().toString().equals("Booking")) {
				System.out.println("Because Status is Booking, I am here");
				if (invoice.getTotal() != null) {

					if (invoice.getId() < 1) {

						bookingRepository.getInvoiceNumber(paymentType);
					}

					/* btnSave.PerformClick(); */
					if (savePaymentType) {
						templateChange(recordsDTO);

						/*
						 * Thread.Sleep(2000); //string f =
						 * Directory.GetCurrentDirectory(); Convert(@
						 * "C:\Invoice_temp\Invoice_tc.docx", @"C:\Invoice_temp\" + txtScName.Text + "
						 * _" + txtInNumber.Text + ".pdf",
						 * WdSaveFormat.wdFormatPDF); Thread.Sleep(1000);
						 * File.Delete("C:\\Invoice_temp\\Invoice_tc.docx");
						 */

						try {

							if (paymentType == "L") {
								// sendEMailThroughOUTLOOK_LT();
							} else if (paymentType == "S") {
								/* sendEMailThroughOUTLOOK(); */
							} else if (invoice.getPaymentType().toString() == "L") {
								/* sendEMailThroughOUTLOOK_LT(); */

							} else if (invoice.getPaymentType().toString() == "S") {
								/* sendEMailThroughOUTLOOK(); */

							}

						} catch (Exception e) {
							/* sendEMailThroughOUTLOOK(); */
						}

					} else {
						System.out.println("Invoice has not been created due to a previous error!");
					}
					/* getAllFieldsData(true); */

				} else {

					System.out.println("Invoice Total does not contain any value, an Invoice can not be created!");
				}

			} else {

				String enqCan = booking.getStatus().toString();
				String text = "To create an Invoice, you should change the status of the record from " + enqCan
						+ " to Booking";
				String capt = "Record Status is incorrect";

				System.out.println("Values are:" + enqCan + text + capt);
			}

		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public InvoiceDTO calculateTotal(RecordsDTO recordsDTO) {
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		try {
			Double softCost = 0.0;
			Double softStp = 0.0;
			Double travelCost;
			Double trainingCost;
			Double discount1;
			Double cancsurCharge;
			Double vatAmmount;
			Double additionalDelegates;
			Boolean txtFirstFeevisible = true;
			Boolean txtSecondFeevisible = true;
			Boolean txtThirdFeevisible = true;
			Boolean txtFourthFeevisible = true;
			Boolean txtTravelCostvisible = true;
			Boolean txtTrainingCostvisible = true;
			Boolean txtDiscountvisible = true;
			Boolean txtCanSurvisible = true;
			Boolean txtVatvisible = true;
			Boolean txtAdditionalDelegatesvisible = true;
			Boolean txtSoftCostvisible = true;
			Boolean txtSoftSetupvisible = true;
			Booking booking = recordsDTO.getBooking();
			double firstTimeVatValue = 0.0;
			Invoice invoice = recordsDTO.getInvoice();
			if (booking.getTraining().toString().contains("Software")
					|| booking.getTraining().toString().contains("EYFSonline")) {

				softCost = invoice.getSoftCost() != null ? invoice.getSoftCost() : 0;
				softStp = invoice.getSoftSetup() != null ? invoice.getSoftSetup() : 0;

			} else {

				softCost = 0.0;
				softStp = 0.0;
			}
			if (softCost == 0.0) {
				if (booking.getTraining() != null) {
					if (booking.getTraining().toString().contains("Software")
							|| booking.getTraining().toString().contains("EYFSonline")) {
						softCost = 975.0;
						softStp = 0.0;
						softCost = invoice.getSoftCost() != null ? invoice.getSoftCost() : 0.0;
						softStp = invoice.getSoftSetup() != null ? invoice.getSoftSetup() : 0.0;
					}
				}
			}
			Double firstFee = invoice.getFirstDelegFee() != null ? invoice.getFirstDelegFee() : 0.0;
			Double secondFee = invoice.getSecondDelegFee() != null ? invoice.getSecondDelegFee() : 0.0;
			Double thirdFee = invoice.getThirdDelegFee() != null ? invoice.getThirdDelegFee() : 0.0;
			Double fourthFee = invoice.getFourthDelegFee() != null ? invoice.getFourthDelegFee() : 0.0;
			if (softCost > 0) {
				firstFee = 0.0;
				secondFee = 0.0;
				thirdFee = 0.0;
				fourthFee = 0.0;
			}

			if (!txtFirstFeevisible) {
				firstFee = 0.0;
			}
			if (!txtSecondFeevisible) {
				secondFee = 0.0;
			}
			if (!txtThirdFeevisible) {
				thirdFee = 0.0;
			}
			if (!txtFourthFeevisible) {
				fourthFee = 0.0;
			}
			if (!txtTravelCostvisible) {
				travelCost = 0.0;
			}
			if (!txtTrainingCostvisible) {
				trainingCost = 0.0;
			}
			if (!txtSoftCostvisible) {
				softCost = 0.0;
			}
			if (!txtSoftSetupvisible) {
				softStp = 0.0;
			}

			if (!txtDiscountvisible) {
				discount1 = 0.0;
			}
			if (!txtCanSurvisible) {
				cancsurCharge = 0.0;
			}
			if (!txtVatvisible) {
				vatAmmount = 0.0;
			}
			if (!txtAdditionalDelegatesvisible) {
				additionalDelegates = 0.0;
			}

			firstFee = invoice.getFirstDelegFee() != null ? invoice.getFirstDelegFee() : 0.0;
			secondFee = invoice.getSecondDelegFee() != null ? invoice.getSecondDelegFee() : 0.0;
			thirdFee = invoice.getThirdDelegFee() != null ? invoice.getThirdDelegFee() : 0.0;
			fourthFee = invoice.getFourthDelegFee() != null ? invoice.getFourthDelegFee() : 0.0;
			travelCost = invoice.getTravelCost() != null ? invoice.getTravelCost() : 0.0;
			trainingCost = invoice.getTrainCost() != null ? invoice.getTrainCost() : 0.0;

			discount1 = invoice.getDiscount() != null ? invoice.getDiscount() : 0.0;
			cancsurCharge = invoice.getCancsurCharge() != null ? invoice.getCancsurCharge() : 0.0;

			vatAmmount = invoice.getVatAmount() != null ? invoice.getVatAmount() : 0.0;
			additionalDelegates = invoice.getAdditionalDelegates() != null ? invoice.getAdditionalDelegates() : 0.0;
			try {
				String startDate = "2013-09-24 00:00:00";
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDate formatedStartDate = LocalDate.parse(startDate, formatters);
				LocalDate submitDateTime = LocalDate.parse(booking.getSubmitDateTime(), formatters);

				Double vatcalculation = (((firstFee + secondFee + thirdFee + fourthFee + travelCost + trainingCost
						+ softCost + softStp + additionalDelegates + cancsurCharge) - Math.abs(discount1)) * 20) / 100;
				boolean isvatboxchanging = false;
				if (isvatboxchanging) {
					try {
						vatcalculation = invoice.getVatAmount() != null ? invoice.getVatAmount() : 0.0;
					} catch (Exception ex) {
					}
				}
				if (submitDateTime.isBefore(formatedStartDate)) {
					vatcalculation = 0.0;
				}
				boolean isfirsttime = false;
				if (!isfirsttime) {
					vatAmmount = vatcalculation;
				} else {
					if (vatAmmount == null || Double.toString(vatAmmount).equals("") || vatAmmount == 0) {
						vatAmmount = vatcalculation;
					} else {
						vatAmmount = firstTimeVatValue;
					}
				}
				 
				boolean chkIncludeVat =  invoice.getChkvat();
				System.out.println(chkIncludeVat);
				if (chkIncludeVat==false) {
					vatAmmount = 0.0;

				}
				invoiceDTO.setVatAmmount(vatAmmount);
			} catch (Exception e) {
				System.out.println(e);
			}
			double sum = 0;
			if (discount1 > 0) {
				sum = firstFee + secondFee + thirdFee + fourthFee + travelCost + trainingCost + softCost + softStp
						+ vatAmmount + additionalDelegates + cancsurCharge - discount1;
			} else {
				sum = firstFee + secondFee + thirdFee + fourthFee + travelCost + trainingCost + softCost + softStp
						+ vatAmmount + additionalDelegates + cancsurCharge + discount1;
			}
			Double total = sum;
			invoiceDTO.setTotal(total);
		} catch (Exception e) {
			System.out.println(e);
		}
		return invoiceDTO;
	}

}