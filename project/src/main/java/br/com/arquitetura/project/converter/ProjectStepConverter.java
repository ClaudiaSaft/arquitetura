package br.com.arquitetura.project.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.project.data.ProjectStepData;
import br.com.arquitetura.project.entity.Project;
import br.com.arquitetura.project.entity.ProjectStep;
import br.com.arquitetura.project.entity.Step;

public class ProjectStepConverter {
	
	private ProjectStepConverter() {
		
	}

	public static List<ProjectStep> convertToProjectStep(List<ProjectStepData> projectStepsData) {
		List<ProjectStep> projectSteps = new ArrayList<>();
		if(projectStepsData != null) {
			projectStepsData.forEach(p -> projectSteps.add(convertToProjectStep(p)));
		}
		return projectSteps;
	}

	private static ProjectStep convertToProjectStep(ProjectStepData projectStepData) {
		Step step = StepConverter.convertToStep(projectStepData.getStepData());
		Project project = ProjectConverter.convertToProject(projectStepData.getUidProject());
		return new ProjectStep(project, step);
	}

	public static List<ProjectStepData> convertToProjectStepData(List<ProjectStep> projectSteps) {
		List<ProjectStepData> projectStepsData = new ArrayList<>();
		projectSteps.forEach(p -> projectStepsData.add(convertToProjectStepData(p)));
		return projectStepsData;
	}

	private static ProjectStepData convertToProjectStepData(ProjectStep projectStep) {
		return new ProjectStepData(projectStep.getUid(), projectStep.getProject().getUid(), 
				StepConverter.convertToStepData(projectStep.getStep()), projectStep.getStatus());
	}

}
