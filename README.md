# api-financeira

API REST desenvolvida em Java para o gerenciamento de contas correntes e movimentações financeiras, permitindo criação de contas, realização de depósitos, saques, transferências e consulta de extrato.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- MapStruct
- Lombok 
- Swagger / OpenAPI

## Arquitetura

O projeto foi desenvolvido utilizando Clean Architecture, separando as responsabilidades em camadas:

- Domain: regras de negócio e entidades
- Application: services (controle do fluxo e transações) e dtos
- Infrastructure: controllers REST e persistência utilizando Spring Boot

Também foram aplicados princípios de Clean Code

## Funcionalidades

- Criar conta corrente
- Realizar depósito
- Realizar saque
- Realizar transferência
- Consultar extrato por mês (retornando além do saldo, as respectivas movimentações)

## Documentação da API

A API pode ser testada via Swagger: http://localhost:8080/api-financeira/swagger-ui/index.html

