package com.crm.repository;

import java.util.List;

public interface ICourseMasterRepository {

	public List<Object[]> findCourseMasterByYearAndMonth(String year, String month);
	public List<Object[]> findCourseByYearAndMonthFromCourseTrlocationAndCourseCourses(String year, String month);
	public List<Object[]> findCourseByCourseMaster();
	
	
		
	
}
