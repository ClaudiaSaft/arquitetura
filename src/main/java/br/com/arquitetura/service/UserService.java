package br.com.arquitetura.service;

import java.util.List;

import br.com.arquitetura.data.UserData;
import br.com.arquitetura.entity.User;

public interface UserService {

	User save(UserData userData);

	List<UserData> findAll();

	UserData findByUid(Long uidUser);

	void update(UserData userData);

	void inactivate(Long uidUser);
	
	void activate(Long uidUser);

	void changePassword(Long uidUser, UserData userData);
	
}
