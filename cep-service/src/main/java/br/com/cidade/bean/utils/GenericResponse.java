package br.com.cidade.bean.utils;

public class GenericResponse<T> {
	private T content;
	private String message;
	
	public GenericResponse(T t){
		this.setContent(t);
	}
	public GenericResponse(T t,String message){
		this.setContent(t);
		this.setMessage(message);
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
