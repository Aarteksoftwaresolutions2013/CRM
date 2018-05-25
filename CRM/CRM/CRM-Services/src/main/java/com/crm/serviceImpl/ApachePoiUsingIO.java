package com.crm.serviceImpl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class ApachePoiUsingIO {

	public static void main(String[] args) {
		/*
		 * try { XWPFDocument doc = new XWPFDocument( OPCPackage.
		 * open("K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_tc.docx"
		 * )); // System.out.println(doc.getParagraphs().get(10).getText()); for
		 * (XWPFParagraph p : doc.getParagraphs()) { List<XWPFRun> runs =
		 * p.getRuns(); // System.out.println(runs + "runs"); if (runs != null)
		 * { for (XWPFRun r : runs) { String text = r.getText(0);
		 * 
		 * 
		 * JTextField textField = new JTextField();
		 * 
		 * textField.setText(r.get);
		 * 
		 * textField.setColumns(20);
		 * 
		 * String pictureText =textField.getText();
		 * 
		 * 
		 * System.out.println("String picture texr ..."+textField.toString());
		 * //String pictureText = r.getPictureText();
		 * 
		 * //r.setText(r.getPictureText().contains("4556") ? r.("4556",
		 * "45567")); //System.out.println(); //
		 * System.out.println(r.getPictureText()+ "picr");
		 * 
		 * if (text != null && text.contains("Marsh")) { //
		 * System.out.println(text + "text"); text = text.replace("Marsh",
		 * "MarshNew"); r.setText(text, 0); }
		 * 
		 * String[] arr = pictureText.split("\n");
		 * 
		 * for (String picText : arr) { if (picText != null &&
		 * picText.contains("4556")) { System.out.println(picText +
		 * "pictureText");
		 * 
		 * picText = picText.replace("_INDATE_", "47"); picText =
		 * picText.replace("4556", "45567");
		 * 
		 * 
		 * 
		 * r.setText(picText); }} } } }
		 * 
		 * doc.write( new
		 * FileOutputStream("K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_tc_2.docx"
		 * ));
		 * 
		 * } catch (Exception exep) { exep.printStackTrace(); } }
		 */
		try {
			XWPFDocument doc = new XWPFDocument(
					OPCPackage.open("K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t.docx"));
			// System.out.println(doc.getParagraphs().get(10).getText());
			StringBuffer tableHTML = new StringBuffer();
			for (XWPFParagraph p : doc.getParagraphs()) {

				CTBody ctbody = doc.getDocument().getBody();

				XmlCursor xmlcursor = ctbody.newCursor();

				QName qnameTbl = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tbl", "w");
				QName qnameFallback = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006",
						"Fallback", "mc");

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
					
					//System.out.println("cTTbl ... " +cTTbl.getTrList());
					for (CTRow cTRow : cTTbl.getTrList()) {

						for (CTTc cTTc : cTRow.getTcList()) {

							for (CTP cTP : cTTc.getPList()) {
								for (CTR cTR : cTP.getRList()) {
									for (CTText cTText : cTR.getTList()) {
										tableHTML.append(cTText.getStringValue() + "\n");
									}
								}
							}
						}
					}
				}
			}
			System.out.println(tableHTML.toString() + "table");
			doc.write(new FileOutputStream(
					"K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t_2.docx"));

		} catch (Exception exep) {
			exep.printStackTrace();

		}
	}
}
