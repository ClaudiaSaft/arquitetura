package br.com.arquitetura.project.data;

import br.com.arquitetura.project.enumeration.StepStatusEnum;

public class ProjectStepData {

	private Long uid;
	private Long uidProject;
	private StepData step;
	private StepStatusEnum status;
	
	public ProjectStepData() {
	}
	
	public ProjectStepData(Long uid, Long uidProject, StepData step, StepStatusEnum status) {
		this.uid = uid;
		this.uidProject = uidProject;
		this.step = step;
		this.status = status;
	}

	public Long getUid() {
		return uid;
	}
	
	public Long getUidProject() {
		return uidProject;
	}
	
	public StepData getStep() {
		return step;
	}
	
	public StepStatusEnum getStatus() {
		return status;
	}
	
}
