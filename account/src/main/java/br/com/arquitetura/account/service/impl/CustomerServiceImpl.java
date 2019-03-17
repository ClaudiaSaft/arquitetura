package br.com.arquitetura.account.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.account.converter.CustomerConverter;
import br.com.arquitetura.account.data.AddressData;
import br.com.arquitetura.account.data.CustomerData;
import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.Customer;
import br.com.arquitetura.account.exception.CustomerNotFoundException;
import br.com.arquitetura.account.repository.CustomerRepository;
import br.com.arquitetura.account.service.AddressService;
import br.com.arquitetura.account.service.CustomerService;
import br.com.arquitetura.account.service.UserService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	
	
	@Override
	public Long save(@Valid CustomerData customerData) {
		customerData.validateCreate();
		
		UserData userSaved = userService.save(customerData.getUser());
		AddressData addressSaved = saveAddressToDatabase(customerData);
		
		Customer customer = CustomerConverter.convertToCustomer(customerData, userSaved, addressSaved);
		
		Customer customerSaved = customerRepository.save(customer);
		return customerSaved.getUid();
	}

	@Override
	public void update(@Valid CustomerData customerData) {
		customerData.validateUpdate();
		
		Customer customerDataBase = getCustomerById(customerData.getUid());
		
		AddressData addressData = customerData.getAddress();
		if(customerData.getAddress() != null && customerData.getAddress().getUid() == null) {
			addressData = saveAddressToDatabase(customerData);
		}
		
		Customer customer = CustomerConverter.convertToCustomer(customerDataBase, customerData, addressData);
		
		customerRepository.save(customer);
	}

	private AddressData saveAddressToDatabase(CustomerData customerData) {
		customerData.getAddress().validateCreate();
		return addressService.save(customerData.getAddress());
	}
	
	@Override
	public CustomerData findByUid(Long uidCustomer) {
		Customer customer = getCustomerById(uidCustomer);
		return CustomerConverter.convertToCustomerData(customer);
	}

	private Customer getCustomerById(Long uidCustomer) {
		Optional<Customer> customerOptional = customerRepository.findById(uidCustomer);
		if(customerOptional.isPresent()) {
			return customerOptional.get();
		} else {
			throw new CustomerNotFoundException();
		}
	}

}
