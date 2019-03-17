package br.com.arquitetura.account.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="architects")
public class Architect {

	@Id
	@Column(name="uid_architect")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@ManyToOne
	@JoinColumn(name="uid_user")
	private User user;
	
	@Column(name="nr_whatsapp")
	private String whatsapp;
	
	@Column(name="nr_comercial_phone")
	private String comercialPhone;
	
	@Column(name="dt_update")
	private LocalDateTime update;
	
	public Architect() {
	}
	
	public Architect(Long uidArchitect) {
		this.uid = uidArchitect;
	}

	public Long getUid() {
		return uid;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getComercialPhone() {
		return comercialPhone;
	}

	public void setComercialPhone(String comercialPhone) {
		this.comercialPhone = comercialPhone;
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}
}
