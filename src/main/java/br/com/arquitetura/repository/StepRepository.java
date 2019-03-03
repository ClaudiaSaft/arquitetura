package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.entity.Step;

public interface StepRepository extends JpaRepository<Step, Long>{

}
