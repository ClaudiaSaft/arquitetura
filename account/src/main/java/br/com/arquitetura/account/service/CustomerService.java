package br.com.arquitetura.account.service;

import br.com.arquitetura.account.data.CustomerData;

public interface CustomerService {

	Long save(CustomerData customerData);

	CustomerData findByUid(Long uidCustomer);

	void update(CustomerData customerData);

}
