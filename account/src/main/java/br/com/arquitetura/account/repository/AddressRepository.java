package br.com.arquitetura.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.account.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
