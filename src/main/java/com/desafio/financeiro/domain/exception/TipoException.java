package com.desafio.financeiro.domain.exception;

public enum TipoException {

	CONTA_CPF_INVALIDO("CPF inválido"),
    CONTA_CNPJ_INVALIDO("CNPJ inválido"),
    CONTA_CPF_E_CNPJ_PREENCHIDOS("Informe apenas o CPF ou CNPJ"),
    CONTA_CPF_E_CNPJ_NAO_PREENCHIDOS("Informe o CPF ou CNPJ"),
    CONTA_NUMERO_OU_DV_NAO_PREENCHIDOS("Informe o número e o digito verificardor da conta corrente"),
    CONTA_DV_INVALIDO("O digito verificador deve ser um valor de 0 a 9"),
    CONTA_EXISTENTE("Conta já cadastrada"),
    CONTA_SALDO_NEGATIVO("A conta não pode ter saldo negativo"),
	CONTA_NAO_CREDITOU("A conta não pode ser creditada"),
	CONTA_NAO_DEBITOU("Não foi possivel debitar, pois a conta não tem saldo suficiente"),
	CONTA_SALDO_INSUFICIENTE("A conta não tem saldo suficiente para realizar o debito"),
	CONTA_EXTRATO_ERRO("Não foi possível verificar o extrato da conta"),
	MOVIMENTACAO_VALOR_MAIOR_ZERO("A movimentação deve ter o valor maior do que zero"),
	MOVIMENTACAO_CONTA_ORIGEM_NAO_INFORMADA("Conta de origem não informada ou não localizada"),
	MOVIMENTACAO_CONTA_DESTINO_NAO_INFORMADA("Conta de destino não informada ou não localizada"),
	MOVIMENTACAO_CONTAS_IGUAIS("As contas de origem e destino devem ser diferentes"),
	EXTRATO_CONTA_NAO_ENCONTRADA("Esta conta não existe"),
	EXTRATO_MES_ANO_FUTUROS("O mês/ano informado é superior ao mês/ano atual");

    private final String message;

    TipoException(String message) {
        this.message = message;
    }
    
    public String message() {
        return message;
    }
}
