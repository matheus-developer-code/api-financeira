package com.desafio.financeiro.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio.financeiro.domain.model.ContaCorrente;

public interface ContaCorrenteRepository {

	ContaCorrente criar(ContaCorrente contaCorrente);
	
	boolean exists(Long numero, Integer digitoVerificador);
	
	ContaCorrente consultarPorNumeroEDigitoVerificador(Long numero, Integer digitoVerificador);
	
	boolean creditar(Long idConta, BigDecimal valor);
	
	boolean debitar(Long idConta, BigDecimal valor);
	
	BigDecimal consultarExtratoMesAtual(Long idConta);
	
	BigDecimal consultarExtratoOutroMes(Long idConta, LocalDateTime dtFim);
}
