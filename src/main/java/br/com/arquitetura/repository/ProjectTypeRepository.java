package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.entity.ProjectType;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long>{

}
