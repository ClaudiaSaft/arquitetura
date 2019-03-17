package br.com.arquitetura.account.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.service.UserService;

@RestController
@RequestMapping(path="/user")
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserData>> getUsers(){
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping(path="/{uidUser}")
	public ResponseEntity<UserData> getUser(@PathVariable("uidUser") Long uidUser){
		return ResponseEntity.ok(userService.findByUid(uidUser));
	}
	
	@PatchMapping(path="/inactivate/{uidUser}")
	public ResponseEntity<Void> inactivateUser(@PathVariable("uidUser") Long uidUser){
		userService.inactivate(uidUser);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping(path="/activate/{uidUser}")
	public ResponseEntity<Void> activateUser(@PathVariable("uidUser") Long uidUser){
		userService.activate(uidUser);
		return ResponseEntity.ok().build();
	}

	@PatchMapping(path="/password/{uidUser}")
	public ResponseEntity<Void> changePassword(@PathVariable("uidUser") Long uidUser, @RequestBody UserData userData){
		userService.changePassword(uidUser, userData);
		return ResponseEntity.ok().build();
	}
}
