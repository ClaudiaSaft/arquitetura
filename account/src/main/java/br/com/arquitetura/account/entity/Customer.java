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
@Table(name="customer")
public class Customer {

	@Id
	@Column(name="uid_customer")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@ManyToOne
	@JoinColumn(name="uid_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="uid_address")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="uid_architect")
	private Architect architect;

	@Column(name="nr_whatsapp")
	private String whatsapp;
	
	@Column(name="nr_personal_phone")
	private String personalPhone;
	
	@Column(name="nr_comercial_phone")
	private String comercialPhone;
	
	@Column(name="dt_update")
	private LocalDateTime update;
	
	public Customer() {
	}
	
	public Customer(Long uidCustomer) {
		this.uid = uidCustomer;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getPersonalPhone() {
		return personalPhone;
	}

	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}

	public String getComercialPhone() {
		return comercialPhone;
	}

	public void setComercialPhone(String comercialPhone) {
		this.comercialPhone = comercialPhone;
	}

	public Architect getArchitect() {
		return architect;
	}

	public void setArchitect(Architect architect) {
		this.architect = architect;
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}
}
