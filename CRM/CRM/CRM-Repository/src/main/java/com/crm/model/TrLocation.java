package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trlocation")
public class TrLocation {

	@Id
	@Column(name = "TrLId")
	private Integer trLId;

	@Column(name = "TrLocation")
	private String trLocation;

	@Column(name = "displayorder")
	private String displayOrder;

	/**
	 * @return the trLId
	 */
	public Integer getTrLId() {
		return trLId;
	}

	/**
	 * @param trLId
	 *            the trLId to set
	 */
	public void setTrLId(Integer trLId) {
		this.trLId = trLId;
	}

	/**
	 * @return the trLocation
	 */
	public String getTrLocation() {
		return trLocation;
	}

	/**
	 * @param trLocation
	 *            the trLocation to set
	 */
	public void setTrLocation(String trLocation) {
		this.trLocation = trLocation;
	}

	/**
	 * @return the displayOrder
	 */
	public String getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder
	 *            the displayOrder to set
	 */
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
}