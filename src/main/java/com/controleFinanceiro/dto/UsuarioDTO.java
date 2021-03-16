package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	@NotNull(message = "Um usuário deve ter um e-mail ou login de acesso")
	@Length(max = 100, message = "Email pode ter no máximo, 100 caracteres")
	private String email;
	@NotNull(message = "Um usuário deve ter um senha")
	@Length(min = 6, message = "Senha deve conter no mínimo 6 caracteres.")
	private String senha;
	private String nome;

}
