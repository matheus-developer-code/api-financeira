package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

public record ExtratoItemResponse(
		Long id,
		String natureza,
		BigDecimal valor,
		LocalDateTime dataHora,
		TipoMovimentacao tipo
		) {
}
