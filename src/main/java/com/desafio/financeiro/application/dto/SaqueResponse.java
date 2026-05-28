package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaqueResponse(
		Long id,
		ContaCorrenteResponse contaDestino,
		BigDecimal valor,
		LocalDateTime dataHora
		) {
}
