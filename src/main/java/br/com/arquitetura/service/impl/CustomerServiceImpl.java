package br.com.arquitetura.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.AddressConverter;
import br.com.arquitetura.converter.CustomerConverter;
import br.com.arquitetura.data.CustomerData;
import br.com.arquitetura.entity.Address;
import br.com.arquitetura.entity.Customer;
import br.com.arquitetura.entity.User;
import br.com.arquitetura.exception.ObjectNotFoundException;
import br.com.arquitetura.repository.CustomerRepository;
import br.com.arquitetura.service.AddressService;
import br.com.arquitetura.service.CustomerService;
import br.com.arquitetura.service.UserService;

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
		
		User user = userService.save(customerData.getUser());
		Address address = saveAddressToDatabase(customerData);
		
		Customer customer = CustomerConverter.convertToCustomer(customerData);
		customer.setUser(user);
		customer.setAddress(address);
		
		Customer customerSaved = customerRepository.save(customer);
		return customerSaved.getUid();
	}

	@Override
	public void update(@Valid CustomerData customerData) {
		customerData.validateUpdate();
		
		Customer customerDataBase = getCustomerById(customerData.getUid());
		
		Address address = customerDataBase.getAddress();
		if(customerData.getAddress() != null && customerData.getAddress().getUid() == null) {
			address = saveAddressToDatabase(customerData);
		} else {
			address = updateAddress(customerData, customerDataBase);
		}
		
		Customer customer = CustomerConverter.convertToCustomer(customerDataBase, customerData);
		customer.setAddress(address);
		
		customerRepository.save(customer);
	}

	private Address updateAddress(CustomerData customerData, Customer customerDataBase) {
		customerData.getAddress().validateUpdate();
		return AddressConverter.convertToAddress(customerDataBase.getAddress(), customerData.getAddress());
	}

	private Address saveAddressToDatabase(CustomerData customerData) {
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
			throw new ObjectNotFoundException("Cliente");
		}
	}

}
