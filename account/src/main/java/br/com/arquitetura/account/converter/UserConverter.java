package br.com.arquitetura.account.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.User;

public class UserConverter {

	public static User convertToUser(UserData userData) {
		return new User(userData.getUid(), userData.getName(), userData.getEmail(), userData.getPassword(), userData.getRole());
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
		return new UserData(user.getUid(), user.getName(), user.getEmail(), user.getPassword(), user.isActive(), user.getRole());
	}

}
