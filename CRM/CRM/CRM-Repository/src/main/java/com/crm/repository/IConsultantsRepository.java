package com.crm.repository;

import java.util.List;

import com.crm.model.Consultants;

public interface IConsultantsRepository {

	public List<Consultants> findAllConsultants();

}