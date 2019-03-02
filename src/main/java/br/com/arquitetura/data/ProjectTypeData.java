package br.com.arquitetura.data;

import br.com.arquitetura.exception.FieldRequiredException;

public class ProjectTypeData extends ValidationFiedsData{

	private Long uid;
	private String name;
	private String description;
	
	public ProjectTypeData() {
	}
	
	public ProjectTypeData(Long uid, String name, String description) {
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
			throw new FieldRequiredException("project type name");
		}
	}

	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new FieldRequiredException("project type uid");
		}
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}
	
}
