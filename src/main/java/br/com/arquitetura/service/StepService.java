package br.com.arquitetura.service;

import java.util.List;

import br.com.arquitetura.data.StepData;

public interface StepService {

	List<StepData> findAll();

	Long save(StepData stepData);

	void update(StepData stepData);

}
