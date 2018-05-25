package com.crm.repository;

import java.util.List;

import com.crm.model.LogMaster;

public interface ILogMasterRepository {

	public void saveLogMaster(LogMaster logMaster);

	public LogMaster updateLogMaster(LogMaster logMaster);

	public List<Integer> findMaxId();

	public List<LogMaster> findCommentByBookingIdAndEntrydate(Integer bId,
			String startDate, String endDate);
}