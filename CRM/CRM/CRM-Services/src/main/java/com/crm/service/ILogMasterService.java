package com.crm.service;

import java.util.List;

import com.crm.model.LogMaster;

public interface ILogMasterService {

	public List<LogMaster> findCommentByBookingIdAndEntrydate(Integer bId,
			String startDate, String endDate);

}