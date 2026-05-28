package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;

public record DepositoRequest(
		BigDecimal valor,
        Long numero,
        Integer digitoVerificador) {
}
