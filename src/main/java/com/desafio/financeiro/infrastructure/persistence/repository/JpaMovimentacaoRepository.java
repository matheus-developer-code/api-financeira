package com.desafio.financeiro.infrastructure.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafio.financeiro.infrastructure.persistence.entity.MovimentacaoEntity;
import com.desafio.financeiro.infrastructure.persistence.projection.ExtratoItemProjection;

public interface JpaMovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Long> {
	
     @Query(
 		    value = """
 		        SELECT id, 
				    CASE WHEN (conta_destino = :idConta and tipo in (0, 2)) THEN 'C' 
					    WHEN (conta_origem = :idConta and tipo in (1, 2)) THEN 'D'
				    END as natureza,
				    data_hora as dataHora,
				    valor,
				    tipo
				 FROM movimentacao
				     WHERE (conta_origem = :idConta 
				           OR conta_destino = :idConta)
				       AND data_hora >= :dtInicio
				       AND data_hora < :dtFim
				     ORDER BY data_hora
 		    """,
 		    nativeQuery = true
 		)
	List<ExtratoItemProjection> consultarMovimentacoesPorMesAno(Long idConta, 
			  											   @Param("dtInicio") LocalDateTime dtInicio,
			  											   @Param("dtFim") LocalDateTime dtFim);
}
