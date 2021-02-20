package com.controleFinanceiro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.Despesa;
import com.controleFinanceiro.repository.DespesaRepository;
import com.controleFinanceiro.service.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService {

	@Autowired
	DespesaRepository repo;

	@Override
	public void deletar(Despesa d) {
		repo.delete(d);
	}

	@Override
	public Optional<Despesa> porId(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Despesa> porMesId(Long mesId) {
		return repo.findByMesId(mesId);
	}

	@Override
	public Despesa save(Despesa d) {
		return repo.save(d);
	}

}
