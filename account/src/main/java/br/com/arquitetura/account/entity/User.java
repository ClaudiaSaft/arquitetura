package br.com.arquitetura.account.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.arquitetura.account.enumeration.UserRole;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="uid_user")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="nm_user")
	private String name;
	
	@Column(name="ds_email")
	private String email;
	
	@Column(name="ds_password")
	private String password;
	
	@Column(name="fl_active")
	private boolean active;
	
	@Column(name="cd_role")
	private UserRole role;
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	
	public User() {
		this.active = true;
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public User(Long uid, String name, String email, String password, UserRole role) {
		this(name, email, password, role);
		this.uid = uid;
	}
	
	public User(String name, String email, String password, UserRole role) {
		this();
		this.name = name;
		this.email = email.toLowerCase();
		this.password = encodePassword(password);
		this.role = role;
	}

	private String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
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
		this.email = email.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = encodePassword(password);
	}

	public Long getUid() {
		return uid;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}
}
