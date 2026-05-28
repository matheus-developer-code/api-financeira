package com.desafio.financeiro.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.desafio.financeiro.application.dto.TransferenciaRequest;
import com.desafio.financeiro.application.dto.TransferenciaResponse;
import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.ContaCorrente;
import com.desafio.financeiro.domain.model.Transferencia;
import com.desafio.financeiro.domain.repository.ContaCorrenteRepository;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.mapper.MovimentacaoMapper;

public class TransferenciaService {

	private final MovimentacaoRepository movimentacaoRepository;
	private final ContaCorrenteRepository contaCorrenteRepository;
	private final MovimentacaoMapper mapper;
	
	public TransferenciaService(MovimentacaoRepository movimentacaoRepository, 
			               ContaCorrenteRepository contaCorrenteRepository, 
			               MovimentacaoMapper mapper) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.contaCorrenteRepository = contaCorrenteRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	public TransferenciaResponse salvar(TransferenciaRequest request) {
		
		ContaCorrente contaOrigem = contaCorrenteRepository.consultarPorNumeroEDigitoVerificador(request.numeroContaOrigem(), request.digitoVerificadorContaOrigem());		
		ContaCorrente contaDestino = contaCorrenteRepository.consultarPorNumeroEDigitoVerificador(request.numeroContaDestino(), request.digitoVerificadorContaDestino());		
		
		Transferencia transferencia = new Transferencia(null, request.valor(), contaOrigem, contaDestino);	
		
		boolean debitou = contaCorrenteRepository.debitar(contaOrigem.getId(), transferencia.getValor());
		
		if (!debitou) 
			throw new BusinessException(TipoException.CONTA_NAO_DEBITOU);
		
		boolean creditou = contaCorrenteRepository.creditar(contaDestino.getId(), transferencia.getValor());
		
		if (!creditou) 
			throw new BusinessException(TipoException.CONTA_NAO_CREDITOU);
					
		transferencia = (Transferencia) movimentacaoRepository.salvar(transferencia);		

		return mapper.toResponseTransferencia(transferencia);
	}
}
