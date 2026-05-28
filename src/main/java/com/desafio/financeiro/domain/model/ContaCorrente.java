package com.desafio.financeiro.domain.model;

import java.math.BigDecimal;

import com.desafio.financeiro.domain.exception.BusinessException;
import com.desafio.financeiro.domain.exception.TipoException;
import com.desafio.financeiro.domain.validation.ValidadorDocumento;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContaCorrente {

	private Long id;
	private Long numero;
	private Integer digitoVerificador;
	private String cpf;
	private String cnpj;
	private BigDecimal saldo;
	
	public ContaCorrente(Long id, Long numero, Integer digitoVerificador, String cpf, String cnpj, BigDecimal saldo) {
		super();
		
		validarNumeroEDigitoVerificador(numero, digitoVerificador);
		validarPreenchimentoCpfECnpj(cpf, cnpj);
		validarDocumento(cpf, cnpj);
		validarSaldo(saldo);		
		
		this.id = id;
		this.numero = numero;
		this.digitoVerificador = digitoVerificador;
		this.cpf = ValidadorDocumento.removerMascara(cpf);
		this.cnpj = ValidadorDocumento.removerMascara(cnpj);
		
		if (id == null)
			this.saldo = BigDecimal.ZERO;
		else 
			this.saldo = saldo;
	}
	
	private void validarSaldo(BigDecimal saldo) { 
		
		if (saldo != null && saldo.compareTo(BigDecimal.ZERO) < 0) 
			throw new BusinessException(TipoException.CONTA_SALDO_NEGATIVO);
	}
	
	private void validarPreenchimentoCpfECnpj(String cpf, String cnpj) {
		
		if (cpf == null && cnpj == null)
			throw new BusinessException(TipoException.CONTA_CPF_E_CNPJ_NAO_PREENCHIDOS);
		
		if (cpf != null && cnpj != null)
			throw new BusinessException(TipoException.CONTA_CPF_E_CNPJ_PREENCHIDOS);
		
		if ((cpf != null && cpf.isEmpty()) || (cnpj != null && cnpj.isEmpty()))
			throw new BusinessException(TipoException.CONTA_CPF_E_CNPJ_NAO_PREENCHIDOS);
	}
	
	private void validarDocumento(String cpf, String cnpj) {
		
		if (cpf != null)
			validarCpf(cpf);
		else
			validarCnpj(cnpj);
	}
	
	private void validarCpf(String cpf) {
		
		cpf = ValidadorDocumento.removerMascara(cpf);
		
		if (cpf.isEmpty())
        	throw new BusinessException(TipoException.CONTA_CPF_E_CNPJ_NAO_PREENCHIDOS); 
		else if (cpf.length() == 11) {

            if (!ValidadorDocumento.cpfValido(cpf)) 
                throw new BusinessException(TipoException.CONTA_CPF_INVALIDO);
        }
        else
        	throw new BusinessException(TipoException.CONTA_CPF_INVALIDO);
    }
	
	private void validarCnpj(String cnpj) {
		
		cnpj = ValidadorDocumento.removerMascara(cnpj);
		
		if (cnpj.isEmpty())
        	throw new BusinessException(TipoException.CONTA_CPF_E_CNPJ_NAO_PREENCHIDOS); 
        else if (cnpj.length() == 14) {

            if (!ValidadorDocumento.cnpjValido(cnpj)) 
                throw new BusinessException(TipoException.CONTA_CNPJ_INVALIDO);
        }
        else
        	throw new BusinessException(TipoException.CONTA_CNPJ_INVALIDO);
    }
	
	private void validarNumeroEDigitoVerificador(Long numero, Integer digitoVerificador) {
		
		if (numero == null || digitoVerificador == null)
			throw new BusinessException(TipoException.CONTA_NUMERO_OU_DV_NAO_PREENCHIDOS); 
		
		if (numero.equals(0L) && digitoVerificador.equals(0))
			throw new BusinessException(TipoException.CONTA_NUMERO_OU_DV_NAO_PREENCHIDOS); 
		
		if (digitoVerificador < 0 || digitoVerificador > 9)
			throw new BusinessException(TipoException.CONTA_DV_INVALIDO);
	}
}
