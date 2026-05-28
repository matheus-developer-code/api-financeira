package com.desafio.financeiro.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import org.springframework.transaction.annotation.Transactional;

import com.desafio.financeiro.application.dto.ContaCorrenteRequest;
import com.desafio.financeiro.application.dto.ContaCorrenteResponse;
import com.desafio.financeiro.application.dto.ExtratoRequest;
import com.desafio.financeiro.application.dto.ExtratoResponse;
import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.model.ContaCorrente;
import com.desafio.financeiro.domain.model.valueObject.ExtratoConta;
import com.desafio.financeiro.domain.repository.ContaCorrenteRepository;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.mapper.ContaCorrenteMapper;

public class ContaCorrenteService {

	private final ContaCorrenteRepository contaCorrenteRepository;
	private final MovimentacaoRepository movimentacaoRepository;
	private final ContaCorrenteMapper mapper;
	
	public ContaCorrenteService(ContaCorrenteRepository contaCorrenteRepository, MovimentacaoRepository movimentacaoRepository, ContaCorrenteMapper mapper) {
		this.contaCorrenteRepository = contaCorrenteRepository;
		this.movimentacaoRepository = movimentacaoRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	public ContaCorrenteResponse criar(ContaCorrenteRequest request) {
		
		ContaCorrente contaCorrente = ContaCorrente.builder()
				                                   .numero(request.numero())
				                                   .digitoVerificador(request.digitoVerificador())
				                                   .cpf(request.cpf())
				                                   .cnpj(request.cnpj())
				                                   .build();
		
		if (contaCorrenteRepository.exists(contaCorrente.getNumero(), contaCorrente.getDigitoVerificador())) 
			throw new BusinessException(TipoException.CONTA_EXISTENTE);		

		return mapper.toResponse(contaCorrenteRepository.criar(contaCorrente));
	}
	
	@Transactional(readOnly = true)
	public ExtratoResponse consultarExtrato(ExtratoRequest request) {
		
		validarMesAno(request.mes(), request.ano());
		ContaCorrente contaCorrente = contaCorrenteRepository.consultarPorNumeroEDigitoVerificador(request.numero(), request.digitoVerificador());
		
		if (contaCorrente == null)
    		throw new BusinessException(TipoException.EXTRATO_CONTA_NAO_ENCONTRADA);
		
        LocalDateTime dtInicio = LocalDate.of(request.ano(), request.mes(), 1).atStartOfDay();
        LocalDateTime dtFim = dtInicio.plusMonths(1); 
		
     	BigDecimal saldo = null;
     	
     	if (isMesAtual(request.mes(), request.ano()))
     		saldo = contaCorrenteRepository.consultarExtratoMesAtual(contaCorrente.getId());
     	else 
     		saldo = contaCorrenteRepository.consultarExtratoOutroMes(contaCorrente.getId(), dtFim);
     		
     	if (saldo == null)
     		throw new BusinessException(TipoException.CONTA_EXTRATO_ERRO);
        
     	ExtratoConta extratoConta = ExtratoConta.builder()
     	            .numero(request.numero())
     	            .digitoVerificador(request.digitoVerificador())
     	            .saldo(saldo)
     	            .itens(movimentacaoRepository.consultarMovimentacoesPorPeriodo(contaCorrente.getId(), dtInicio, dtFim))
     	            .build();

		return mapper.toExtratoResponse(extratoConta);
	}
	
	private void validarMesAno(int mes, int ano) {
		
		if (YearMonth.of(ano, mes).isAfter(YearMonth.now())) {
            throw new BusinessException(TipoException.EXTRATO_MES_ANO_FUTUROS);
        }
	}
	
	private boolean isMesAtual(int mes, int ano) {
	    YearMonth dataInformada = YearMonth.of(ano, mes);
	    YearMonth mesAnoAtual = YearMonth.now();

	    return dataInformada.equals(mesAnoAtual);
	}
}
