package br.com.arquitetura.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.project.entity.ProjectType;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long>{

}
