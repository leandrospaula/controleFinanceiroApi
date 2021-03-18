package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class DespesaFixaDTO {

	private Long id;
	@NotNull(message = "Uma despesa fixa deve ter um identificador")
	@Length(max = 100, message = "O identificador da despesa fixa pode ter no máximo, 100 caracteres")
	private String nome;
	private int vencimento;
	@NotNull(message = "Uma despesa fixa deve estar vinculada a um usuário")
	private UsuarioDTO usuario;
	private String tipo;
	private String calculo;
	private boolean ativo;
	private Double valor;
	private Double parte;
}
