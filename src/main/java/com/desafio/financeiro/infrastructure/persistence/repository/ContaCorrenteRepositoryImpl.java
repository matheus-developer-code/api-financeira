package com.desafio.financeiro.infrastructure.persistence.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio.financeiro.domain.model.ContaCorrente;
import com.desafio.financeiro.domain.repository.ContaCorrenteRepository;
import com.desafio.financeiro.infrastructure.persistence.entity.ContaCorrenteEntity;
import com.desafio.financeiro.infrastructure.persistence.mapper.ContaCorrenteMapper;

public class ContaCorrenteRepositoryImpl implements ContaCorrenteRepository {

	private final JpaContaCorrenteRepository jpaRepository;
    private final ContaCorrenteMapper mapper;

    public ContaCorrenteRepositoryImpl(JpaContaCorrenteRepository jpaRepository, ContaCorrenteMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
	
	@Override
	public ContaCorrente criar(ContaCorrente contaCorrente) {
		ContaCorrenteEntity entity = mapper.toEntity(contaCorrente);
		entity = jpaRepository.save(entity);
        return mapper.toDomain(entity);
	}

	@Override
	public boolean exists(Long numero, Integer digitoVerificador) {
		return jpaRepository.existsByNumeroAndDigitoVerificador(numero, digitoVerificador);
	}

	@Override
	public ContaCorrente consultarPorNumeroEDigitoVerificador(Long numero, Integer digitoVerificador) {
		ContaCorrenteEntity entity = jpaRepository.findByNumeroAndDigitoVerificador(numero, digitoVerificador);
		return mapper.toDomain(entity);
	}

	@Override
	public boolean creditar(Long idConta, BigDecimal valor) {
		return jpaRepository.creditar(idConta, valor) == 1;
	}
	
	@Override
	public boolean debitar(Long idConta, BigDecimal valor) {
		return jpaRepository.debitar(idConta, valor) == 1;
	}

	@Override
	public BigDecimal consultarExtratoMesAtual(Long idConta) {
		return jpaRepository.consultarSaldoAtual(idConta);
	}

	@Override
	public BigDecimal consultarExtratoOutroMes(Long idConta, LocalDateTime dtFim) {
		return jpaRepository.consultarSaldoOutroMes(idConta, dtFim);
	}
}
