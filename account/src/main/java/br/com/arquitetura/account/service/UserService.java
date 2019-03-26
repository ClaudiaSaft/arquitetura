package br.com.arquitetura.account.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.arquitetura.account.data.UserData;

public interface UserService extends UserDetailsService{

	UserData save(UserData userData);

	List<UserData> findAll();

	UserData findByUid(Long uidUser);

	void update(UserData userData);

	void inactivate(Long uidUser);
	
	void activate(Long uidUser);

	void changePassword(Long uidUser, UserData userData);
	
}
