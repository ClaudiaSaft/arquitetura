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

import br.com.arquitetura.project.enumeration.StepStatusEnum;

@Entity
@Table(name="step")
public class Step {

	@Id
	@Column(name="uid_step")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="ds_step")
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="cd_status")
	private StepStatusEnum status;
	
	@ManyToOne
	@JoinColumn(name="uid_step_owner")
	private Step stepOwner;
	
	@OneToMany(mappedBy="stepOwner", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Step> subSteps;
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	
	public Step() {
		this.status = StepStatusEnum.AGUARDANDO_INICIO;
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public Step(String description) {
		this();
		this.description = description;
	}

	public Long getUid() {
		return uid;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public StepStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(StepStatusEnum status) {
		this.status = status;
	}
	
	public Step getStepOwner() {
		return stepOwner;
	}
	
	public List<Step> getSubSteps() {
		if(subSteps == null) {
			return new ArrayList<>();
		}
		return Collections.unmodifiableList(subSteps);
	}

	public void addSubProjectStep(Step step) {
		if(subSteps == null) {
			subSteps = new ArrayList<>();
		}
		subSteps.add(step);
		step.stepOwner = this;
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}

	@Override
	public String toString() {
		return "Step [uid=" + uid + ", description=" + description + ", status=" + status + ", stepOwner=" + stepOwner
				+ ", subSteps=" + subSteps + ", create=" + create + ", update=" + update + "]";
	}
	
}
