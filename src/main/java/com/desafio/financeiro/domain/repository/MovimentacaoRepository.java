package com.desafio.financeiro.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio.financeiro.domain.model.Movimentacao;
import com.desafio.financeiro.domain.model.valueObject.ExtratoContaItem;

public interface MovimentacaoRepository {

	Movimentacao salvar(Movimentacao movimentacao);
	
	List<ExtratoContaItem> consultarMovimentacoesPorPeriodo(Long idConta, LocalDateTime dtInicio, LocalDateTime dtFim);
}
