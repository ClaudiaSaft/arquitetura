package br.com.arquitetura.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.account.entity.Architect;

public interface ArchitectRepository extends JpaRepository<Architect, Long>{

	Architect findByUserUid(Long uidUser);

}
