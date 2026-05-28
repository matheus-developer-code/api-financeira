package com.desafio.financeiro.domain.model;

import java.math.BigDecimal;

import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

import lombok.Getter;

@Getter
public final class Saque extends Movimentacao {

	private final ContaCorrente contaOrigem;
	
	public Saque(Long id, BigDecimal valor, ContaCorrente contaOrigem) {
		super(id, TipoMovimentacao.SAQUE, valor);		
		validarContaOrigem(contaOrigem);
		this.contaOrigem = contaOrigem;
	}

	private static void validarContaOrigem(ContaCorrente contaOrigem) {
    	
    	if (contaOrigem == null)
    		throw new BusinessException(TipoException.MOVIMENTACAO_CONTA_ORIGEM_NAO_INFORMADA);
    }
}
