package com.desafio.financeiro.infrastructure.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import com.desafio.financeiro.application.dto.DepositoResponse;
import com.desafio.financeiro.application.dto.SaqueResponse;
import com.desafio.financeiro.application.dto.TransferenciaResponse;
import com.desafio.financeiro.domain.model.Deposito;
import com.desafio.financeiro.domain.model.Movimentacao;
import com.desafio.financeiro.domain.model.Saque;
import com.desafio.financeiro.domain.model.Transferencia;
import com.desafio.financeiro.domain.model.enums.TipoMovimentacao;
import com.desafio.financeiro.domain.model.valueObject.ExtratoContaItem;
import com.desafio.financeiro.infrastructure.persistence.entity.MovimentacaoEntity;
import com.desafio.financeiro.infrastructure.persistence.projection.ExtratoItemProjection;

@Mapper(componentModel = "spring")

public interface MovimentacaoMapper {

	@SubclassMapping(source = Deposito.class, target = MovimentacaoEntity.class)
    @SubclassMapping(source = Saque.class, target = MovimentacaoEntity.class)
    @SubclassMapping(source = Transferencia.class, target = MovimentacaoEntity.class)
    MovimentacaoEntity toEntity(Movimentacao dominio);

    @Mapping(target = "tipo", constant = "DEPOSITO")
    MovimentacaoEntity map(Deposito source);

    @Mapping(target = "tipo", constant = "SAQUE")
    MovimentacaoEntity map(Saque source);

    @Mapping(target = "tipo", constant = "TRANSFERENCIA")
    MovimentacaoEntity map(Transferencia source);

    default Movimentacao toDomain(MovimentacaoEntity entity) {
        if (entity == null || entity.getTipo() == null) {
            return null;
        }

        TipoMovimentacao tipo = entity.getTipo();

        switch(tipo) {
        	case DEPOSITO: return toDeposito(entity);
        	case SAQUE: return toSaque(entity);
        	case TRANSFERENCIA: return toTransferencia(entity);
        }
		return null;
    }

    Deposito toDeposito(MovimentacaoEntity entity);
    Saque toSaque(MovimentacaoEntity entity);
    Transferencia toTransferencia(MovimentacaoEntity entity);
    
    DepositoResponse toResponseDeposito(Deposito deposito);  
    SaqueResponse toResponseSaque(Saque saque); 
    TransferenciaResponse toResponseTransferencia(Transferencia saque);
    
    @Mapping(source = "tipo", target = "tipo")
    List<ExtratoContaItem> toExtratoItens(List<ExtratoItemProjection> itens);
    
    default TipoMovimentacao mapNumberToEnum(Number value) {
        if (value == null) return null;
        return TipoMovimentacao.fromId(value.shortValue()); 
    }
}
