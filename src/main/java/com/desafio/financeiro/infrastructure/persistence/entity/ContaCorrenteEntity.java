package com.desafio.financeiro.infrastructure.persistence.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="conta_corrente", 
       uniqueConstraints = {@UniqueConstraint(columnNames = {"numero", "digito_verificador"})})
@Check(
	    constraints = """
	        (
	            cpf IS NOT NULL
	            OR cnpj IS NOT NULL
	        )
	        AND NOT (
	            cpf IS NOT NULL
	            AND cnpj IS NOT NULL
	        )
	        AND (
	            cpf IS NULL
	            OR cpf ~ '^[0-9]{11}$'
	        )
	        AND (
	            cnpj IS NULL
	            OR cnpj ~ '^[0-9]{14}$'
	        )
	    """
	)
@Check(constraints = "saldo >= 0")
@Check(constraints = """
	        NOT (
	            numero = 0
	            AND digito_verificador = 0
	        )
	    """
	)
@Check(constraints = "digito_verificador BETWEEN 0 AND 9")
@Getter
@Setter
@NoArgsConstructor
public class ContaCorrenteEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private Long numero;
    
	@Column(nullable = false)
    private Integer digitoVerificador;
    
	@CPF
	@Column(length = 11)
    private String cpf;
	
	@CNPJ
	@Column(length = 14)
	private String cnpj;
	
	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal saldo;

    public ContaCorrenteEntity(Long id, Long numero, Integer digitoVerificador, String cpf, String cnpj) {
        this.id = id;
        this.numero = numero;
        this.digitoVerificador = digitoVerificador;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }
}
