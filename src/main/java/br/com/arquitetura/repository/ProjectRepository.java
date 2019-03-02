package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
