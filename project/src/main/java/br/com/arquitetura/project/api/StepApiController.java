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

import br.com.arquitetura.project.data.StepData;
import br.com.arquitetura.project.service.StepService;

@RestController
@RequestMapping(path="/step")
public class StepApiController {

	@Autowired
	private StepService stepService;
	
	@GetMapping
	public ResponseEntity<List<StepData>> getSteps(){
		return ResponseEntity.ok(stepService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Long> saveStep(@RequestBody @Valid StepData stepData) {
		return ResponseEntity.status(HttpStatus.CREATED).body(stepService.save(stepData));
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateStep(@RequestBody @Valid StepData stepData){
		stepService.update(stepData);
		return ResponseEntity.ok().build();
	}
	
}
