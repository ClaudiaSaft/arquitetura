package br.com.arquitetura.service;

import br.com.arquitetura.data.CustomerData;

public interface CustomerService {

	Long save(CustomerData customerData);

	CustomerData findByUid(Long uidCustomer);

	void update(CustomerData customerData);

}
