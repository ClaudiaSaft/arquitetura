package br.com.arquitetura.service;

import javax.validation.Valid;

import br.com.arquitetura.data.CustomerData;
import br.com.arquitetura.entity.Customer;

public interface CustomerService {

	Customer save(@Valid CustomerData customerData);

	CustomerData findByUid(Long uidCustomer);

	void update(@Valid CustomerData customerData);

}
