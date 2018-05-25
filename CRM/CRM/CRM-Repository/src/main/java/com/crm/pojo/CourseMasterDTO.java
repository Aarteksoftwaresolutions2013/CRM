package com.crm.pojo;

import java.util.Date;

public class CourseMasterDTO {

	private Date t_date_start;
	private Date t_date_end;
	private String coursesmaster_t_date;
	private String coursesmaster_c_name;
	private String coursesmaster_enquiry;
	private String coursesmaster_numberofdelegates;
	private String course;
	private String shortname;

	public CourseMasterDTO() {
		// TODO Auto-generated constructor stub
	}

	public CourseMasterDTO(Object[] object) {
		super();
		this.coursesmaster_t_date = (object[7].toString());
		this.coursesmaster_c_name = (String) object[6];
		this.coursesmaster_enquiry = (String) object[10];
		this.coursesmaster_numberofdelegates = (String) object[9];
	}

	public CourseMasterDTO(Object[] object, boolean isStatus) {
		this.course = (String) object[0];
		this.t_date_start = (Date) object[1];
		this.t_date_end = (Date) object[2];
		this.shortname = (String) object[3];

	}

	/**
	 * @return the shortname
	 */
	public String getShortname() {
		return shortname;
	}

	/**
	 * @param shortname
	 *            the shortname to set
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	/**
	 * @return the t_date_start
	 */
	public Date getT_date_start() {
		return t_date_start;
	}

	/**
	 * @param t_date_start
	 *            the t_date_start to set
	 */
	public void setT_date_start(Date t_date_start) {
		this.t_date_start = t_date_start;
	}

	/**
	 * @return the t_date_end
	 */
	public Date getT_date_end() {
		return t_date_end;
	}

	/**
	 * @param t_date_end
	 *            the t_date_end to set
	 */
	public void setT_date_end(Date t_date_end) {
		this.t_date_end = t_date_end;
	}

	/**
	 * @return the coursesmaster_t_date
	 */
	public String getCoursesmaster_t_date() {
		return coursesmaster_t_date;
	}

	/**
	 * @param coursesmaster_t_date
	 *            the coursesmaster_t_date to set
	 */
	public void setCoursesmaster_t_date(String coursesmaster_t_date) {
		this.coursesmaster_t_date = coursesmaster_t_date;
	}

	/**
	 * @return the coursesmaster_c_name
	 */
	public String getCoursesmaster_c_name() {
		return coursesmaster_c_name;
	}

	/**
	 * @param coursesmaster_c_name
	 *            the coursesmaster_c_name to set
	 */
	public void setCoursesmaster_c_name(String coursesmaster_c_name) {
		this.coursesmaster_c_name = coursesmaster_c_name;
	}

	/**
	 * @return the coursesmaster_enquiry
	 */
	public String getCoursesmaster_enquiry() {
		return coursesmaster_enquiry;
	}

	/**
	 * @param coursesmaster_enquiry
	 *            the coursesmaster_enquiry to set
	 */
	public void setCoursesmaster_enquiry(String coursesmaster_enquiry) {
		this.coursesmaster_enquiry = coursesmaster_enquiry;
	}

	/**
	 * @return the coursesmaster_numberofdelegates
	 */
	public String getCoursesmaster_numberofdelegates() {
		return coursesmaster_numberofdelegates;
	}

	/**
	 * @param coursesmaster_numberofdelegates
	 *            the coursesmaster_numberofdelegates to set
	 */
	public void setCoursesmaster_numberofdelegates(String coursesmaster_numberofdelegates) {
		this.coursesmaster_numberofdelegates = coursesmaster_numberofdelegates;
	}

	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}

}
