package com.crm.serviceImpl;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class temp {
public static void main(String[] args) {
	
	final Logger logger = Logger.getLogger(ClassName.class);
	
	logger.debug("hiiiiiiiiii");
	
	DateTime dateTime = new DateTime();
    System.out.println(dateTime);
    System.out.println(dateTime.minusDays(1));
    
    
    LocalDate submitDateTime = LocalDate.now();
	System.out.println("submitDateTime"+submitDateTime);
	
	DateTime rep = new DateTime();
	System.out.println("rep ... "+rep);
	
	String str = null;
	String invoiceDetail = (str != null) ? str : "abc";
	
	System.out.println("invoice details ....  "+invoiceDetail);
	
	//Local path	
	//String filePath = "K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t_lt.docx";
	
	//Server Path
	//String filePath ="C:\\Invoicing\\Invoice_t.docx";
	
	
	//Local path
	//	String fileWritePate=  "K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t_lt "+invoice.getId()+"_.docx";

	//Server Path
	//	String fileWritePate =  "C:\\Invoicing\\Invoice_t_lt "+invoice.getId()+"_.docx";
		
	//System.out.println("doc...: " + doc);
			//doc.write(new FileOutputStream("C:\\Invoicing\\Invoice_t_lt "+invoice.getId()+"_.docx"));
		//	doc.write(new FileOutputStream("K:\\pooja\\New folder\\RASEonline\\RASEonline\\Invoice_temp\\Invoice_t_lt "+invoice.getId()+"_.docx"));


}
}
