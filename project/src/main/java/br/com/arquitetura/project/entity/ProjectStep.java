package br.com.arquitetura.project.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.arquitetura.project.enumeration.StepStatusEnum;

@Entity
@Table(name="project_step")
public class ProjectStep {

	@Id
	@Column(name="uid_project_step")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@ManyToOne
	@JoinColumn(name="uid_project")
	private Project project;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="cd_status")
	private StepStatusEnum status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="uid_step", insertable=true, updatable=true)
	private Step step;
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	
	public ProjectStep() {
		this.status = StepStatusEnum.AGUARDANDO_INICIO;
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public ProjectStep(Project project, Step step) {
		this();
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

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}

	@Override
	public String toString() {
		return "ProjectStep [uid=" + uid + ", project=" + project + ", status=" + status + ", step=" + step
				+ ", create=" + create + ", update=" + update + "]";
	}

}
