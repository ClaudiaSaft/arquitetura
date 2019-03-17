package br.com.arquitetura.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.data.AppVersionData;

@RestController
@RequestMapping(path="/app")
public class AppApiController {
	
	@Value("${build.version}")
	private String buildVersion;

	@GetMapping(path="/version")
	public ResponseEntity<AppVersionData> getVersion(){
		return ResponseEntity.ok(new AppVersionData(buildVersion));
	}
}
