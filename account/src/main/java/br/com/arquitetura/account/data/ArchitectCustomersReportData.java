package br.com.arquitetura.account.data;

import java.util.ArrayList;
import java.util.List;

public class ArchitectCustomersReportData {
	
	private String name;
	private String email;
	private String whatsapp;
	private List<CustomerReportData> customersReportData = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public List<CustomerReportData> getCustomersReportData() {
		return customersReportData;
	}
	public void setCustomersReportData(List<CustomerReportData> customersReportData) {
		this.customersReportData = customersReportData;
	}
	
}
