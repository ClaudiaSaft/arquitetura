package br.com.arquitetura.service;

import java.util.List;

import br.com.arquitetura.data.ProjectTypeData;

public interface ProjectTypeService {

	List<ProjectTypeData> findAll();

	Long save(ProjectTypeData projectTypeData);

	void update(ProjectTypeData projectTypeData);

}
