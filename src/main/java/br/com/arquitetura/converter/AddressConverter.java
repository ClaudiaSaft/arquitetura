package br.com.arquitetura.converter;

import br.com.arquitetura.data.AddressData;
import br.com.arquitetura.entity.Address;

public class AddressConverter {

	public static Address convertToAddress(AddressData addressData) {
		return new Address(addressData.getName(), addressData.getNeighborhood(), addressData.getComplement(), addressData.getNumber());
	}

	public static AddressData convertToAddressData(Address address) {
		if(address == null) {
			return new AddressData();
		}
		return new AddressData(address.getUid(), address.getName(), address.getNeighborhood(), address.getComplement(), address.getNumber());
	}

	public static Address convertToAddress(Address address, AddressData addressData) {
		if(addressData == null) {
			return null;
		}
		address.setName(addressData.getName());
		address.setNeighborhood(addressData.getNeighborhood());
		address.setComplement(addressData.getComplement());
		address.setNumber(addressData.getNumber());
		return address;
	}

}
