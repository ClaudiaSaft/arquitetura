package br.com.arquitetura.account.data;

import javax.validation.constraints.Email;

import br.com.arquitetura.account.enumeration.UserRole;
import br.com.arquitetura.account.exception.UserFieldRequiredException;

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
			throw new UserFieldRequiredException("name");
		}
		if(email == null) {
			throw new UserFieldRequiredException("email");
		}
		if(role == null) {
			throw new UserFieldRequiredException("role");
		}
	}
	
	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new UserFieldRequiredException("uid");
		}
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validateRequiredFields();
	}

}
