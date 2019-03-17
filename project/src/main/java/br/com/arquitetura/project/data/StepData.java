package br.com.arquitetura.project.data;

import br.com.arquitetura.account.data.ValidationFiedsData;
import br.com.arquitetura.project.exception.StepFieldRequiredException;

public class StepData extends ValidationFiedsData{

	private Long uid;
	private String name;
	private String description;
	
	public StepData() {
	}
	
	public StepData(Long uid, String name, String description) {
		this.uid = uid;
		this.name = name;
		this.description = description;
	}
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(name == null) {
			throw new StepFieldRequiredException("name");
		}
	}

	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new StepFieldRequiredException("uid");
		}
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}
	
}
