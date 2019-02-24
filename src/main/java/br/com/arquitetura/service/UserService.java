package br.com.arquitetura.service;

import java.util.List;

import br.com.arquitetura.data.UserData;

public interface UserService {

	Long save(UserData userData);

	List<UserData> findAll();

	UserData findByUid(Long uidUser);

	void update(UserData userData);

	void inactivate(Long uidUser);
	
	void activate(Long uidUser);

	void changePassword(Long uidUser, UserData userData);
	
}
