package ar.com.uade.pfi.dao.dbo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AppConfiguration")
public class AppConfigurationEntity {
	@Id
	@Column(name = "clave")
	String clave;

	@Column(name = "valor")
	String valor;

	public AppConfigurationEntity(String id, String valor) {
		this.clave = id;
		this.valor = valor;
	}

	public AppConfigurationEntity() {
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
