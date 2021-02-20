package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MesDTO {

	private Long id;
	private int mes;
	private int ano;
	@NotNull(message = "Um mês deve estar vinculado a um usuário")
	private UsuarioDTO usuario;
	private boolean encerrado;
	@NotNull(message = "Um mês deve ter um valor valido de salário")
	private Double salario;
	private Double economia;
	private Double livre;
	private Double totalGasto;
	private Double totalFixo;
	private Double totalCartao;
}
