package com.desafio.financeiro.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.desafio.financeiro.application.dto.DepositoRequest;
import com.desafio.financeiro.application.dto.DepositoResponse;
import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.ContaCorrente;
import com.desafio.financeiro.domain.model.Deposito;
import com.desafio.financeiro.domain.repository.ContaCorrenteRepository;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.mapper.MovimentacaoMapper;

public class DepositoService {

	private final MovimentacaoRepository movimentacaoRepository;
	private final ContaCorrenteRepository contaCorrenteRepository;
	private final MovimentacaoMapper mapper;
	
	public DepositoService(MovimentacaoRepository movimentacaoRepository, 
			               ContaCorrenteRepository contaCorrenteRepository, 
			               MovimentacaoMapper mapper) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.contaCorrenteRepository = contaCorrenteRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	public DepositoResponse salvar(DepositoRequest request) {
		
		ContaCorrente contaCorrente = contaCorrenteRepository.consultarPorNumeroEDigitoVerificador(request.numero(), request.digitoVerificador());
		Deposito deposito = new Deposito(null, request.valor(), contaCorrente);
		
		boolean creditou = contaCorrenteRepository.creditar(contaCorrente.getId(), deposito.getValor());
		
		if (creditou)
			deposito = (Deposito) movimentacaoRepository.salvar(deposito);
		else
			throw new BusinessException(TipoException.CONTA_NAO_CREDITOU);
		

		return mapper.toResponseDeposito(deposito);
	}
}
