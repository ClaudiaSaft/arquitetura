package br.com.arquitetura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.ProjectConverter;
import br.com.arquitetura.data.ProjectData;
import br.com.arquitetura.entity.Project;
import br.com.arquitetura.exception.ObjectNotFoundException;
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

	@Override
	public void update(ProjectData projectData) {
		projectData.validateUpdate();
		
		Project projectDataBase = getProjectById(projectData.getUid());
		Project project = ProjectConverter.convertToProject(projectDataBase, projectData);
		projectRepository.save(project);
	}

	private Project getProjectById(Long uidProject) {
		Optional<Project> projectOptional = projectRepository.findById(uidProject);
		if(projectOptional.isPresent()) {
			return projectOptional.get();
		} else {
			throw new ObjectNotFoundException("Projeto");
		}
	}
}
