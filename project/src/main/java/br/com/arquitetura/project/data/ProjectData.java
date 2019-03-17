package br.com.arquitetura.project.data;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.account.data.ValidationFiedsData;
import br.com.arquitetura.project.exception.ProjectFieldRequiredException;

public class ProjectData extends ValidationFiedsData{

	private Long uid;
	private String name;
	private String description;
	private Long uidCustomer;
	private Long uidArchitect;
	private Long uidProjectType;
	private List<ProjectStepData> projectSteps;
	
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
	
	public List<ProjectStepData> getProjectSteps() {
		if (projectSteps == null) {
			return new ArrayList<>();
		}
		return projectSteps;
	}

	public void setProjectSteps(List<ProjectStepData> projectSteps) {
		this.projectSteps = projectSteps;
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(name == null) {
			throw new ProjectFieldRequiredException("name");
		}
		if(uidArchitect == null) {
			throw new ProjectFieldRequiredException("uidArchitect");
		}
		if(uidCustomer == null) {
			throw new ProjectFieldRequiredException("uidCustomer");
		}
		if(uidProjectType == null) {
			throw new ProjectFieldRequiredException("uidProjectType");
		}
	}
	
	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new ProjectFieldRequiredException("uid");
		}
	}

}
