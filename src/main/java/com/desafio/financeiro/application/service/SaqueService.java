package com.desafio.financeiro.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.desafio.financeiro.application.dto.DepositoRequest;
import com.desafio.financeiro.application.dto.DepositoResponse;
import com.desafio.financeiro.application.dto.SaqueRequest;
import com.desafio.financeiro.application.dto.SaqueResponse;
import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.ContaCorrente;
import com.desafio.financeiro.domain.model.Deposito;
import com.desafio.financeiro.domain.model.Saque;
import com.desafio.financeiro.domain.repository.ContaCorrenteRepository;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.mapper.MovimentacaoMapper;

public class SaqueService {

	private final MovimentacaoRepository movimentacaoRepository;
	private final ContaCorrenteRepository contaCorrenteRepository;
	private final MovimentacaoMapper mapper;
	
	public SaqueService(MovimentacaoRepository movimentacaoRepository, 
			               ContaCorrenteRepository contaCorrenteRepository, 
			               MovimentacaoMapper mapper) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.contaCorrenteRepository = contaCorrenteRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	public SaqueResponse salvar(SaqueRequest request) {
		
		ContaCorrente contaCorrente = contaCorrenteRepository.consultarPorNumeroEDigitoVerificador(request.numero(), request.digitoVerificador());		
		Saque saque = new Saque(null, request.valor(), contaCorrente);		
		boolean debitou = contaCorrenteRepository.debitar(contaCorrente.getId(), saque.getValor());
		
		if (debitou)
			saque = (Saque) movimentacaoRepository.salvar(saque);
		else
			throw new BusinessException(TipoException.CONTA_NAO_DEBITOU);
		

		return mapper.toResponseSaque(saque);
	}
}
