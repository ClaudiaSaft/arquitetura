package br.com.arquitetura.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.ArchitectConverter;
import br.com.arquitetura.data.ArchitectData;
import br.com.arquitetura.entity.Architect;
import br.com.arquitetura.entity.User;
import br.com.arquitetura.exception.ObjectNotFoundException;
import br.com.arquitetura.repository.ArchitectRepository;
import br.com.arquitetura.service.ArchitectService;
import br.com.arquitetura.service.UserService;

@Service
public class ArchitectServiceImpl implements ArchitectService {
	
	@Autowired
	private ArchitectRepository architectRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public Long save(@Valid ArchitectData architectData) {
		architectData.validateCreate();
		
		User user = userService.save(architectData.getUser());
		
		Architect architect = ArchitectConverter.convertToArchitect(architectData);
		architect.setUser(user);
		
		Architect architectSaved = architectRepository.save(architect);
		return architectSaved.getUid();
	}

	@Override
	public void update(@Valid ArchitectData architectData) {
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
			throw new ObjectNotFoundException("Arquiteto");
		}
	}

}
