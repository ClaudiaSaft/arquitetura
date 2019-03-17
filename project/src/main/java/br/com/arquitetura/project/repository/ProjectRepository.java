package br.com.arquitetura.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
