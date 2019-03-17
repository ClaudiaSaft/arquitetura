package br.com.arquitetura.project.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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

import br.com.arquitetura.project.enumeration.StepStatusEnum;

@Entity
@Table(name="project_steps")
public class ProjectStep {

	@Id
	@Column(name="uid_project_step")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@ManyToOne
	@JoinColumn(name="uid_project")
	private Project project;

	@ManyToOne
	@JoinColumn(name="uid_step")
	private Step step;
	
	@Column(name="cd_status")
	private StepStatusEnum status;
	
	@ManyToOne
	@JoinColumn(name="uid_project_step_owner")
	private ProjectStep projectStepOwner;
	
	@OneToMany(mappedBy="projectStepOwner", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ProjectStep> subProjectSteps = new ArrayList<>();
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	
	public ProjectStep() {
		this.status = StepStatusEnum.AGUARDANDO_INICIO;
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public ProjectStep(Long uid, Project project, Step step) {
		this();
		this.uid = uid;
		this.project = project;
		this.step = step;
	}

	public Long getUid() {
		return uid;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public StepStatusEnum getStatus() {
		return status;
	}

	public void setStatus(StepStatusEnum status) {
		this.status = status;
	}

	public ProjectStep getProjectStepOwner() {
		return projectStepOwner;
	}

	public List<ProjectStep> getSubProjectSteps() {
		return subProjectSteps;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}

	public void addSubProjectStep(ProjectStep projectStep) {
		subProjectSteps.add(projectStep);
		projectStep.projectStepOwner = this;
	}
}
