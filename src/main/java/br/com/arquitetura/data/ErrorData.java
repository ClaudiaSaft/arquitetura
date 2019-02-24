package br.com.arquitetura.data;

public class ErrorData {

	private String code;
	private String name;
	private String message;
	
	public ErrorData(String code, String name, String message) {
		this.code = code;
		this.name = name;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getMessage() {
		return message;
	}
	
}
