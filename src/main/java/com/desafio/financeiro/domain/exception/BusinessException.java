package com.desafio.financeiro.domain.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(TipoException tipo) {
        super(tipo.message());
    }
}
