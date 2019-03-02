package br.com.arquitetura.service;

import java.util.List;

import br.com.arquitetura.data.ProjectData;

public interface ProjectService {

	List<ProjectData> findAll();

	Long save(ProjectData projectData);

}
