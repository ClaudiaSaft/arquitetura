package br.com.arquitetura.project.data;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.account.data.ValidationFiedsData;
import br.com.arquitetura.project.enumeration.StepStatusEnum;
import br.com.arquitetura.project.exception.ProjectStepFieldRequiredException;

public class ProjectStepData extends ValidationFiedsData{

	private Long uid;
	private Long uidProject;
	private Long uidStep;
	private StepStatusEnum status;
	private Long uidProjectStepOwner;
	private List<ProjectStepData> subProjectSteps = new ArrayList<>();
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getUidProject() {
		return uidProject;
	}

	public void setUidProject(Long uidProject) {
		this.uidProject = uidProject;
	}

	public Long getUidStep() {
		return uidStep;
	}

	public void setUidStep(Long uidStep) {
		this.uidStep = uidStep;
	}

	public StepStatusEnum getStatus() {
		return status;
	}

	public void setStatus(StepStatusEnum status) {
		this.status = status;
	}
	
	public Long getUidProjectStepOwner() {
		return uidProjectStepOwner;
	}

	public void setUidProjectStepOwner(Long uidProjectStepOwner) {
		this.uidProjectStepOwner = uidProjectStepOwner;
	}

	public List<ProjectStepData> getSubProjectSteps() {
		return subProjectSteps;
	}

	public void setSubProjectSteps(List<ProjectStepData> subProjectSteps) {
		this.subProjectSteps = subProjectSteps;
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(uidProject == null) {
			throw new ProjectStepFieldRequiredException("uidProject");
		}
		if(uidStep == null) {
			throw new ProjectStepFieldRequiredException("uidStep");
		}
	}
	
	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new ProjectStepFieldRequiredException("uid");
		}
	}

}