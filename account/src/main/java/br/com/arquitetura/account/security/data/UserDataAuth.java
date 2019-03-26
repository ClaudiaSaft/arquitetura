package br.com.arquitetura.account.security.data;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.arquitetura.account.enumeration.UserRole;

public class UserDataAuth implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Long uid;
	private String name;
	private String email;
	private String password;
	private UserRole role;
	
	public UserDataAuth(Long uid, String name, String email, String password, UserRole role) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Long getUid() {
		return uid;
	}
	
	public String getName() {
		return name;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(this.role.toString()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public static UserDataAuth getUserDataAuth(Principal user) {
		if(user instanceof Authentication){
			Authentication auth = (Authentication) user;
			return (UserDataAuth) auth.getPrincipal();
		}
		return null;
	}
}
