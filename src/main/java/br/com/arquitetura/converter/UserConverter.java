package br.com.arquitetura.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.data.UserData;
import br.com.arquitetura.entity.User;

public class UserConverter {

	public static User convertToUser(UserData userData) {
		return new User(userData.getName(), userData.getEmail(), userData.getPassword());
	}
	
	public static User convertToUser(User user, UserData userData) {
		user.setEmail(userData.getEmail());
		user.setName(userData.getName());
		return user;
	}

	public static List<UserData> convertToUserData(List<User> users) {
		List<UserData> usersData = new ArrayList<>();
		users.forEach(u -> usersData.add(convertToUserData(u)));
		return usersData;
	}

	public static UserData convertToUserData(User user) {
		return new UserData(user.getUid(), user.getName(), user.getEmail(), user.getPassword(), user.isActive());
	}

}
