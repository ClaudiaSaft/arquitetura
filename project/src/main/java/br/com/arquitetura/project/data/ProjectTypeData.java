package br.com.arquitetura.project.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectTypeData {

	private Long uid;
	private String name;
	private Long uidProjectTypeOwner;
	private List<ProjectTypeData> projectSubTypes;
	
	public ProjectTypeData() {
	}
	
	public ProjectTypeData(Long uid, String name) {
		this.uid = uid;
		this.name = name;
	}
	
	public Long getUid() {
		return uid;
	}
	public String getName() {
		return name;
	}
	public Long getUidProjectTypeOwner() {
		return uidProjectTypeOwner;
	}
	public List<ProjectTypeData> getProjectSubTypes() {
		if(projectSubTypes == null) {
			return new ArrayList<>();
		}
		return Collections.unmodifiableList(projectSubTypes);
	}
	
	public void addProjectSubType(ProjectTypeData projectTypeData) {
		if(projectSubTypes == null) {
			projectSubTypes = new ArrayList<>();
		}
		projectSubTypes.add(projectTypeData);
		projectTypeData.uidProjectTypeOwner = this.getUid();
	}
	
}
