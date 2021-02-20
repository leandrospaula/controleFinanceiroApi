package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CartaoDTO {
	
	private Long id;
	@Length(message = "Máximo de caracteres para o nome de 100 caracteres", max = 100)
	@NotNull(message = "Nome identificador não pode ser salvo em branco")
	private String nome;
	private boolean ativo;
	private Integer fechamento;
	private Integer vencimento;
	@NotNull(message = "Um cartão deve estar vinculado a um usuário")
	private UsuarioDTO usuario;

}
