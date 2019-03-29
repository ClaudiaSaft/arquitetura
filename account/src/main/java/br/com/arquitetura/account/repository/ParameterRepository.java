package br.com.arquitetura.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.account.entity.ParameterSystem;

public interface ParameterRepository extends JpaRepository<ParameterSystem, String> {

	ParameterSystem findByCode(String parameterConstant);

}
