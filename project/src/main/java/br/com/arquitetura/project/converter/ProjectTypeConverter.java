package br.com.arquitetura.project.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.project.data.ProjectTypeData;
import br.com.arquitetura.project.entity.ProjectType;

public class ProjectTypeConverter {

	public static List<ProjectTypeData> convertToProjectTypeData(List<ProjectType> projectTypes) {
		List<ProjectTypeData> projectTypesData = new ArrayList<>();
		projectTypes.forEach(p -> projectTypesData.add(convertToProjectTypeData(p)));
		return projectTypesData;
	}
	
	private static ProjectTypeData convertToProjectTypeData(ProjectType projectType) {
		return new ProjectTypeData(projectType.getUid(), projectType.getName(), projectType.getDescription());
	}

	public static ProjectType convertToProjectType(ProjectTypeData projectTypeData) {
		return convertToProjectType(new ProjectType(), projectTypeData);
	}

	public static ProjectType convertToProjectType(ProjectType projectTypeDataBase, ProjectTypeData projectTypeData) {
		projectTypeDataBase.setName(projectTypeData.getName());
		projectTypeDataBase.setDescription(projectTypeData.getDescription());
		
		return projectTypeDataBase;
	}

}
