package br.com.arquitetura.project.data;

import br.com.arquitetura.project.enumeration.StepStatusEnum;

public class ProjectStepData {

	private Long uid;
	private Long uidProject;
	private StepData stepData;
	private StepStatusEnum status;
	
	public ProjectStepData() {
	}
	
	public ProjectStepData(Long uid, Long uidProject, StepData stepData, StepStatusEnum status) {
		this.uid = uid;
		this.uidProject = uidProject;
		this.stepData = stepData;
		this.status = status;
	}

	public Long getUid() {
		return uid;
	}
	
	public Long getUidProject() {
		return uidProject;
	}
	
	public StepData getStepData() {
		return stepData;
	}
	
	public StepStatusEnum getStatus() {
		return status;
	}
	
}
