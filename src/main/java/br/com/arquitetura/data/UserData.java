package br.com.arquitetura.data;

import javax.validation.constraints.Email;

import br.com.arquitetura.exception.FieldRequiredException;

public class UserData extends ValidationFiedsData{

	private Long uid;
	private boolean active;
	private String name;
	@Email
	private String email;
	private String password;

	public UserData() {
	}

	public UserData(Long uid, String name, String email, String password, boolean active) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
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
