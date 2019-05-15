package br.com.arquitetura.project.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.project.data.ProjectData;
import br.com.arquitetura.project.service.ProjectService;

@RestController
@RequestMapping(path="/project")
public class ProjectApiController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping(path="/{uidProject}")
	public ResponseEntity<ProjectData> getProjectByUid(@PathVariable("uidProject") Long uidProject){
		return ResponseEntity.ok(projectService.findByUid(uidProject));
	}

	@GetMapping
	public ResponseEntity<List<ProjectData>> getProjects(){
		return ResponseEntity.ok(projectService.findAll());
	}

	@PostMapping
	public ResponseEntity<Long> saveProject(@RequestBody @Valid ProjectData projectData) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(projectData));
	}
	
	@PatchMapping
	public ResponseEntity<Void> updateProject(@RequestBody @Valid ProjectData projectData) {
		projectService.update(projectData);
		return ResponseEntity.ok().build();
	}
}
