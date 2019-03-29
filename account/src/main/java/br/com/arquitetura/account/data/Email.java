package br.com.arquitetura.account.data;

import java.util.ArrayList;
import java.util.List;

public class Email {

	private String destinatario;
	private String assunto;
	private String corpo;
	private List<EmailAnexo> anexos;
	private boolean isCorpoHtml;
	
	public Email(String destinatario, String assunto, String corpo, boolean isCorpoHtml) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.corpo = corpo;
		this.isCorpoHtml = isCorpoHtml;
		this.anexos = new ArrayList<>();
	}
	
	public void addAnexo(EmailAnexo emailAnexo) {
		anexos.add(emailAnexo);
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	public String getAssunto() {
		return assunto;
	}
	public String getCorpo() {
		return corpo;
	}
	public List<EmailAnexo> getAnexos() {
		return anexos;
	}
	public boolean isCorpoHtml() {
		return isCorpoHtml;
	}

}
