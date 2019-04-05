package br.com.arquitetura.account.api;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

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

import br.com.arquitetura.account.data.ArchitectData;
import br.com.arquitetura.account.data.CustomerData;
import br.com.arquitetura.account.security.data.UserDataAuth;
import br.com.arquitetura.account.service.ArchitectService;
import br.com.arquitetura.account.service.CustomerService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path="/architect")
public class ArchitectApiController {

	@Autowired
	private ArchitectService architectService;
	@Autowired
	private CustomerService customerService;
	
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
	
	@GetMapping(path="/customers")
	public ResponseEntity<List<CustomerData>> getCustomersByArchitect(Principal userLogged){
		return ResponseEntity.ok(customerService.getCustomersByUidUserArchitect(UserDataAuth.getUserDataAuth(userLogged).getUid()));
	}

	@GetMapping(path="/customers/send-report")
	public ResponseEntity<List<CustomerData>> getReportCustomersByArchitect(Principal userLogged) throws IOException, JRException{
		architectService.generateReportCustomersByArchitect(UserDataAuth.getUserDataAuth(userLogged).getUid());
		return ResponseEntity.ok().build();
	}
}
