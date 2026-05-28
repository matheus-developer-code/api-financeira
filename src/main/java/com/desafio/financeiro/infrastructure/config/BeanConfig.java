package com.desafio.financeiro.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafio.financeiro.application.service.ContaCorrenteService;
import com.desafio.financeiro.application.service.DepositoService;
import com.desafio.financeiro.application.service.SaqueService;
import com.desafio.financeiro.application.service.TransferenciaService;
import com.desafio.financeiro.domain.repository.ContaCorrenteRepository;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.mapper.ContaCorrenteMapper;
import com.desafio.financeiro.infrastructure.persistence.mapper.MovimentacaoMapper;
import com.desafio.financeiro.infrastructure.persistence.repository.ContaCorrenteRepositoryImpl;
import com.desafio.financeiro.infrastructure.persistence.repository.JpaContaCorrenteRepository;
import com.desafio.financeiro.infrastructure.persistence.repository.JpaMovimentacaoRepository;
import com.desafio.financeiro.infrastructure.persistence.repository.MovimentacaoRepositoryImpl;

@Configuration
public class BeanConfig {

	@Bean
    ContaCorrenteRepository contaCorrenteRepository(JpaContaCorrenteRepository jpa, ContaCorrenteMapper mapper) {
        return new ContaCorrenteRepositoryImpl(jpa, mapper);
    }
	
	@Bean
    MovimentacaoRepository MovimentacaoRepository(JpaMovimentacaoRepository jpa, MovimentacaoMapper mapper) {
        return new MovimentacaoRepositoryImpl(jpa, mapper);
    }

    @Bean
    ContaCorrenteService contaCorrenteService(ContaCorrenteRepository contaCorrenteRepository, MovimentacaoRepository movimentacaoRepository, ContaCorrenteMapper mapper) {
        return new ContaCorrenteService(contaCorrenteRepository, movimentacaoRepository, mapper);
    }
    
    @Bean
    DepositoService depositoService(MovimentacaoRepository movimentacaoRepository, ContaCorrenteRepository contaCorrenteRepository, MovimentacaoMapper mapper) {
        return new DepositoService(movimentacaoRepository, contaCorrenteRepository, mapper);
    }
    
    @Bean
    SaqueService saqueService(MovimentacaoRepository movimentacaoRepository, ContaCorrenteRepository contaCorrenteRepository, MovimentacaoMapper mapper) {
        return new SaqueService(movimentacaoRepository, contaCorrenteRepository, mapper);
    }
    
    @Bean
    TransferenciaService transferenciaService(MovimentacaoRepository movimentacaoRepository, ContaCorrenteRepository contaCorrenteRepository, MovimentacaoMapper mapper) {
        return new TransferenciaService(movimentacaoRepository, contaCorrenteRepository, mapper);
    }
}
