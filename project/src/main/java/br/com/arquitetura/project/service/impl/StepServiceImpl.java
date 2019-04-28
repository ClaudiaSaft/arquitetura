package br.com.arquitetura.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.project.converter.StepConverter;
import br.com.arquitetura.project.data.StepData;
import br.com.arquitetura.project.entity.Step;
import br.com.arquitetura.project.exception.StepNotFoundException;
import br.com.arquitetura.project.repository.StepRepository;
import br.com.arquitetura.project.service.StepService;

@Service
public class StepServiceImpl implements StepService {
	
	@Autowired
	private StepRepository stepRepository;
	
	@Override
	public void update(StepData stepData) {
		stepData.validateUpdate();
		Step stepDataBase = getUserById(stepData.getUid());
		Step step = StepConverter.convertToStep(stepDataBase, stepData);
		stepRepository.save(step);
	}
	
	private Step getUserById(Long uidStep) {
		Optional<Step> stepOptional = stepRepository.findById(uidStep);
		if(stepOptional.isPresent()) {
			return stepOptional.get();
		} else {
			throw new StepNotFoundException();
		}
	}


}
