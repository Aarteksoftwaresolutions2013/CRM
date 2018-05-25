package com.crm.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.repository.ICourseMasterRepository;

@Repository
public class CourseMasterRepositoryImpl implements ICourseMasterRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findCourseMasterByYearAndMonth(String year, String month) {
		Query query = em
				.createNativeQuery("select coursesmaster.id coursesmaster_id, coursesmaster.title coursesmaster_title, "
						+ "coursesmaster.full_name coursesmaster_full_name,coursesmaster.message coursesmaster_message,"
						+ "coursesmaster.schoolname coursesmaster_schoolname,coursesmaster.tel_num coursesmaster_tel_num,"
						+ "coursesmaster.c_name coursesmaster_c_name,coursesmaster.t_date coursesmaster_t_date,"
						+ "coursesmaster.t_loc_date coursesmaster_t_loc_date,"
						+ "coursesmaster.numberofdelegates coursesmaster_numberofdelegates,"
						+ "coursesmaster.enquiry coursesmaster_enquiry,coursesmaster.email coursesmaster_email,"
						+ "coursesmaster.submitdatetime coursesmaster_submitdatetime,"
						+ "coursesmaster.callbacktime coursesmaster_callbacktime,"
						+ "coursesmaster_invoice.id coursesmaster_invoice_id,"
						+ "coursesmaster_invoice.firstdelegfee coursesmaster_invoice_firstdelegfee,coursesmaster_invoice.seconddelegfee coursesmaster_invoice_seconddelegfee,coursesmaster_invoice.thirdDelegFee coursesmaster_invoice_thirdDelegFee,coursesmaster_invoice.costoftraining coursesmaster_invoice_costoftraining,"
						+ "coursesmaster_invoice.invoiceaddress coursesmaster_invoice_invoiceaddress,coursesmaster_invoice.website coursesmaster_invoice_website,"
						+ "coursesmaster_invoice.innotes coursesmaster_invoice_innotes,"
						+ "coursesmaster_invoice.vatamount coursesmaster_invoice_vatamount,coursesmaster_invoice.total coursesmaster_invoice_total,coursesmaster_invoice.fourthDelegFee coursesmaster_invoice_fourthDelegFee,"
						+ "coursesmaster_invoice.venue coursesmaster_invoice_venue,coursesmaster_invoice.date coursesmaster_invoice_date,"
						+ "coursesmaster_invoice.pono coursesmaster_invoice_pono,coursesmaster_invoice.invsentdate coursesmaster_invoice_invsentdate,coursesmaster_invoice.payreceivedate coursesmaster_invoice_payreceivedate,"
						+ "coursesmaster_invoice.payreceiveby coursesmaster_invoice_payreceiveby,coursesmaster_invoice.additionalnotes coursesmaster_invoice_additionalnotes,coursesmaster_invoice.invsenttoschfinance coursesmaster_invoice_invsenttoschfinance,"
						+ "coursesmaster_invoice.bascrefno coursesmaster_invoice_bascrefno,coursesmaster_invoice.reconswithbank coursesmaster_invoice_reconswithbank,coursesmaster_invoice.paidbybacs coursesmaster_invoice_paidbybacs,coursesmaster_invoice.ltbacs coursesmaster_invoice_ltbacs,coursesmaster_invoice.checkno coursesmaster_invoice_checkno,"
						+ "coursesmaster_invoice.accno coursesmaster_invoice_accno,coursesmaster_invoice.sortcode coursesmaster_invoice_sortcode,coursesmaster_invoice.paidbycheque coursesmaster_invoice_paidbycheque,coursesmaster_invoice.ltcheque coursesmaster_invoice_ltcheque,"
						+ "coursesmaster_invoice.chequedate coursesmaster_invoice_chequedate,coursesmaster_invoice.checquedepdate coursesmaster_invoice_checquedepdate,coursesmaster_invoice.bankreceivedate coursesmaster_invoice_bankreceivedate,"
						+ "coursesmaster_invoice.raisedinvinpaypal coursesmaster_invoice_raisedinvinpaypal,coursesmaster_invoice.dateoftransftobank coursesmaster_invoice_dateoftransftobank,coursesmaster_invoice.flcost coursesmaster_invoice_flcost,"
						+ "coursesmaster_invoice.traingocost coursesmaster_invoice_traingocost,coursesmaster_invoice.cancsurcharge coursesmaster_invoice_cancsurcharge,coursesmaster_invoice.additionaldelegates coursesmaster_invoice_additionaldelegates,"
						+ "coursesmaster_invoice.chkvat coursesmaster_invoice_chkvat,coursesmaster_invoice.lt coursesmaster_invoice_lt,coursesmaster_invoice.paymenttype coursesmaster_invoice_paymenttype,coursesmaster_invoice.discount coursesmaster_invoice_discount,"
						+ "coursesmaster_confirmation.id coursesmaster_confirmation_id,coursesmaster_confirmation.delegatewillbringlaptop coursesmaster_confirmation_delegatewillbringlaptop,coursesmaster_confirmation.reminddelegatebeforetraining coursesmaster_confirmation_reminddelegatebeforetraining,"
						+ "coursesmaster_confirmation.confirmedwithtrainer coursesmaster_confirmation_confirmedwithtrainer,coursesmaster_confirmation.delegateconfirmedattendance coursesmaster_confirmation_delegateconfirmedattendance,coursesmaster_confirmation.delegateconfirmedattendanceby coursesmaster_confirmation_delegateconfirmedattendanceby,"
						+ "coursesmaster_confirmation.certificateposted coursesmaster_confirmation_certificateposted,coursesmaster_confirmation.notes coursesmaster_confirmation_notes  from coursesmaster Left join coursesmaster_invoice "
						+ "on coursesmaster.id=coursesmaster_invoice.coursesmasterid "
						+ "LEFT JOIN coursesmaster_confirmation ON coursesmaster.id=coursesmaster_confirmation.coursesmasterid "
						+ "where YEAR(coursesmaster.t_date)= ?1 and MONTH(coursesmaster.t_date)= ?2 AND coursesmaster.enquiry<> 'Cancelled' ");
		query.setParameter(1, year);
		query.setParameter(2, month);
		List<Object[]> coursemasterlists = query.getResultList();
		return coursemasterlists;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findCourseByYearAndMonthFromCourseTrlocationAndCourseCourses(String year, String month) {
		Query query = em.createNativeQuery("SELECT c_type course,t_date_start,t_date_end ,shortname "
				+ "FROM coursesmaster_traininglocation "
				+ "LEFT JOIN coursesMaster_courses ON coursesmaster_traininglocation.c_type=coursesmaster_courses.course");
		List<Object[]> coursemasterlists = query.getResultList();
		return coursemasterlists;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Object[]> findCourseByCourseMaster() {
		return (List<Object[]>) em
				.createNativeQuery(
						"SELECT course FROM coursesmaster_courses WHERE course IN(SELECT c_type FROM  coursesmaster_traininglocation)")
				.getResultList();

	}
}
