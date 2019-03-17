package br.com.arquitetura.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arquitetura.account.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
