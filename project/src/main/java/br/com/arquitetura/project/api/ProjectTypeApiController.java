package br.com.arquitetura.project.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.project.data.ProjectTypeData;
import br.com.arquitetura.project.service.ProjectTypeService;

@RestController
@RequestMapping(path="/project-type")
public class ProjectTypeApiController {

	@Autowired
	private ProjectTypeService projectTypeService;
	
	@GetMapping
	public ResponseEntity<List<ProjectTypeData>> getProjectTypes(){
		return ResponseEntity.ok(projectTypeService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Long> saveProjectType(@RequestBody @Valid ProjectTypeData projectTypeData) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectTypeService.save(projectTypeData));
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateProjectType(@RequestBody @Valid ProjectTypeData projectTypeData){
		projectTypeService.update(projectTypeData);
		return ResponseEntity.ok().build();
	}
	
}
