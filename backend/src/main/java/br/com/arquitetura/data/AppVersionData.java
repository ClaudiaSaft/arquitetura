package br.com.arquitetura.data;

public class AppVersionData {

	private String version;
	
	public AppVersionData(String buildVersion) {
		this.version = buildVersion;
	}

	public String getVersion() {
		return version;
	}
}
