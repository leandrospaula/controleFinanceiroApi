package com.controleFinanceiro.service;

import java.util.List;
import java.util.Optional;

import com.controleFinanceiro.entity.Cartao;

public interface CartaoService {
	
	Optional<Cartao> porId(Long id);
	
	List<Cartao> porUsuario(Long usuarioId);
	
	Cartao save(Cartao c);

}
