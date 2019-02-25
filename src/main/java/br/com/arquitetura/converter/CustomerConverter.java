package br.com.arquitetura.converter;

import br.com.arquitetura.data.AddressData;
import br.com.arquitetura.data.CustomerData;
import br.com.arquitetura.data.UserData;
import br.com.arquitetura.entity.Customer;
import br.com.arquitetura.entity.User;

public class CustomerConverter {

	public static Customer convertToCustomer(CustomerData customerData) {
		Customer customer = new Customer();
		customer.setComercialPhone(customerData.getComercialPhone());
		customer.setPersonalPhone(customerData.getPersonalPhone());
		customer.setWhatsapp(customerData.getWhatsapp());
		return customer;
	}

	public static CustomerData convertToCustomerData(Customer customer) {
		UserData userData = UserConverter.convertToUserData(customer.getUser());
		AddressData addressData = AddressConverter.convertToAddressData(customer.getAddress());
		
		return new CustomerData(customer.getUid(), userData, addressData, customer.getWhatsapp(), customer.getPersonalPhone(), customer.getComercialPhone());
	}

	public static Customer convertToCustomer(Customer customer, CustomerData customerData) {
		User user = UserConverter.convertToUser(customer.getUser(), customerData.getUser());
		customer.setUser(user);
		
		customer.setComercialPhone(customerData.getComercialPhone());
		customer.setPersonalPhone(customerData.getPersonalPhone());
		customer.setWhatsapp(customerData.getWhatsapp());
		return customer;
	}

}
