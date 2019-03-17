package br.com.arquitetura.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquitetura.account.converter.AddressConverter;
import br.com.arquitetura.account.data.AddressData;
import br.com.arquitetura.account.entity.Address;
import br.com.arquitetura.account.repository.AddressRepository;
import br.com.arquitetura.account.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public AddressData save(AddressData addressData) {
		Address address = AddressConverter.convertToAddress(addressData);
		Address addressSaved = addressRepository.save(address);
		return AddressConverter.convertToAddressData(addressSaved);
	}

}
