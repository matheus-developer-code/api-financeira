package com.desafio.financeiro.infrastructure.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.application.dto.ContaCorrenteRequest;
import com.desafio.financeiro.application.dto.ContaCorrenteResponse;
import com.desafio.financeiro.application.dto.ExtratoRequest;
import com.desafio.financeiro.application.dto.ExtratoResponse;
import com.desafio.financeiro.application.service.ContaCorrenteService;

@RestController
@RequestMapping("/contas-correntes")
public class ContaCorrenteController {

	private final ContaCorrenteService contaCorrenteService;

    public ContaCorrenteController(ContaCorrenteService contaCorrenteService) {
        this.contaCorrenteService = contaCorrenteService;
    }

    @PostMapping
    public ContaCorrenteResponse criar(@RequestBody ContaCorrenteRequest request) {
        return contaCorrenteService.criar(request);
    }
    
    @GetMapping("/{numeroConta}/extrato")
    public ExtratoResponse consultarExtrato(
            @PathVariable Long numeroConta,
            @RequestParam(value = "digito_verificador", required = true) Integer digitoVerificador,
            @RequestParam(value = "mes", required = true) Integer mes,
            @RequestParam(value = "ano", required = true) Integer ano) {
    	
    	return contaCorrenteService.consultarExtrato(new ExtratoRequest(numeroConta, digitoVerificador, mes, ano));
    }
}
