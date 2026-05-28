package com.desafio.financeiro.application.dto;

import java.math.BigDecimal;
import java.util.List;

public record ExtratoResponse(
		Long numero,
		Integer digitoVerificador,
		BigDecimal saldo,
		List<ExtratoItemResponse> itens
		) {
}
