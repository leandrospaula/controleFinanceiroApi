package com.controleFinanceiro.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.Usuario;
import com.controleFinanceiro.repository.UsuarioRepository;
import com.controleFinanceiro.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository repo;

	@Override
	public Optional<Usuario> porEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public Optional<Usuario> porId(Long id) {
		return repo.findById(id);
	}

	@Override
	public Usuario save(Usuario u) {
		return repo.save(u);
	}

}
