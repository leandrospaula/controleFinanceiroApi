package com.controleFinanceiro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.Cartao;
import com.controleFinanceiro.repository.CartaoRepository;
import com.controleFinanceiro.service.CartaoService;

@Service
public class CartaoServiceImpl implements CartaoService {
	
	@Autowired
	CartaoRepository repo;

	@Override
	public Optional<Cartao> porId(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Cartao> porUsuario(Long usuarioId) {
		return repo.findByUsuarioId(usuarioId);
	}

	@Override
	public Cartao save(Cartao c) {
		return repo.save(c);
	}

}
