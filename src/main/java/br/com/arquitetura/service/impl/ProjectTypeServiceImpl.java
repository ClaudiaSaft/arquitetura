package br.com.arquitetura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.ProjectTypeConverter;
import br.com.arquitetura.data.ProjectTypeData;
import br.com.arquitetura.entity.ProjectType;
import br.com.arquitetura.exception.ObjectNotFoundException;
import br.com.arquitetura.repository.ProjectTypeRepository;
import br.com.arquitetura.service.ProjectTypeService;

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
		projectTypeData.validateCreate();
		ProjectType projectType = ProjectTypeConverter.convertToProjectType(projectTypeData);
		ProjectType projectTypeSaved = projectTypeRepository.save(projectType);
		return projectTypeSaved.getUid();
	}

	@Override
	public void update(ProjectTypeData projectTypeData) {
		projectTypeData.validateUpdate();
		ProjectType projectTypeDataBase = getUserById(projectTypeData.getUid());
		ProjectType projectType = ProjectTypeConverter.convertToProjectType(projectTypeDataBase, projectTypeData);
		projectTypeRepository.save(projectType);
	}
	
	private ProjectType getUserById(Long uidProjectType) {
		Optional<ProjectType> projectTypeOptional = projectTypeRepository.findById(uidProjectType);
		if(projectTypeOptional.isPresent()) {
			return projectTypeOptional.get();
		} else {
			throw new ObjectNotFoundException("Tipo de projeto");
		}
	}


}
