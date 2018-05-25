package com.crm.pojo;

import static com.crm.utils.DateUtils.convertTimeStampToString;

import com.crm.utils.DateUtils;

public class DonationDTO {

	private String collectionDate;

	public DonationDTO(Object[] object) {

		super();
		this.setCollectionDate(object[6] != null ? DateUtils
				.convertStringToDateWithDateFormatYYYY_MM_DD(convertTimeStampToString(object[6]
						.toString()))
				: null);

	}

	public String getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

}