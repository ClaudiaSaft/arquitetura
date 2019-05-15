package br.com.arquitetura.project.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.arquitetura.account.data.ValidationFiedsData;
import br.com.arquitetura.project.enumeration.StepStatusEnum;
import br.com.arquitetura.project.exception.StepFieldRequiredException;

public class StepData extends ValidationFiedsData{

	private Long uid;
	private String description;
	private StepStatusEnum status;
	private Long uidStepOwner;
	private List<StepData> subSteps;
	
	public StepData() {
	}
	
	public StepData(Long uid, String description, StepStatusEnum status) {
		this.uid = uid;
		this.description = description;
		this.status = status;
	}
	
	public Long getUid() {
		return uid;
	}
	public String getDescription() {
		return description;
	}
	
	public StepStatusEnum getStatus() {
		return status;
	}
	
	public Long getUidStepOwner() {
		return uidStepOwner;
	}	

	public List<StepData> getSubSteps() {
		if(subSteps == null) {
			return new ArrayList<>();
		}
		return Collections.unmodifiableList(subSteps);
	}
	
	public void addSubProjectStep(StepData stepData) {
		if(subSteps == null) {
			subSteps = new ArrayList<>();
		}
		subSteps.add(stepData);
		stepData.uidStepOwner = this.getUid();
	}

	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(description == null) {
			throw new StepFieldRequiredException("description");
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
