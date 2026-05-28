package com.desafio.financeiro.domain.model.enums;

public enum TipoMovimentacao {
	DEPOSITO,
	SAQUE,
	TRANSFERENCIA;
	
	public static TipoMovimentacao fromId(Short id) {
        if (id == null) return null;
        for (TipoMovimentacao t : values()) {
            if (t.ordinal() == id) return t;
        }
        throw new IllegalArgumentException("Código inválido: " + id);
    }
}
