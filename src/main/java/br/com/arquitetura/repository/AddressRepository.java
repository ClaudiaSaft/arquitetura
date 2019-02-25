package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
