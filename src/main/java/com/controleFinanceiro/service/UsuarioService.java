package com.controleFinanceiro.service;

import java.util.Optional;

import com.controleFinanceiro.entity.Usuario;

public interface UsuarioService {
	
	Optional<Usuario> porEmail(String email);
	
	Optional<Usuario> porId(Long id);
	
	Usuario save(Usuario u);

}
