package br.com.arquitetura.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.data.ProjectData;
import br.com.arquitetura.service.ProjectService;

@RestController
@RequestMapping(path="/project")
public class ProjectApiController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public ResponseEntity<List<ProjectData>> getProjects(){
		return ResponseEntity.ok(projectService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Long> saveProjectType(@RequestBody @Valid ProjectData projectData) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(projectData));
	}
	
}
