package br.com.arquitetura.account.data;

public class CustomerReportData {
	
	private String name;
	private String email;
	private String whatsapp;
	
	public CustomerReportData(UserData userData, String whatsapp) {
		this.name = userData.getName();
		this.email = userData.getEmail();
		this.whatsapp = whatsapp;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

}
