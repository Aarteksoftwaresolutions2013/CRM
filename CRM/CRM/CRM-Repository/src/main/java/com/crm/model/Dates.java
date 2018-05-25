package com.crm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dates")

public class Dates {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "location")
	private String location;

	@Column(name = "addtocalender")
	private String addtocalender;

	@Column(name = "t_date")
	private Date t_date;

	@Transient
	String t_datedummy;

	/**
	 * @return the t_datedummy
	 */
	public String getT_datedummy() {
		return t_datedummy;
	}

	/**
	 * @param t_datedummy
	 *            the t_datedummy to set
	 */
	public void setT_datedummy(String t_datedummy) {
		this.t_datedummy = t_datedummy;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the addtocalender
	 */
	public String getAddtocalender() {
		return addtocalender;
	}

	/**
	 * @param addtocalender
	 *            the addtocalender to set
	 */
	public void setAddtocalender(String addtocalender) {
		this.addtocalender = addtocalender;
	}

	/**
	 * @return the t_date
	 */
	public Date getT_date() {
		return t_date;
	}

	/**
	 * @param t_date
	 *            the t_date to set
	 */
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}

}
