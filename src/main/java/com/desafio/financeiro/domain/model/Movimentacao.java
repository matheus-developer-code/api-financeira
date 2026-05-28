package com.desafio.financeiro.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

import lombok.Getter;

@Getter
public sealed abstract class Movimentacao permits Deposito, Saque, Transferencia {

	private Long id;
	private TipoMovimentacao tipo;
	private LocalDateTime dataHora;
	private BigDecimal valor;
	
	protected Movimentacao(Long id, TipoMovimentacao tipo, BigDecimal valor) {
		super();
		
		validarValorPositivo(valor);		
		this.id = id;
		this.tipo = tipo;
		this.dataHora = LocalDateTime.now();
		this.valor = valor;
	}
	
	private static void validarValorPositivo(BigDecimal valor) {
    	
    	if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
    		throw new BusinessException(TipoException.MOVIMENTACAO_VALOR_MAIOR_ZERO);
    }
}
