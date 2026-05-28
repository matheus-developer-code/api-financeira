package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;

public record SaqueRequest(
		BigDecimal valor,
        Long numero,
        Integer digitoVerificador) {
}
