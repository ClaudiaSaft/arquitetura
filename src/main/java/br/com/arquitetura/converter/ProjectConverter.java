package br.com.arquitetura.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.data.ProjectData;
import br.com.arquitetura.entity.Architect;
import br.com.arquitetura.entity.Customer;
import br.com.arquitetura.entity.Project;
import br.com.arquitetura.entity.ProjectType;

public class ProjectConverter {

	public static List<ProjectData> convertToProjectData(List<Project> projects) {
		List<ProjectData> projectsData = new ArrayList<>();
		projects.forEach(p -> projectsData.add(convertToProjectData(p)));
		return projectsData;
	}

	private static ProjectData convertToProjectData(Project project) {
		ProjectData projectData = new ProjectData();
		projectData.setUid(project.getUid());
		projectData.setName(project.getName());
		projectData.setDescription(project.getDescription());
		projectData.setUidArchitect(project.getArchitect().getUid());
		projectData.setUidCustomer(project.getCustomer().getUid());
		projectData.setUidProjectType(project.getType().getUid());
		projectData.setProjectSteps(ProjectStepConverter.convertToProjectStepData(project.getSteps()));
		return projectData;
	}

	public static Project convertToProject(ProjectData projectData) {
		Project project = new Project(projectData.getName(), projectData.getDescription(), 
				new Customer(projectData.getUidCustomer()), new Architect(projectData.getUidArchitect()), 
				new ProjectType(projectData.getUidProjectType()));
		
		project.addAllSteps(ProjectStepConverter.convertToProjectStep(projectData.getProjectSteps()));
		
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
