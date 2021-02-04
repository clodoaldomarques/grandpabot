package br.com.cgms.grandpabot.api.model;

import javax.validation.constraints.NotBlank;

public class Mensagem {
	
	String numero;
	
	@NotBlank
	String mensagem;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
