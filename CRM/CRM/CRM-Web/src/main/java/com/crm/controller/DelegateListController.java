package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.DelegateList;
import com.crm.service.IDelegateListService;

@RestController
public class DelegateListController {

	@Autowired
	private IDelegateListService delegateService;

	/**
	 * Method for save delegate list
	 * @param delegateList
	 * @return void
	 */
	@RequestMapping(value = "/saveDelegateList", method = RequestMethod.POST)
	public void saveDelegateList(@RequestBody DelegateList delegateList) {
		delegateService.saveDelegateList(delegateService
				.findDelegateListMaxId(delegateList));
	}

	/**
	 * Method for update delegate list
	 * @param delegateList
	 * @return DelegateList
	 */
	@RequestMapping(value = "/updateDelegateList", method = RequestMethod.POST)
	public DelegateList updateDelegateList(
			@RequestBody DelegateList delegateList) {
		return delegateService.updateDelegateList(delegateList);
	}

	/**
	 * Method for delete delegate list
	 * @param delegateListId
	 * @return void
	 */
	@RequestMapping(value = "/deleteDelegateList/{delegateListId}", method = RequestMethod.GET)
	public void deleteDelegateList(@PathVariable Integer delegateListId) {
		delegateService.deleteDelegateList(delegateListId);
	}

	/**
	 * Method for delete delegate list by booking id
	 * @param bookingId
	 * @return void
	 */
	@RequestMapping(value = "/deleteDelegateListByBookingId/{bookingId}", method = RequestMethod.GET)
	public void deleteDelegateListByBookingId(@PathVariable Integer bookingId) {
		delegateService.deleteDelegateListByBookingId(bookingId);
	}

	/**
	 * Method for find delegate list by booking id
	 * @param bookingId
	 * @return List<DelegateList>
	 */
	@RequestMapping(value = "/delegateListByBId/{bookingId}", method = RequestMethod.GET)
	public List<DelegateList> findDelegateListByBId(
			@PathVariable Integer bookingId) {
		return delegateService.findDelegateListByBId(bookingId);
	}
}