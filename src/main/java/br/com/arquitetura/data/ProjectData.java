package br.com.arquitetura.data;

import br.com.arquitetura.exception.FieldRequiredException;

public class ProjectData extends ValidationFiedsData{

	private Long uid;
	private String name;
	private String description;
	private Long uidCustomer;
	private Long uidArchitect;
	private Long uidProjectType;
	
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

	public Long getUidCustomer() {
		return uidCustomer;
	}

	public void setUidCustomer(Long uidCustomer) {
		this.uidCustomer = uidCustomer;
	}

	public Long getUidArchitect() {
		return uidArchitect;
	}

	public void setUidArchitect(Long uidArchitect) {
		this.uidArchitect = uidArchitect;
	}

	public Long getUidProjectType() {
		return uidProjectType;
	}

	public void setUidProjectType(Long uidProjectType) {
		this.uidProjectType = uidProjectType;
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(name == null) {
			throw new FieldRequiredException("project name");
		}
		if(uidArchitect == null) {
			throw new FieldRequiredException("project uidArchitect");
		}
		if(uidCustomer == null) {
			throw new FieldRequiredException("project uidCustomer");
		}
		if(uidProjectType == null) {
			throw new FieldRequiredException("project uidProjectType");
		}
	}
	
	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new FieldRequiredException("project uid");
		}
	}

}
