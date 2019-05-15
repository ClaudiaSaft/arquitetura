package br.com.arquitetura.project.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.project.data.ProjectData;
import br.com.arquitetura.project.data.ProjectStepData;
import br.com.arquitetura.project.entity.Project;

public class ProjectConverter {
	
	private ProjectConverter() {
		
	}

	public static List<ProjectData> convertToProjectData(List<Project> projects) {
		List<ProjectData> projectsData = new ArrayList<>();
		projects.forEach(p -> projectsData.add(convertToProjectDataWithoutSteps(p)));
		return projectsData;
	}

	private static ProjectData convertToProjectDataWithoutSteps(Project project) {
		return new ProjectData.Builder(project.getName(), project.getType().getUid(), project.getSubType().getUid())
				.uid(project.getUid())
				.description(project.getDescription())
				.uidArchitect(project.getArchitect().getUid())
				.uidCustomer(project.getCustomer().getUid())
				.status(project.getStatus())
				.build();
	}
	
	public static ProjectData convertToProjectData(Project project) {
		List<ProjectStepData> projectStepsData = ProjectStepConverter.convertToProjectStepData(project.getProjectSteps());
		
		return new ProjectData.Builder(project.getName(), project.getType().getUid(), project.getSubType().getUid())
				.uid(project.getUid())
				.description(project.getDescription())
				.uidArchitect(project.getArchitect().getUid())
				.uidCustomer(project.getCustomer().getUid())
				.projectSteps(projectStepsData)
				.status(project.getStatus())
				.build();
	}

	public static Project convertToProject(ProjectData projectData) {
		Project project = new Project.Builder(projectData.getName(), projectData.getUidProjectType(), projectData.getUidProjectSubType())
				.description(projectData.getDescription())
				.uidArchitect(projectData.getUidArchitect())
				.uidCustomer(projectData.getUidCustomer())
				.build();
		
		project.addAllStepProjects(ProjectStepConverter.convertToProjectStep(projectData.getProjectSteps()));

		return project;
	}

	public static Project convertToProject(Long uidProject) {
		return new Project(uidProject);
	}

	public static Project convertToProject(Project projectDataBase, ProjectData projectData) {
		projectDataBase.setDescription(projectData.getDescription());
		projectDataBase.setName(projectData.getName());
		return projectDataBase;
	}

}
