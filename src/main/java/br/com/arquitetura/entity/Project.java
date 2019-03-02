package br.com.arquitetura.entity;

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

import br.com.arquitetura.enumeration.ProjectStatusEnum;

@Entity
@Table(name="projects")
public class Project {

	@Id
	@Column(name="uid_project")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="nm_project")
	private String name;
	
	@Column(name="ds_project")
	private String description;
	
	@Column(name="cd_status")
	private ProjectStatusEnum status;

	@ManyToOne
	@JoinColumn(name="uid_customer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="uid_architect")
	private Architect architect;
	
	@ManyToOne
	@JoinColumn(name="uid_project_type")
	private ProjectType type;
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	
	public Project() {
		this.status = ProjectStatusEnum.AGUARDANDO_INICIO;
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public Project(String name, String description, Customer customer, Architect architect, ProjectType type) {
		this();
		this.name = name;
		this.description = description;
		this.customer = customer;
		this.architect = architect;
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ProjectStatusEnum getStatus() {
		return status;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Architect getArchitect() {
		return architect;
	}
	
	public ProjectType getType() {
		return type;
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}
}
