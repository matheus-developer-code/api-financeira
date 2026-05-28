package com.desafio.financeiro.infrastructure.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.application.dto.DepositoRequest;
import com.desafio.financeiro.application.dto.DepositoResponse;
import com.desafio.financeiro.application.dto.SaqueRequest;
import com.desafio.financeiro.application.dto.SaqueResponse;
import com.desafio.financeiro.application.dto.TransferenciaRequest;
import com.desafio.financeiro.application.dto.TransferenciaResponse;
import com.desafio.financeiro.application.service.DepositoService;
import com.desafio.financeiro.application.service.SaqueService;
import com.desafio.financeiro.application.service.TransferenciaService;

@RestController
@RequestMapping
public class MovimentacaoController {

	private final DepositoService depositoService;
	private final SaqueService saqueService;
	private final TransferenciaService transferenciaService;

    public MovimentacaoController(DepositoService depositoService, SaqueService saqueService, TransferenciaService transferenciaService) {
        this.depositoService = depositoService;
        this.saqueService = saqueService;
        this.transferenciaService = transferenciaService;
    }

    @PostMapping("/depositos")
    public DepositoResponse depositar(@RequestBody DepositoRequest request) {
        return depositoService.salvar(request);
    }
    
    @PostMapping("/saques")
    public SaqueResponse sacar(@RequestBody SaqueRequest request) {
        return saqueService.salvar(request);
    }
    
    @PostMapping("/transferencias")
    public TransferenciaResponse transferir(@RequestBody TransferenciaRequest request) {
        return transferenciaService.salvar(request);
    }
}
