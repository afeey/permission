package com.afeey.permission.core.po;

import com.afeey.permission.core.enums.YesNoEnum;

/**
 * 地址实体类
 * 
 * @author yjl
 *
 */
public class Address {
	private String id;
	private String userId;
	private String regionId;
	private String address;
	private String receiver;
	private String postcode;
	private String cellphone;
	private String phone;
	private YesNoEnum isDefault;
	
	public Address() {
		this.id = ""; 
		this.userId = ""; 
		this.regionId = ""; 
		this.address = ""; 
		this.receiver = ""; 
		this.postcode = ""; 
		this.cellphone = ""; 
		this.phone = ""; 
		this.isDefault = YesNoEnum.NO; 
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	} 
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	} 
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	} 
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	} 
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	} 
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	} 
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	} 
	public YesNoEnum getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(YesNoEnum isDefault) {
		this.isDefault = isDefault;
	} 
}
