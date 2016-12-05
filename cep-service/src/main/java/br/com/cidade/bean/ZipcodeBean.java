package br.com.cidade.bean;

public class ZipcodeBean {
	private String value;
	
	public ZipcodeBean(String value){
		this.value  = value.replace("-", "");
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
