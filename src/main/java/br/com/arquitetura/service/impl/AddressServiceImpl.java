package br.com.arquitetura.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.converter.AddressConverter;
import br.com.arquitetura.data.AddressData;
import br.com.arquitetura.entity.Address;
import br.com.arquitetura.repository.AddressRepository;
import br.com.arquitetura.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Address save(AddressData addressData) {
		Address address = AddressConverter.convertToAddress(addressData);
		return addressRepository.save(address);
	}

}
