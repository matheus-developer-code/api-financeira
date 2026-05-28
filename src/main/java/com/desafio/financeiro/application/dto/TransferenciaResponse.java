package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaResponse(
		Long id,
		ContaCorrenteResponse contaOrigem,
		ContaCorrenteResponse contaDestino,
		BigDecimal valor,
		LocalDateTime dataHora
		) {
}
