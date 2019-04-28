package br.com.arquitetura.project.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="project")
public class Project {

	@Id
	@Column(name="uid_project")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="nm_project")
	private String name;
	
	@Column(name="ds_project")
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
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

	@ManyToOne
	@JoinColumn(name="uid_project_subtype")
	private ProjectType subType;
	
	@Column(name="ind_template")
	private boolean template;
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ProjectStep> projectSteps;
	
	
	public Project() {
	}
	
	public static class Builder {
		private String name;
		private String description;
		private Long uidCustomer;
		private Long uidArchitect;
		private Long uidProjectType;
		private Long uidProjectSubType;
		
		public Builder(String name, Long uidProjectType, Long uidProjectSubType) {
			this.name = name;
			this.uidProjectType = uidProjectType;
			this.uidProjectSubType = uidProjectSubType;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder uidCustomer(Long uidCustomer) {
			this.uidCustomer = uidCustomer;
			return this;
		}
		
		public Builder uidArchitect(Long uidArchitect) {
			this.uidArchitect = uidArchitect;
			return this;
		}

		public Project build() {
			return new Project(name, description, new Customer(uidCustomer), new Architect(uidArchitect), 
					new ProjectType(uidProjectType), new ProjectType(uidProjectSubType));
		}
	}
	
	private Project(String name, String description, Customer customer, 
			Architect architect, ProjectType type, ProjectType subType) {
		this.projectSteps = new ArrayList<>();
		this.status = ProjectStatusEnum.AGUARDANDO_INICIO;
		this.create = LocalDateTime.now(ZoneId.of("Z"));
		this.name = name;
		this.description = description;
		this.customer = customer;
		this.architect = architect;
		this.type = type;
		this.subType = subType;
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
	
	public ProjectType getSubType() {
		return subType;
	}

	public List<ProjectStep> getProjectSteps() {
		return Collections.unmodifiableList(this.projectSteps);
	}
	
	public void addAllStepProjects(List<ProjectStep> projectSteps) {
		initSteps();
		this.projectSteps.addAll(projectSteps);
		setProjectInProjectSteps(projectSteps);
	}

	private void setProjectInProjectSteps(List<ProjectStep> projectSteps) {
		projectSteps.forEach(p -> p.setProject(this));
	}

	private void initSteps() {
		if (this.projectSteps == null) {
			this.projectSteps = new ArrayList<>();
		}
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}

}
