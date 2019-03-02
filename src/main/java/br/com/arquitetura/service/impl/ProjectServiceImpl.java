package br.com.arquitetura.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.ProjectConverter;
import br.com.arquitetura.data.ProjectData;
import br.com.arquitetura.entity.Project;
import br.com.arquitetura.repository.ProjectRepository;
import br.com.arquitetura.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<ProjectData> findAll() {
		List<Project> projects = projectRepository.findAll();
		return ProjectConverter.convertToProjectData(projects);
	}
	
	@Override
	public Long save(ProjectData projectData) {
		projectData.validateCreate();
		Project project = ProjectConverter.convertToProject(projectData);
		Project projectSaved = projectRepository.save(project);
		return projectSaved.getUid();
	}

}
