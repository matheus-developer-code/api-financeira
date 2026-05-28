package com.desafio.financeiro.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desafio.financeiro.application.dto.ContaCorrenteResponse;
import com.desafio.financeiro.application.dto.ExtratoResponse;
import com.desafio.financeiro.domain.model.ContaCorrente;
import com.desafio.financeiro.domain.model.valueObject.ExtratoConta;
import com.desafio.financeiro.infrastructure.persistence.entity.ContaCorrenteEntity;

@Mapper(componentModel = "spring")
public interface ContaCorrenteMapper {

	ContaCorrenteEntity toEntity(ContaCorrente contaCorrente);

    ContaCorrente toDomain(ContaCorrenteEntity entity);
    
    @Mapping(target = "documento", expression = "java(formataDocumento(contaCorrente))")
    ContaCorrenteResponse toResponse(ContaCorrente contaCorrente);
    
    default String formataDocumento(ContaCorrente contaCorrente) {
        
        if (contaCorrente.getCpf() != null && contaCorrente.getCpf().length() == 11) {
            return contaCorrente.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        else if (contaCorrente.getCnpj() != null && contaCorrente.getCnpj().length() == 14) {
            return contaCorrente.getCnpj().replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        
        return ""; 
    }
    
    ExtratoResponse toExtratoResponse(ExtratoConta extratoConta);
}
