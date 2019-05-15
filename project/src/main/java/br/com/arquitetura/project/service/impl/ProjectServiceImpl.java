package br.com.arquitetura.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.project.converter.ProjectConverter;
import br.com.arquitetura.project.data.ProjectData;
import br.com.arquitetura.project.entity.Project;
import br.com.arquitetura.project.exception.ProjectNotFoundException;
import br.com.arquitetura.project.repository.ProjectRepository;
import br.com.arquitetura.project.service.ProjectService;

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
			throw new ProjectNotFoundException();
		}
	}

	@Override
	public ProjectData findByUid(Long uidProject) {
		Project projectDataBase = getProjectById(uidProject);
		return ProjectConverter.convertToProjectData(projectDataBase);
	}
}
