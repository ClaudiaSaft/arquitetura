package br.com.arquitetura.project.service;

import java.util.List;

import br.com.arquitetura.project.data.ProjectData;

public interface ProjectService {

	List<ProjectData> findAll();

	Long save(ProjectData projectData);

	void update(ProjectData projectData);

	ProjectData findByUid(Long uidProject);

}
