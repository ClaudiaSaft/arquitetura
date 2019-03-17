package br.com.arquitetura.project.service;

import java.util.List;

import br.com.arquitetura.project.data.StepData;

public interface StepService {

	List<StepData> findAll();

	Long save(StepData stepData);

	void update(StepData stepData);

}
