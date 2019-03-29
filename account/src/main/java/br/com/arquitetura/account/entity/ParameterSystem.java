package br.com.arquitetura.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parameter_system")
public class ParameterSystem {

	@Id
	@Column(name="cd_parameter")
	private String code;
	
	@Column(name="vl_parameter")
	private String value;
	
	public String getCode() {
		return code;
	}
	
	public String getValue() {
		return value;
	}

}
