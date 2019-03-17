package br.com.arquitetura.project.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.project.data.ProjectStepData;
import br.com.arquitetura.project.entity.ProjectStep;
import br.com.arquitetura.project.entity.Step;

public class ProjectStepConverter {

	public static List<ProjectStep> convertToProjectStep(List<ProjectStepData> projectStepsData) {
		List<ProjectStep> projectSteps = new ArrayList<>();
		projectStepsData.forEach(p -> {
			ProjectStep projectStepOwner = convertToProjectStep(p);
			projectSteps.add(projectStepOwner);
			List<ProjectStep> subProjectSteps = convertToProjectStep(p.getSubProjectSteps());
			subProjectSteps.forEach(sub -> projectStepOwner.addSubProjectStep(sub));
		});
		return projectSteps;
	}

	private static ProjectStep convertToProjectStep(ProjectStepData projectStepData) {
		Step step = StepConverter.convertToStep(projectStepData.getUidStep());
		return new ProjectStep(null, null, step);
	}

	public static List<ProjectStepData> convertToProjectStepData(List<ProjectStep> projectSteps) {
		List<ProjectStepData> projectStepsData = new ArrayList<>();
		projectSteps.forEach(p -> {
			projectStepsData.add(convertToProjectStepData(p));	
		});
		return projectStepsData;
	}

	private static ProjectStepData convertToProjectStepData(ProjectStep projectStep) {
		ProjectStepData projectStepData = new ProjectStepData();
		projectStepData.setUid(projectStep.getUid());
		projectStepData.setStatus(projectStep.getStatus());
		projectStepData.setUidProject(projectStep.getProject() == null ? null : projectStep.getProject().getUid());
		projectStepData.setUidStep(projectStep.getStep().getUid());
		projectStepData.setUidProjectStepOwner(projectStep.getProjectStepOwner() == null ? null : projectStep.getProjectStepOwner().getUid());
		projectStepData.setSubProjectSteps(convertToProjectStepData(projectStep.getSubProjectSteps()));
		return projectStepData;
	}

}
