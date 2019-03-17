package br.com.arquitetura.project.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.arquitetura.account.entity.Architect;
import br.com.arquitetura.account.entity.Customer;
import br.com.arquitetura.project.enumeration.ProjectStatusEnum;

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
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ProjectStep> steps;
	
	
	public Project() {
		this.steps = new ArrayList<>();
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

	public Project(Long uidProject) {
		this.uid = uidProject;
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

	public List<ProjectStep> getSteps() {
		return Collections.unmodifiableList(this.steps);
	}
	
	public void addAllSteps(List<ProjectStep> projectSteps) {
		initSteps();
		steps.addAll(projectSteps);
		setProjectInProjectSteps(projectSteps);
	}

	private void setProjectInProjectSteps(List<ProjectStep> projectSteps) {
		projectSteps.forEach(p -> {
			p.setProject(this);
		});
	}

	private void initSteps() {
		if (steps == null) {
			steps = new ArrayList<>();
		}
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}

}
