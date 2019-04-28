package br.com.arquitetura.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.project.converter.ProjectTypeConverter;
import br.com.arquitetura.project.data.ProjectTypeData;
import br.com.arquitetura.project.entity.ProjectType;
import br.com.arquitetura.project.repository.ProjectTypeRepository;
import br.com.arquitetura.project.service.ProjectTypeService;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {
	
	@Autowired
	private ProjectTypeRepository projectTypeRepository;
	
	@Override
	public List<ProjectTypeData> findAll() {
		List<ProjectType> projectTypes = projectTypeRepository.findAll();
		return ProjectTypeConverter.convertToProjectTypeData(projectTypes);
	}

	@Override
	public Long save(ProjectTypeData projectTypeData) {
		ProjectType projectType = ProjectTypeConverter.convertToProjectType(projectTypeData);
		ProjectType projectTypeSaved = projectTypeRepository.save(projectType);
		return projectTypeSaved.getUid();
	}
	
}
