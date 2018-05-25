package com.crm.repository;

import java.util.List;

public interface IDonationRepository {

	public List<Object[]> findDonationByYearAndMonth(String year, String month);

}