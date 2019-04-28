package br.com.arquitetura.project.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	@PatchMapping()
	public ResponseEntity<Void> updateStep(@RequestBody @Valid StepData stepData){
		stepService.update(stepData);
		return ResponseEntity.ok().build();
	}
	
}
