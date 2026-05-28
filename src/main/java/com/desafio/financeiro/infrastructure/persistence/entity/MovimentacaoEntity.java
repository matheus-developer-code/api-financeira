package com.desafio.financeiro.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Check;

import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="movimentacao",
	   indexes = {
			   @Index(columnList = "conta_origem"),
			   @Index(columnList = "conta_destino"),
			   @Index(columnList = "data_hora")
       })
@Check(
	    constraints = """			
	        NOT (
	            tipo = 0
	            AND conta_destino IS NULL
	        )
	        AND NOT (
	            tipo = 1
	            AND conta_origem IS NULL
	        )
	        AND NOT (
	            tipo = 2 
	            AND (conta_origem IS NULL
	            OR conta_destino IS NULL
	            OR conta_origem = conta_destino)
	        )
	    """
	)
@Check(constraints = "valor >= 0")
@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Enumerated
	@Column(nullable = false)
	private TipoMovimentacao tipo;
	
	@Column(nullable = false)
	private LocalDateTime dataHora;
	
	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal valor;
    
	@ManyToOne
	@JoinColumn(name = "conta_origem")
	private ContaCorrenteEntity contaOrigem; 
	
	@ManyToOne
	@JoinColumn(name = "conta_destino")
	private ContaCorrenteEntity contaDestino; 
}
