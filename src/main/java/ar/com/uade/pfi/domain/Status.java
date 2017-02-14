package ar.com.uade.pfi.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {
	
	int res;
	String message;
	
	public int getRes() {
		return res;
	}
	
	public void setRes(int res) {
		this.res = res;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
