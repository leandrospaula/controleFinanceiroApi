package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	@NotNull(message = "Um usuário deve ter um e-mail ou login")
	@Length(max = 100, message = "Email pode ter no máximo, 100 caracteres")
	private String email;
	@NotNull(message = "Um usuário deve ter um senha")
	private String senha;
	private String nome;

}
