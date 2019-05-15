package br.com.arquitetura.project.data;

import java.util.Collections;
import java.util.List;

import br.com.arquitetura.account.data.ValidationFiedsData;
import br.com.arquitetura.project.enumeration.ProjectStatusEnum;
import br.com.arquitetura.project.exception.ProjectFieldRequiredException;

public class ProjectData extends ValidationFiedsData{

	private Long uid;
	private String name;
	private String description;
	private Long uidCustomer;
	private Long uidArchitect;
	private Long uidProjectType;
	private Long uidProjectSubType;
	private boolean template;
	private ProjectStatusEnum status;
	private List<ProjectStepData> projectSteps;
	
	public ProjectData() {
	}
	
	private ProjectData(Long uid, String name, String description, Long uidCustomer, Long uidArchitect,
			Long uidProjectType, Long uidProjectSubType, ProjectStatusEnum status, boolean template,
			List<ProjectStepData> projectSteps) {
		this.uid = uid;
		this.status = status;
		this.name = name;
		this.description = description;
		this.uidCustomer = uidCustomer;
		this.uidArchitect = uidArchitect;
		this.uidProjectType = uidProjectType;
		this.uidProjectSubType = uidProjectSubType;
		this.template = template;
		this.projectSteps = projectSteps;
	}

	public Long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Long getUidCustomer() {
		return uidCustomer;
	}

	public Long getUidArchitect() {
		return uidArchitect;
	}

	public Long getUidProjectType() {
		return uidProjectType;
	}
	
	public Long getUidProjectSubType() {
		return uidProjectSubType;
	}
	
	public ProjectStatusEnum getStatus() {
		return status;
	}
	
	public boolean isTemplate() {
		return template;
	}

	public List<ProjectStepData> getProjectSteps() {
		return projectSteps != null ? Collections.unmodifiableList(projectSteps) : projectSteps;
	}
	
	public static class Builder {
		private Long uid;
		private String name;
		private String description;
		private Long uidCustomer;
		private Long uidArchitect;
		private Long uidProjectType;
		private Long uidProjectSubType;
		private boolean template;
		private ProjectStatusEnum status;
		private List<ProjectStepData> projectSteps;
		
		public Builder(String name, Long uidProjectType, Long uidProjectSubType) {
			this.name = name;
			this.uidProjectType = uidProjectType;
			this.uidProjectSubType = uidProjectSubType;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder uidCustomer(Long uidCustomer) {
			this.uidCustomer = uidCustomer;
			return this;
		}
		
		public Builder uidArchitect(Long uidArchitect) {
			this.uidArchitect = uidArchitect;
			return this;
		}

		public Builder template(boolean template) {
			this.template = template;
			return this;
		}
		
		public Builder status(ProjectStatusEnum status) {
			this.status = status;
			return this;
		}
		
		public Builder uid(Long uid) {
			this.uid = uid;
			return this;
		}
		
		public Builder projectSteps(List<ProjectStepData> projectSteps) {
			this.projectSteps = projectSteps;
			return this;
		}

		public ProjectData build() {
			return new ProjectData(uid, name, description, uidCustomer, uidArchitect, uidProjectType, uidProjectSubType, status, template, projectSteps);
		}
	}
	

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(name == null) {
			throw new ProjectFieldRequiredException("name");
		}
		if(uidProjectType == null) {
			throw new ProjectFieldRequiredException("uidProjectType");
		}
		if(uidProjectSubType == null) {
			throw new ProjectFieldRequiredException("uidProjectSubType");
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
