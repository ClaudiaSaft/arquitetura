package br.com.arquitetura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.StepConverter;
import br.com.arquitetura.data.StepData;
import br.com.arquitetura.entity.Step;
import br.com.arquitetura.exception.ObjectNotFoundException;
import br.com.arquitetura.repository.StepRepository;
import br.com.arquitetura.service.StepService;

@Service
public class StepServiceImpl implements StepService {
	
	@Autowired
	private StepRepository stepRepository;
	
	@Override
	public List<StepData> findAll() {
		List<Step> steps = stepRepository.findAll();
		return StepConverter.convertToStepData(steps);
	}
	
	@Override
	public Long save(StepData stepData) {
		stepData.validateCreate();
		Step step = StepConverter.convertToStep(stepData);
		Step stepSaved = stepRepository.save(step);
		return stepSaved.getUid();
	}

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
			throw new ObjectNotFoundException("Etapa");
		}
	}


}
