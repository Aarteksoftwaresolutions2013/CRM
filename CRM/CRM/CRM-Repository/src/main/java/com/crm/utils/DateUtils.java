package com.crm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

public class DateUtils {

	private static final String DD_MM_YYYY = "dd-MM-yyyy";
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	private static final String MMM_YYYY = "MMM yyyy";
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
	public static DateTime getDateTimeFromTimestamp(Long value, String timeZoneOffSet) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneOffSet);
		long offset = timeZone.getOffset(value);
		if (offset < 0) {
			value -= offset;
		} else {
			value += offset;
		}
		return new DateTime(value);
	}

	public static String convertTimeStampToString(Object object) {
		return object != null ? (object).toString().split("[.]")[0].toString() : null;
	}

	public static LocalDate convertMonthToMonthStartDate(String dateinMMYYYY) {

		SimpleDateFormat formatterMMYY = new SimpleDateFormat(MMM_YYYY);
		Date date = null;
		try {
			date = formatterMMYY.parse(dateinMMYYYY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat(YYYY_MM_DD);

		LocalDate startDateOfMonth = LocalDate.parse(formatterYYYYMMDD.format(date),
				DateTimeFormatter.ofPattern(YYYY_MM_DD));

		return startDateOfMonth;
	}

	public static LocalDate convertMonthToMonthEndDate(String dateinMMYYYY) {

		LocalDate convertedDate = convertMonthToMonthStartDate(dateinMMYYYY);

		LocalDate endDateOfMonth = convertedDate
				.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));

		return endDateOfMonth;
	}

	public static String convertYYYY_MM_DDToDD_MM_YYYYDateFormat(String dateinddMMYYYY) {

		DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		Date date = null;
		try {
			date = (Date) formatter.parse(dateinddMMYYYY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat newFormat = new SimpleDateFormat(DD_MM_YYYY);
		String finalString = newFormat.format(date);

		return finalString;
	}

	public static java.sql.Date convertDD_MM_YYYYToYYYY_MM_DDDateFormat(String dateinddMMYYYY) {
		if (StringUtils.isEmpty(dateinddMMYYYY) && dateinddMMYYYY != "") {
			String[] str = dateinddMMYYYY.split("-");

			int year = Integer.parseInt(str[2]);
			int month = Integer.parseInt(str[1]) - 1; // January
			int date = Integer.parseInt(str[0]);

			Calendar cal = Calendar.getInstance();
			cal.clear();

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DATE, date);

			java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
			System.out.println(sqlDate);
			return sqlDate;
		} else {
			Calendar cal = Calendar.getInstance();
			java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
			return sqlDate;
		}
	}

	public static String convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(String dateinddMMYYYY) {

		DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);
		Date date = null;
		try {
			date = (Date) formatter.parse(dateinddMMYYYY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat newFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String finalString = newFormat.format(date);

		return finalString;
	}

	public static String convertYYYY_MM_DDDToDD_MM_YYYYDateFormatWithHHMMSS(String dateinddMMYYYY) {

		DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		Date date = null;
		try {
			date = formatter.parse(dateinddMMYYYY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat newFormat = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);
		String finalString = newFormat.format(date);

		return finalString;
	}

	public static String convertStringToDateWithDateFormatYYYY_MM_DD(String date) {
		DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		Date date1 = null;
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat newFormat = new SimpleDateFormat(YYYY_MM_DD);
		String finalString = newFormat.format(date1);

		return finalString;
	}

	public static String addDaysIndate(Date date, int index) {
		// Get current date
		Date currentDate = new Date(date.getTime());
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		// plus one
		localDateTime = localDateTime.plusDays(index);
		// localDateTime =
		// localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);
		// convert LocalDateTime to date
		Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return  dateFormat.format(currentDatePlusOneDay);

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * System.out.println(" valo = "+
	 * DateUtils.convertStringToDateWithDateFormatYYYY_MM_DD
	 * ("2018-01-01 10:20:30"));
	 * 
	 * }
	 */

}