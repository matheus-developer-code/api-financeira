package com.desafio.financeiro.application.dto;

public record ExtratoRequest(
		Long numero,
		Integer digitoVerificador,
		Integer mes,
		Integer ano
		) {
	
	public ExtratoRequest {
		
	}
}
