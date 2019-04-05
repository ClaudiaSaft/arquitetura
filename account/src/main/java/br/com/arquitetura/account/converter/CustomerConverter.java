package br.com.arquitetura.account.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.account.data.AddressData;
import br.com.arquitetura.account.data.ArchitectData;
import br.com.arquitetura.account.data.CustomerData;
import br.com.arquitetura.account.data.CustomerReportData;
import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.Address;
import br.com.arquitetura.account.entity.Architect;
import br.com.arquitetura.account.entity.Customer;
import br.com.arquitetura.account.entity.User;

public class CustomerConverter {

	public static Customer convertToCustomer(CustomerData customerData, UserData userData, AddressData addressData, ArchitectData architectData) {
		Customer customer = new Customer();
		customer.setComercialPhone(customerData.getComercialPhone());
		customer.setPersonalPhone(customerData.getPersonalPhone());
		customer.setWhatsapp(customerData.getWhatsapp());
		
		User user = UserConverter.convertToUser(userData);
		customer.setUser(user);
		
		Address address = AddressConverter.convertToAddress(addressData);
		customer.setAddress(address);
		
		customer.setArchitect(new Architect(architectData.getUid()));
		return customer;
	}

	public static CustomerData convertToCustomerData(Customer customer) {
		UserData userData = UserConverter.convertToUserData(customer.getUser());
		AddressData addressData = AddressConverter.convertToAddressData(customer.getAddress());
		
		return new CustomerData(customer.getUid(), userData, addressData, customer.getWhatsapp(), customer.getPersonalPhone(), customer.getComercialPhone());
	}

	public static Customer convertToCustomer(Customer customer, CustomerData customerData, AddressData addressData) {
		User user = UserConverter.convertToUser(customer.getUser(), customerData.getUser());
		customer.setUser(user);
		
		Address address = updateAddress(customer, addressData);
		customer.setAddress(address);
		
		customer.setComercialPhone(customerData.getComercialPhone());
		customer.setPersonalPhone(customerData.getPersonalPhone());
		customer.setWhatsapp(customerData.getWhatsapp());
		return customer;
	}

	private static Address updateAddress(Customer customer, AddressData addressData) {
		addressData.validateUpdate();
		return AddressConverter.convertToAddress(customer.getAddress(), addressData);
	}

	public static List<CustomerData> convertToCustomerData(List<Customer> customers) {
		List<CustomerData> customersData = new ArrayList<>();
		customers.stream().forEach(c -> customersData.add(convertToCustomerData(c)));
		return customersData;
	}

	public static List<CustomerReportData> convertToCustomerReportData(List<Customer> customers) {
		List<CustomerReportData> customersReportData = new ArrayList<>();
		customers.stream().forEach(c -> customersReportData.add(convertToCustomerReportData(c)));
		return customersReportData;
	}
	
	public static CustomerReportData convertToCustomerReportData(Customer customer) {
		UserData userData = UserConverter.convertToUserData(customer.getUser());
		
		return new CustomerReportData(userData, customer.getWhatsapp());
	}
}
