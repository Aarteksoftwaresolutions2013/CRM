package com.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "calendercolor")
public class CalendarColor {

	@Id
	@Column(name = "Id")
	private Integer id;

	@Column(name = "date")
	private Date date;

	@Column(name = "Color")
	private String color;

	@Transient
	private String dateDummy;

	public String getDateDummy() {
		return dateDummy;
	}

	public void setDateDummy(String dateDummy) {
		this.dateDummy = dateDummy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}