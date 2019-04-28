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

@Entity
@Table(name="project_type")
public class ProjectType {

	@Id
	@Column(name="uid_project_type")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="nm_project_type")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="uid_project_type_owner")
	private ProjectType projectTypeOwner;
	
	@OneToMany(mappedBy="projectTypeOwner", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ProjectType> projectSubTypes;
	
	@Column(name="dt_create")
	private LocalDateTime create;

	@Column(name="dt_update")
	private LocalDateTime update;
	
	public ProjectType() {
		this.create = LocalDateTime.now(ZoneId.of("Z"));
	}
	
	public ProjectType(Long uidProjectType) {
		this();
		this.uid = uidProjectType;
	}

	public ProjectType(Long uid, String name) {
		this(uid);
		this.name = name;
	}

	public Long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public ProjectType getProjectTypeOwner() {
		return projectTypeOwner;
	}
	
	public List<ProjectType> getProjectSubTypes() {
		if(projectSubTypes == null) {
			return new ArrayList<>();
		}
		return Collections.unmodifiableList(projectSubTypes);
	}

	public void addProjectSubType(ProjectType projectType) {
		if(projectSubTypes == null) {
			projectSubTypes = new ArrayList<>();
		}
		projectSubTypes.add(projectType);
		projectType.projectTypeOwner = this;
	}

	@PreUpdate
	public void preUpdate() {
		this.update = LocalDateTime.now(ZoneId.of("Z"));
	}

	@Override
	public String toString() {
		return "ProjectType [uid=" + uid + ", name=" + name + ", projectTypeOwner=" + projectTypeOwner
				+ ", projectSubTypes=" + projectSubTypes + ", create=" + create + ", update=" + update + "]";
	}
	
}
