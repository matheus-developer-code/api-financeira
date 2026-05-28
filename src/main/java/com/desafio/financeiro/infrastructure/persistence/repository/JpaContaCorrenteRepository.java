package com.desafio.financeiro.infrastructure.persistence.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafio.financeiro.infrastructure.persistence.entity.ContaCorrenteEntity;

public interface JpaContaCorrenteRepository extends JpaRepository<ContaCorrenteEntity, Long> {

	@Query(
		    value = """
		        select exists(
		            select 1
		            from conta_corrente where numero = :numero and digito_verificador = :digitoVerificador
		        )
		    """,
		    nativeQuery = true
		)
	boolean existsByNumeroAndDigitoVerificador(@Param("numero") Long numero, @Param("digitoVerificador") Integer digitoVerificador);
	
	ContaCorrenteEntity findByNumeroAndDigitoVerificador(Long numero, Integer digitoVerificador);
	
	@Modifying
    @Query(value = "UPDATE conta_corrente SET saldo = saldo + :valor WHERE id = :id", nativeQuery = true)
    int creditar(@Param("id") Long id, @Param("valor") BigDecimal valor);
	
	@Modifying
    @Query(value = "UPDATE conta_corrente SET saldo = saldo - :valor WHERE id = :id and saldo >= :valor", nativeQuery = true)
    int debitar(@Param("id") Long id, @Param("valor") BigDecimal valor);
	
	@Query(
		    value = "select COALESCE(saldo, 0) as saldo from conta_corrente where id = :id",
		    nativeQuery = true
		)
	BigDecimal consultarSaldoAtual(@Param("id") Long id);
	
	@Query(
 		    value = """
 		    		SELECT 
 						COALESCE(
 					            SUM(CASE WHEN (conta_destino = :id and tipo in (0, 2)) THEN valor
 					                     WHEN (conta_origem = :id and tipo in (1, 2)) THEN -valor 
 					                     ELSE 0 END), 
 					            0) as saldo
 					 FROM movimentacao
 					     WHERE (conta_origem = :id 
 						   OR conta_destino = :id)
 					       AND data_hora < :dtFim
 		    		""",
 		    nativeQuery = true
 		)
 	BigDecimal consultarSaldoOutroMes(@Param("id") Long id, 
 									  @Param("dtFim") LocalDateTime dtFim);
}
