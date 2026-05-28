package com.desafio.financeiro.domain.model;

import java.math.BigDecimal;

import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

import lombok.Getter;

@Getter
public final class Transferencia extends Movimentacao {

	private final ContaCorrente contaOrigem;
	private final ContaCorrente contaDestino;
	
	public Transferencia(Long id, BigDecimal valor, ContaCorrente contaOrigem, ContaCorrente contaDestino) {
		super(id, TipoMovimentacao.SAQUE, valor);		
		validarContasTransferencia(contaOrigem, contaDestino);
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}
	
	private static void validarContasTransferencia(ContaCorrente contaOrigem, ContaCorrente contaDestino) {
    	
		if (contaOrigem == null)
    		throw new BusinessException(TipoException.MOVIMENTACAO_CONTA_ORIGEM_NAO_INFORMADA);
    	
		if (contaDestino == null)
    		throw new BusinessException(TipoException.MOVIMENTACAO_CONTA_DESTINO_NAO_INFORMADA);
		
    	if (contaOrigem.getNumero().equals(contaDestino.getNumero()) && contaOrigem.getDigitoVerificador().equals(contaDestino.getDigitoVerificador()))
    		throw new BusinessException(TipoException.MOVIMENTACAO_CONTAS_IGUAIS);
    }
}
