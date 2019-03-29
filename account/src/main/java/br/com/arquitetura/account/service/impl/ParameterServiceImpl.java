package br.com.arquitetura.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.account.entity.ParameterSystem;
import br.com.arquitetura.account.repository.ParameterRepository;
import br.com.arquitetura.account.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Override
	public String getString(String parameterConstant) {
		ParameterSystem parameter = parameterRepository.findByCode(parameterConstant);
		return parameter.getValue();
	}

}
