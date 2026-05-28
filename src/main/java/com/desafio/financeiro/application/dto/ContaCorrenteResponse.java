package com.desafio.financeiro.application.dto;

public record ContaCorrenteResponse(
		Long id,
		Long numero,
        Integer digitoVerificador,
        String documento) {
}
