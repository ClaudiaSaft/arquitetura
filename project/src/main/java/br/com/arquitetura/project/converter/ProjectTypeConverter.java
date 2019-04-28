package br.com.arquitetura.project.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.project.data.ProjectTypeData;
import br.com.arquitetura.project.entity.ProjectType;

public class ProjectTypeConverter {
	
	private ProjectTypeConverter() {
		
	}

	public static List<ProjectTypeData> convertToProjectTypeData(List<ProjectType> projectTypes) {
		List<ProjectTypeData> projectTypesData = new ArrayList<>();
		projectTypes.forEach(p -> {
			if(p.getProjectTypeOwner() == null) {
				projectTypesData.add(convertToProjectTypeData(p));
			}
		});
		return projectTypesData;
	}
	
	private static ProjectTypeData convertToProjectTypeData(ProjectType projectType) {
		ProjectTypeData projectTypeData = new ProjectTypeData(projectType.getUid(), projectType.getName());
		if(projectType.getProjectSubTypes() != null) {
			projectType.getProjectSubTypes().stream().forEach(s -> projectTypeData.addProjectSubType(convertToProjectTypeData(s)));
		}
		return projectTypeData;
	}

	public static ProjectType convertToProjectType(ProjectTypeData projectTypeData) {
		ProjectType projectType = new ProjectType(projectTypeData.getUid(), projectTypeData.getName());
		if(projectTypeData.getProjectSubTypes() != null) {
			projectTypeData.getProjectSubTypes().stream().forEach(p -> projectType.addProjectSubType(convertToProjectType(p)));
		}
		return projectType;
	}

}
