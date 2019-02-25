package br.com.arquitetura.data;

import javax.validation.Valid;

import br.com.arquitetura.exception.FieldRequiredException;
import liquibase.util.StringUtils;

public class CustomerData {
	
	private Long uid;
	@Valid
	private UserData user;
	private AddressData address;
	private String whatsapp;
	private String personalPhone;
	private String comercialPhone;
	
	public CustomerData() {
	}
	
	public CustomerData(Long uid, UserData user, AddressData address, String whatsapp, String personalPhone,
			String comercialPhone) {
		this.uid = uid;
		this.user = user;
		this.address = address;
		this.whatsapp = whatsapp;
		this.personalPhone = personalPhone;
		this.comercialPhone = comercialPhone;
	}

	public Long getUid() {
		return uid;
	}
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	public AddressData getAddress() {
		return address;
	}
	public void setAddress(AddressData address) {
		this.address = address;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getPersonalPhone() {
		return personalPhone;
	}
	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}
	public String getComercialPhone() {
		return comercialPhone;
	}
	public void setComercialPhone(String comercialPhone) {
		this.comercialPhone = comercialPhone;
	}

	public void validateCreate() {
		validateRequiredFields(); 
	}

	private void validateRequiredFields() {
		if(StringUtils.isEmpty(whatsapp) && StringUtils.isEmpty(personalPhone) && StringUtils.isEmpty(comercialPhone)) { 
			throw new FieldRequiredException(new String[] {"customer whatsapp", "customer personalPhone", "customer comercialPhone"});
		}
	} 
	
	public void validateUpdate() {
		validateRequiredUid();
		validateRequiredFields();
	}

	private void validateRequiredUid() {
		if(uid == null) {
			throw new FieldRequiredException("customer uid");
		}
	} 
}
