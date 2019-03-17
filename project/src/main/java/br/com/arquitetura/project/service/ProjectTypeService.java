package br.com.arquitetura.project.service;

import java.util.List;

import br.com.arquitetura.project.data.ProjectTypeData;

public interface ProjectTypeService {

	List<ProjectTypeData> findAll();

	Long save(ProjectTypeData projectTypeData);

	void update(ProjectTypeData projectTypeData);

}
