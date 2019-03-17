package br.com.arquitetura.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.arquitetura.account.entity.User;
import br.com.arquitetura.security.data.UserDataAuth;
import br.com.arquitetura.security.exception.ObjectNotFoundException;
import br.com.arquitetura.security.repository.UserAuthRepository;
import br.com.arquitetura.security.service.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByEmail(username.toLowerCase());
		return new UserDataAuth(user.getUid(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
	}
	
	private User getUserByEmail(String email) {
		Optional<User> userOptional = userAuthRepository.findByEmailAndActive(email, true);
		if(userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new ObjectNotFoundException("Usuário");
		}
	}
}
