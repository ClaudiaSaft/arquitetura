package br.com.arquitetura.data;

import javax.validation.Valid;

import br.com.arquitetura.exception.FieldRequiredException;
import liquibase.util.StringUtils;

public class ArchitectData extends ValidationFiedsData{

	private Long uid;
	@Valid
	private UserData user;
	private String whatsapp;
	private String comercialPhone;
	
	public ArchitectData() {
	}
	
	public ArchitectData(Long uid, UserData userData, String whatsapp, String comercialPhone) {
		this.uid = uid;
		this.user = userData;
		this.whatsapp = whatsapp;
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
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getComercialPhone() {
		return comercialPhone;
	}
	public void setComercialPhone(String comercialPhone) {
		this.comercialPhone = comercialPhone;
	}
	
	@Override
	protected void validateRequiredFieldsUpdate() {
		user.validateUpdate();
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(StringUtils.isEmpty(whatsapp) && StringUtils.isEmpty(comercialPhone)) { 
			throw new FieldRequiredException(new String[] {"architect whatsapp", "architect comercialPhone"});
		}
	}
	
	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new FieldRequiredException("architect uid");
		}
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		user.validateCreate();
		validateRequiredFields();
	}
	
}
