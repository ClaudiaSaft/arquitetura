package br.com.arquitetura.account.data;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class EmailAnexo {

	private String nomeAnexo;
	private Resource anexoResource;
	
	public EmailAnexo(String nomeAnexo, byte[] anexo) {
		this.nomeAnexo = nomeAnexo;
		this.anexoResource = anexo == null ? null : new ByteArrayResource(anexo);
	}

	public EmailAnexo(String nomeAnexo, Resource anexoResource) {
		this.nomeAnexo = nomeAnexo;
		this.anexoResource = anexoResource;
	}
	
	//GETTERS AND SETTERS
	public String getNomeAnexo() {
		return nomeAnexo;
	}
	public Resource getAnexoResource() {
		return anexoResource;
	}

}
