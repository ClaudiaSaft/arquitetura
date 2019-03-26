package br.com.arquitetura.account.service;

import java.util.List;

import br.com.arquitetura.account.data.CustomerData;
import br.com.arquitetura.account.security.data.UserDataAuth;

public interface CustomerService {

	Long save(CustomerData customerData, UserDataAuth userDataAuth);

	CustomerData findByUid(Long uidCustomer);

	void update(CustomerData customerData);

	List<CustomerData> getCustomersByUidUserArchitect(Long uidUserArchitect);

}
