package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;

public record TransferenciaRequest(
		BigDecimal valor,
        Long numeroContaOrigem,
        Integer digitoVerificadorContaOrigem,
        Long numeroContaDestino,
        Integer digitoVerificadorContaDestino) {
}
