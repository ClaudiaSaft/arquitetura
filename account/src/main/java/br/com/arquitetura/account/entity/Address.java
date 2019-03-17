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

@Entity
@Table(name="address")
public class Address {

	@Id
	@Column(name="uid_address")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="nm_address")
	private String name;
	
	@Column(name="nm_neighborhood")
	private String neighborhood;
	
	@Column(name="ds_complement")
	private String complement;
	
	@Column(name="nr_address")
	private Integer number;
	
	@Column(name="dt_create")
	private LocalDateTime create;
	 
	@Column(name="dt_update")
	private LocalDateTime update;
	
	
	public Address() {
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public Address(Long uid, String name, String neighborhood, String complement, Integer number) {
		this();
		this.uid = uid;
		this.name = name;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.number = number;
	}

	public Long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}
}
