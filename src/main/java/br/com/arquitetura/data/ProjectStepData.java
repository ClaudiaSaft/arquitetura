package br.com.arquitetura.data;

import br.com.arquitetura.enumeration.StepStatusEnum;
import br.com.arquitetura.exception.FieldRequiredException;

public class ProjectStepData extends ValidationFiedsData{

	private Long uid;
	private Long uidProject;
	private Long uidStep;
	private StepStatusEnum status;
	
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

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(uidProject == null) {
			throw new FieldRequiredException("project step uidProject");
		}
		if(uidStep == null) {
			throw new FieldRequiredException("project step uidStep");
		}
	}
	
	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new FieldRequiredException("project step uid");
		}
	}

}
