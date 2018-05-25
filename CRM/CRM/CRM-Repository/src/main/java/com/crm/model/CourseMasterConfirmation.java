package com.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coursemasterconfirmation")
public class CourseMasterConfirmation {

	@Id
	@Column(name = "coursesmasterid")
	private Integer coursesMasterId;

	@Column(name = "delegatewillbringlaptop")
	private Integer delegateWillBringLaptop;

	@Column(name = "reminddelegatebeforetraining")
	private Integer remindDelegateBeforetTraining;

	@Column(name = "confirmedwithtrainer")
	private String confirmedWithTrainer;

	@Column(name = "delegateconfirmedattendance")
	private String delegateConfirmedAttendance;

	@Column(name = "delegateconfirmedattendanceby")
	private String delegateConfirmedAttendanceBy;

	@Column(name = "certificateposted")
	private Integer certificatePosted;

	@Column(name = "notes")
	private String notes;

	@Column(name = "id")
	private Integer id;

	/**
	 * @return the coursesMasterId
	 */
	public Integer getCoursesMasterId() {
		return coursesMasterId;
	}

	/**
	 * @param coursesMasterId
	 *            the coursesMasterId to set
	 */
	public void setCoursesMasterId(Integer coursesMasterId) {
		this.coursesMasterId = coursesMasterId;
	}

	/**
	 * @return the delegateWillBringLaptop
	 */
	public Integer getDelegateWillBringLaptop() {
		return delegateWillBringLaptop;
	}

	/**
	 * @param delegateWillBringLaptop
	 *            the delegateWillBringLaptop to set
	 */
	public void setDelegateWillBringLaptop(Integer delegateWillBringLaptop) {
		this.delegateWillBringLaptop = delegateWillBringLaptop;
	}

	/**
	 * @return the remindDelegateBeforetTraining
	 */
	public Integer getRemindDelegateBeforetTraining() {
		return remindDelegateBeforetTraining;
	}

	/**
	 * @param remindDelegateBeforetTraining
	 *            the remindDelegateBeforetTraining to set
	 */
	public void setRemindDelegateBeforetTraining(Integer remindDelegateBeforetTraining) {
		this.remindDelegateBeforetTraining = remindDelegateBeforetTraining;
	}

	/**
	 * @return the confirmedWithTrainer
	 */
	public String getConfirmedWithTrainer() {
		return confirmedWithTrainer;
	}

	/**
	 * @param confirmedWithTrainer
	 *            the confirmedWithTrainer to set
	 */
	public void setConfirmedWithTrainer(String confirmedWithTrainer) {
		this.confirmedWithTrainer = confirmedWithTrainer;
	}

	/**
	 * @return the delegateConfirmedAttendance
	 */
	public String getDelegateConfirmedAttendance() {
		return delegateConfirmedAttendance;
	}

	/**
	 * @param delegateConfirmedAttendance
	 *            the delegateConfirmedAttendance to set
	 */
	public void setDelegateConfirmedAttendance(String delegateConfirmedAttendance) {
		this.delegateConfirmedAttendance = delegateConfirmedAttendance;
	}

	/**
	 * @return the delegateConfirmedAttendanceBy
	 */
	public String getDelegateConfirmedAttendanceBy() {
		return delegateConfirmedAttendanceBy;
	}

	/**
	 * @param delegateConfirmedAttendanceBy
	 *            the delegateConfirmedAttendanceBy to set
	 */
	public void setDelegateConfirmedAttendanceBy(String delegateConfirmedAttendanceBy) {
		this.delegateConfirmedAttendanceBy = delegateConfirmedAttendanceBy;
	}

	/**
	 * @return the certificatePosted
	 */
	public Integer getCertificatePosted() {
		return certificatePosted;
	}

	/**
	 * @param certificatePosted
	 *            the certificatePosted to set
	 */
	public void setCertificatePosted(Integer certificatePosted) {
		this.certificatePosted = certificatePosted;
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

}
