package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Locations {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "location")
	private String location;

	@Column(name = "shortname")
	private String shortName;

	@Column(name = "addtocalender")
	private String addToCalender;

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
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the addToCalendar
	 */
	public String getAddToCalendar() {
		return addToCalender;
	}

	/**
	 * @param addToCalendar
	 *            the addToCalendar to set
	 */
	public void setAddToCalendar(String addToCalendar) {
		this.addToCalender = addToCalendar;
	}

}