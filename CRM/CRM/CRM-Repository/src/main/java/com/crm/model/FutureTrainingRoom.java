package com.crm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "futuretariningroom")
public class FutureTrainingRoom {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "meetingroomid")
	private Integer meetingRoomId;

	@Column(name = "interested")
	private String interested;

	@Column(name = "callbacktime")
	private Date callBackTime;

	@Column(name = "notes")
	private String notes;

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
	 * @return the meetingRoomId
	 */
	public Integer getMeetingRoomId() {
		return meetingRoomId;
	}

	/**
	 * @param meetingRoomId
	 *            the meetingRoomId to set
	 */
	public void setMeetingRoomId(Integer meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	/**
	 * @return the interested
	 */
	public String getInterested() {
		return interested;
	}

	/**
	 * @param interested
	 *            the interested to set
	 */
	public void setInterested(String interested) {
		this.interested = interested;
	}

	/**
	 * @return the callBackTime
	 */
	public Date getCallBackTime() {
		return callBackTime;
	}

	/**
	 * @param callBackTime
	 *            the callBackTime to set
	 */
	public void setCallBackTime(Date callBackTime) {
		this.callBackTime = callBackTime;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
