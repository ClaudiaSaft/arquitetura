package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
