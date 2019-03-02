package br.com.arquitetura.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.data.ArchitectData;
import br.com.arquitetura.service.ArchitectService;

@RestController
@RequestMapping(path="/architect")
public class ArchitectApiController {

	@Autowired
	private ArchitectService architectService;
	
	@PostMapping
	private ResponseEntity<Long> saveArchitect(@RequestBody @Valid ArchitectData architectData) {
		return ResponseEntity.status(HttpStatus.CREATED).body(architectService.save(architectData));
	}
	
	@GetMapping(path="/{uidArchitect}")
	public ResponseEntity<ArchitectData> getArchitect(@PathVariable("uidArchitect") Long uidArchitect){
		return ResponseEntity.ok(architectService.findByUid(uidArchitect));
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateArchitect(@RequestBody @Valid ArchitectData architectData){
		architectService.update(architectData);
		return ResponseEntity.ok().build();
	}
	
}
