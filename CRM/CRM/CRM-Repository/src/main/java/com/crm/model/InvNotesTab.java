package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invnotestab")
public class InvNotesTab {

	@Id
	@Column(name = "BId")
	private Integer bId;

	@Column(name = "InNotes")
	private String inNotes;

	@Column(name = "expectations")
	private String expectations;

	/**
	 * @return the bId
	 */
	public Integer getbId() {
		return bId;
	}

	/**
	 * @param bId
	 *            the bId to set
	 */
	public void setbId(Integer bId) {
		this.bId = bId;
	}

	/**
	 * @return the inNotes
	 */
	public String getInNotes() {
		return inNotes;
	}

	/**
	 * @param inNotes
	 *            the inNotes to set
	 */
	public void setInNotes(String inNotes) {
		this.inNotes = inNotes;
	}

	/**
	 * @return the expectations
	 */
	public String getExpectations() {
		return expectations;
	}

	/**
	 * @param expectations
	 *            the expectations to set
	 */
	public void setExpectations(String expectations) {
		this.expectations = expectations;
	}

}