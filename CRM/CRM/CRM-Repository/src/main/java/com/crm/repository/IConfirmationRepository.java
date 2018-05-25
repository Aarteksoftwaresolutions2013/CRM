package com.crm.repository;

import java.util.List;

import com.crm.model.Confirmation;

public interface IConfirmationRepository {

	public void saveConfirmation(Confirmation confirmation);

	public Confirmation updateConfirmation(Confirmation confirmation);

	public List<Integer> findMaxId();
}