package br.com.arquitetura.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.data.ProjectStepData;
import br.com.arquitetura.entity.ProjectStep;

public class ProjectStepConverter {

	public static List<ProjectStep> convertToProjectStep(List<ProjectStepData> projectStepsData) {
		List<ProjectStep> projectSteps = new ArrayList<>();
		projectStepsData.forEach(p -> projectSteps.add(convertToProjectStep(p)));
		return projectSteps;
	}

	private static ProjectStep convertToProjectStep(ProjectStepData projectStepData) {
		ProjectStep projectStep = new ProjectStep();
		projectStep.setStep(StepConverter.convertToStep(projectStepData.getUidStep()));
		projectStep.setProject(ProjectConverter.convertToProject(projectStepData.getUidProject()));
		return projectStep;
	}

	public static List<ProjectStepData> convertToProjectStepData(List<ProjectStep> projectSteps) {
		List<ProjectStepData> projectStepsData = new ArrayList<>();
		projectSteps.forEach(p -> projectStepsData.add(convertToProjectStepData(p)));
		return projectStepsData;
	}

	private static ProjectStepData convertToProjectStepData(ProjectStep projectStep) {
		ProjectStepData projectStepData = new ProjectStepData();
		projectStepData.setUid(projectStep.getUid());
		projectStepData.setStatus(projectStep.getStatus());
		projectStepData.setUidProject(projectStep.getProject().getUid());
		projectStepData.setUidStep(projectStep.getStep().getUid());
		return projectStepData;
	}

}
