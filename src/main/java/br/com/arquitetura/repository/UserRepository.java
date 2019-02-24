package br.com.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arquitetura.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
}
