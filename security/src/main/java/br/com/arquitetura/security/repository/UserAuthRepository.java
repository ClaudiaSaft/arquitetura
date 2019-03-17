package br.com.arquitetura.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arquitetura.account.entity.User;

@Repository
public interface UserAuthRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmailAndActive(String email, boolean active);
	
}
