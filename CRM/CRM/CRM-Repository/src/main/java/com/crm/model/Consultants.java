package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consultants")
public class Consultants {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "Consultant")
	private String consultants;

	public Consultants() {

	}

	public Consultants(Integer id, String consultant) {
		super();
		this.consultants = consultant;
		this.id = id;
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
	 * @return the consultants
	 */
	public String getConsultants() {
		return consultants;
	}

	/**
	 * @param consultants
	 *            the consultants to set
	 */
	public void setConsultants(String consultants) {
		this.consultants = consultants;
	}

}