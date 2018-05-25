package com.crm.serviceImpl;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Dates;
import com.crm.repository.IDateRepository;
import com.crm.service.IDateService;
import com.crm.utils.DateUtils;

@Service
public class DateServiceImpl implements IDateService {

	@Autowired
	IDateRepository daterepository;

	/**
	 * Method for Find All Dates.
	 * 
	 * @return List<Dates>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Dates> findAllDates() {
		return daterepository.findAllDates();
	}

	/**
	 * Method for save Dates.
	 * 
	 * @param dates
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDates(Dates dates) {
		dates.setT_date(DateUtils.convertDD_MM_YYYYToYYYY_MM_DDDateFormat(dates.getT_datedummy()));
		daterepository.saveDates(dates);

	}

	/**
	 * Method for find Dates Max Id
	 * 
	 * @param dates
	 * @return Dates
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Dates findDatesMaxId(Dates dates) {
		List<Integer> dateMaxId = daterepository.findDateMaxId();
		dates.setId(dateMaxId.get(0) + 1);
		return dates;
	}

	/**
	 * Method for delete Dates
	 * 
	 * @param datesId
	 * @return void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDates(Integer[] datesId) {
		daterepository.deleteDates(Arrays.asList(datesId));
	}

	/**
	 * Method for update Dates
	 * 
	 * @param dates
	 * @return Dates
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Dates updateDates(Dates dates) {
		dates.setT_date(DateUtils.convertDD_MM_YYYYToYYYY_MM_DDDateFormat(dates.getT_datedummy()));
		return daterepository.updateDates(dates);
	}

	/**
	 * Method for find Dates by date id
	 * 
	 * @param datesId
	 * @return List<Dates>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Dates> findDatesById(Integer datesId) {
		return daterepository.findDatesById(datesId);
	}

}
