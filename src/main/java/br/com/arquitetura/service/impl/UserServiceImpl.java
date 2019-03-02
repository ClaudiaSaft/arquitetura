package br.com.arquitetura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.UserConverter;
import br.com.arquitetura.data.UserData;
import br.com.arquitetura.entity.User;
import br.com.arquitetura.exception.ObjectNotFoundException;
import br.com.arquitetura.repository.UserRepository;
import br.com.arquitetura.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserData userData) {
		userData.validateCreate();
		User user = UserConverter.convertToUser(userData);
		return userRepository.save(user);
	}

	@Override
	public List<UserData> findAll() {
		List<User> users = userRepository.findAll();
		return UserConverter.convertToUserData(users);
	}

	@Override
	public UserData findByUid(Long uidUser) {
		User user = getUserById(uidUser);
		return UserConverter.convertToUserData(user);
	}
	
	@Override
	public void update(UserData userData) {
		userData.validateUpdate();
		User userDataBase = getUserById(userData.getUid());
		User user = UserConverter.convertToUser(userDataBase, userData);
		userRepository.save(user);
	}

	@Override
	public void inactivate(Long uidUser) {
		User user = getUserById(uidUser);
		user.setActive(false);
		userRepository.save(user);
	}
	
	@Override
	public void activate(Long uidUser) {
		User user = getUserById(uidUser);
		user.setActive(true);
		userRepository.save(user);
	}

	@Override
	public void changePassword(Long uidUser, UserData userData) {
		User user = getUserById(uidUser);
		user.setPassword(userData.getPassword());
		userRepository.save(user);
	}
	
	private User getUserById(Long uidUser) {
		Optional<User> userOptional = userRepository.findById(uidUser);
		if(userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new ObjectNotFoundException("Usuário");
		}
	}
}
