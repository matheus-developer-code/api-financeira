package com.desafio.financeiro.domain.model.valueObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

public record ExtratoContaItem(
		Long id,
		String natureza,
		BigDecimal valor,
		LocalDateTime dataHora,
		TipoMovimentacao tipo
		) {
}
