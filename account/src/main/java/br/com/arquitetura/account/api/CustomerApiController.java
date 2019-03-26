package br.com.arquitetura.account.api;

import java.security.Principal;

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

import br.com.arquitetura.account.data.CustomerData;
import br.com.arquitetura.account.security.data.UserDataAuth;
import br.com.arquitetura.account.service.CustomerService;

@RestController
@RequestMapping(path="/customer")
public class CustomerApiController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	private ResponseEntity<Long> saveCustomer(@RequestBody @Valid CustomerData customerData, Principal userLogged) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerData, UserDataAuth.getUserDataAuth(userLogged)));
	}
	
	@GetMapping(path="/{uidCustomer}")
	public ResponseEntity<CustomerData> getCustomer(@PathVariable("uidCustomer") Long uidCustomer){
		return ResponseEntity.ok(customerService.findByUid(uidCustomer));
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerData customerData){
		customerService.update(customerData);
		return ResponseEntity.ok().build();
	}
	
}
