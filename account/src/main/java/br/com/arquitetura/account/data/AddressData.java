package br.com.arquitetura.account.data;

import br.com.arquitetura.account.exception.AddressFieldRequiredException;

public class AddressData extends ValidationFiedsData{

	private Long uid;
	private String name;
	private String neighborhood;
	private String complement;
	private Integer number;
	
	public AddressData() {
	}
	
	public AddressData(Long uid, String name, String neighborhood, String complement, Integer number) {
		this.uid = uid;
		this.name = name;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.number = number;
	}
	
	public Long getUid() {
		return uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Override
	protected void validateRequiredFieldsUpdate() {
		validaRequiredFields();
	}
	
	@Override
	protected void validateRequiredUid() {
		if(uid == null) {
			throw new AddressFieldRequiredException("uid");
		}
	}

	@Override
	protected void validateRequiredFieldsCreate() {
		validaRequiredFields();
	}

	private void validaRequiredFields() {
		if(name == null) {
			throw new AddressFieldRequiredException("name");
		}
		if(neighborhood == null) {
			throw new AddressFieldRequiredException("neighborhood");
		}
		if(complement == null) {
			throw new AddressFieldRequiredException("complement");
		}
		if(number == null) {
			throw new AddressFieldRequiredException("number");
		}
	}
}
