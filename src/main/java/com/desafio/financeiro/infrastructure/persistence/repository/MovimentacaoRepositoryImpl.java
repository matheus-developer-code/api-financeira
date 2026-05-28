package com.desafio.financeiro.infrastructure.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio.financeiro.domain.model.Movimentacao;
import com.desafio.financeiro.domain.model.valueObject.ExtratoContaItem;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.entity.MovimentacaoEntity;
import com.desafio.financeiro.infrastructure.persistence.mapper.MovimentacaoMapper;
import com.desafio.financeiro.infrastructure.persistence.projection.ExtratoItemProjection;

public class MovimentacaoRepositoryImpl implements MovimentacaoRepository {

	private final JpaMovimentacaoRepository jpaRepository;
    private final MovimentacaoMapper mapper;

    public MovimentacaoRepositoryImpl(JpaMovimentacaoRepository jpaRepository, MovimentacaoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

	@Override
	public Movimentacao salvar(Movimentacao movimentacao) {
		MovimentacaoEntity entity = mapper.toEntity(movimentacao);
		entity = jpaRepository.save(entity);
        return mapper.toDomain(entity);
	}

	@Override
	public List<ExtratoContaItem> consultarMovimentacoesPorPeriodo(Long idConta, LocalDateTime dtInicio, LocalDateTime dtFim) {
		List<ExtratoItemProjection> extratoItens = jpaRepository.consultarMovimentacoesPorMesAno(idConta, dtInicio, dtFim);
		return mapper.toExtratoItens(extratoItens);
	}
}
