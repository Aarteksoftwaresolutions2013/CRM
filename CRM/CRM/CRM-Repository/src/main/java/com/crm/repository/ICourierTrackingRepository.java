package com.crm.repository;

import java.util.List;

import com.crm.model.CourierTracking;

public interface ICourierTrackingRepository {

	public void saveCourierTracking(CourierTracking courierTracking);

	public CourierTracking updateCourierTracking(CourierTracking courierTracking);

	public List<Integer> findMaxId();
}