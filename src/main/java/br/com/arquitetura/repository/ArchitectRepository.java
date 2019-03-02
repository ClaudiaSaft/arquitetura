package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.entity.Architect;

public interface ArchitectRepository extends JpaRepository<Architect, Long>{

}
