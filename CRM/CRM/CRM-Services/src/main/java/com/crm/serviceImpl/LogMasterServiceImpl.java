package com.crm.serviceImpl;

import static com.crm.utils.DateUtils.convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.LogMaster;
import com.crm.repository.ILogMasterRepository;
import com.crm.service.ILogMasterService;

@Service
public class LogMasterServiceImpl implements ILogMasterService {

	@Autowired
	private ILogMasterRepository logMasterRepository;
	
	/**
	 * Method for find comments by booking id and entry date
	 * @param bId
	 * @param startDate
	 * @param endDate
	 * @return List<LogMaster>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<LogMaster> findCommentByBookingIdAndEntrydate(Integer bId, String startDate, String endDate) {
		if(bId !=null && startDate != null && endDate !=null){
		startDate = 
				 convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(startDate) ;
				endDate = 
						 convertDD_MM_YYYYToYYYY_MM_DDDateFormatWithHHMMSS(endDate) ;}
		return logMasterRepository.findCommentByBookingIdAndEntrydate(bId, startDate, endDate);
	}

}