package br.com.arquitetura.account.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arquitetura.account.converter.UserConverter;
import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.User;
import br.com.arquitetura.account.exception.UserNotFoundException;
import br.com.arquitetura.account.repository.UserRepository;
import br.com.arquitetura.account.security.data.UserDataAuth;
import br.com.arquitetura.account.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserData save(UserData userData) {
		userData.validateCreate();
		User user = UserConverter.convertToUser(userData);
		User userSaved = userRepository.save(user);
		return UserConverter.convertToUserData(userSaved);
	}

	@Override
	@Transactional(readOnly=true)
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
	
	@Transactional(readOnly=true)
	private User getUserById(Long uidUser) {
		Optional<User> userOptional = userRepository.findById(uidUser);
		if(userOptional.isPresent()) {
			return userOptional.get(); 
		} else {
			throw new UserNotFoundException();
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByEmail(username.toLowerCase());
		return new UserDataAuth(user.getUid(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
	}
	
	private User getUserByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmailAndActive(email, true);
		if(userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new UserNotFoundException();
		}
	}
}
