package br.com.arquitetura.data;

public abstract class ValidationFiedsData {

	protected abstract void validateRequiredFieldsCreate();

	protected abstract void validateRequiredFieldsUpdate();
	
	protected abstract void validateRequiredUid();

	public void validateCreate() {
		validateRequiredFieldsCreate();
	}
	
	public void validateUpdate() {
		validateRequiredUid();
		validateRequiredFieldsUpdate();
	}

}
