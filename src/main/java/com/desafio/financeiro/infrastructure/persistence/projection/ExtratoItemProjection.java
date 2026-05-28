package com.desafio.financeiro.infrastructure.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExtratoItemProjection {

	Long getId();
	String getNatureza();
	BigDecimal getValor();
	LocalDateTime getDataHora();
	Number getTipo();
}
