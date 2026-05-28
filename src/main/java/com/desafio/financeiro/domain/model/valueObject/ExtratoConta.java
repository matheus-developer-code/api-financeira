package com.desafio.financeiro.domain.model.valueObject;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class ExtratoConta{
	
	private Long numero;
	private Integer digitoVerificador;
	private BigDecimal saldo;
	private List<ExtratoContaItem> itens;
}
