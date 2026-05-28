package com.desafio.financeiro.domain.model;

import java.math.BigDecimal;

import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

import lombok.Getter;

@Getter
public final class Deposito extends Movimentacao {

	private final ContaCorrente contaDestino;
	
	public Deposito(Long id, BigDecimal valor, ContaCorrente contaDestino) {
		super(id, TipoMovimentacao.DEPOSITO, valor);		
		validarContaDestino(contaDestino);
		this.contaDestino = contaDestino;
	}

	private static void validarContaDestino(ContaCorrente contaDestino) {
    	
    	if (contaDestino == null)
    		throw new BusinessException(TipoException.MOVIMENTACAO_CONTA_DESTINO_NAO_INFORMADA);
    }
}
