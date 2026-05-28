package com.desafio.financeiro.application.dto;

public record ContaCorrenteRequest(
        Long numero,
        Integer digitoVerificador,
        String cpf,
        String cnpj) {
	
	public ContaCorrenteRequest {
	}
}
