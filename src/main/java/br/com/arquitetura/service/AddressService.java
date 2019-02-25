package br.com.arquitetura.service;

import br.com.arquitetura.data.AddressData;
import br.com.arquitetura.entity.Address;

public interface AddressService {

	Address save(AddressData addressData);

}
