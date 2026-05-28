package com.desafio.financeiro.domain.validation;

public class ValidadorDocumento {

	private ValidadorDocumento() {
		
    }

    public static boolean cpfValido(String cpf) {

        cpf = removerMascara(cpf);

        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;

        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int digito1 = calcularDigito(soma);

        soma = 0;

        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        
        int digito2 = calcularDigito(soma);

        return digito1 == (cpf.charAt(9) - '0')
                && digito2 == (cpf.charAt(10) - '0');
    }
    
    public static boolean cnpjValido(String cnpj) {

        cnpj = removerMascara(cnpj);

        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] peso1 = {
                5,4,3,2,9,8,7,6,5,4,3,2
        };

        int[] peso2 = {
                6,5,4,3,2,9,8,7,6,5,4,3,2
        };

        int soma = 0;

        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso1[i];
        }

        int digito1 = calcularDigito(soma);
        
        soma = 0;

        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso2[i];
        }

        int digito2 = calcularDigito(soma);

        return digito1 == (cnpj.charAt(12) - '0')
                && digito2 == (cnpj.charAt(13) - '0');
    }

    private static int calcularDigito(int soma) {

        int resto = soma % 11;

        return resto < 2
                ? 0
                : 11 - resto;
    }
    
    public static String removerMascara(String valor) {

    	if (valor != null)
    		return valor.replaceAll("\\D", "");
    	
    	return null;
    }
}
