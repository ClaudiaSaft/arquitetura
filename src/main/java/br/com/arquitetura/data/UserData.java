package br.com.arquitetura.data;

import javax.validation.constraints.Email;

import br.com.arquitetura.enumeration.UserRole;
import br.com.arquitetura.exception.FieldRequiredException;

public class UserData extends ValidationFiedsData{

	private Long uid;
	private boolean active;
	private String name;
	@Email
	private String email;
	private String password;
	private UserRole role;

	public UserData() {
	}

	public UserData(Long uid, String name, String email, String password, boolean active, UserRole role) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
		this.role = role;
	}

	public UserData(UserData userData, UserRole role) {
		this(userData.getUid(), userData.getName(), userData.getEmail(), userData.getPassword(), userData.isActive(), role);
	}

	public Long getUid() {
		return uid;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getRole() {
		return role;
	}

	@Override
	protected void validateRequiredFieldsUpdate() {
		validateRequiredFields();
	}

	private void validateRequiredFields() {
		if(name == null) {
			throw new FieldRequiredException("user name");
		}
		if(email == null) {
			throw new FieldRequiredException("user email");
		}
	}
	
	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new FieldRequiredException("user uid");
		}
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

}
