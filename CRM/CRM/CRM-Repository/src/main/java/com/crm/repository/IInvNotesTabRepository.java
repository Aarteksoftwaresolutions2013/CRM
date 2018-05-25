package com.crm.repository;

import java.util.List;

import com.crm.model.InvNotesTab;

public interface IInvNotesTabRepository {

	public void saveInvNotesTab(InvNotesTab invNotesTab);

	public InvNotesTab updateInvNotesTab(InvNotesTab invNotesTab);
	
	public List<Integer> findMaxId();

}
