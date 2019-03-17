package br.com.arquitetura.account.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.account.converter.ArchitectConverter;
import br.com.arquitetura.account.data.ArchitectData;
import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.Architect;
import br.com.arquitetura.account.exception.ArchitectNotFoundException;
import br.com.arquitetura.account.repository.ArchitectRepository;
import br.com.arquitetura.account.service.ArchitectService;
import br.com.arquitetura.account.service.UserService;

@Service
public class ArchitectServiceImpl implements ArchitectService {
	
	@Autowired
	private ArchitectRepository architectRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public Long save(ArchitectData architectData) {
		architectData.validateCreate();
		
		UserData userSaved = userService.save(architectData.getUser());
		
		Architect architect = ArchitectConverter.convertToArchitect(architectData, userSaved);
		
		Architect architectSaved = architectRepository.save(architect);
		return architectSaved.getUid();
	}

	@Override
	public void update(ArchitectData architectData) {
		architectData.validateUpdate();
		
		Architect architectDataBase = getArchitectById(architectData.getUid());
		
		Architect architect = ArchitectConverter.convertToArchitect(architectDataBase, architectData);
		
		architectRepository.save(architect);
	}

	@Override
	public ArchitectData findByUid(Long uidArchitect) {
		Architect architect = getArchitectById(uidArchitect);
		return ArchitectConverter.convertToArchitectData(architect);
	}

	private Architect getArchitectById(Long uidArchitect) {
		Optional<Architect> architectOptional = architectRepository.findById(uidArchitect);
		if(architectOptional.isPresent()) {
			return architectOptional.get();
		} else {
			throw new ArchitectNotFoundException();
		}
	}

}
