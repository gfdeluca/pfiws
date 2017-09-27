package ar.com.uade.pfi.dao.dbo.entities;

public class AppConfigurationEntity {
	String clave;
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
