package br.com.arquitetura.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.project.entity.Step;

public interface StepRepository extends JpaRepository<Step, Long>{

}
