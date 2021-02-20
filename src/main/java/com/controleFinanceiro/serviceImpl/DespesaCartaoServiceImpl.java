package com.controleFinanceiro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.DespesaCartao;
import com.controleFinanceiro.repository.DespesaCartaoRepository;
import com.controleFinanceiro.service.DespesaCartaoService;

@Service
public class DespesaCartaoServiceImpl implements DespesaCartaoService {

	@Autowired
	DespesaCartaoRepository repo;

	@Override
	public void deletar(DespesaCartao d) {
		repo.delete(d);
	}

	@Override
	public List<DespesaCartao> porCartaoId(Long cartaoId) {
		return repo.findByCartaoId(cartaoId);
	}

	@Override
	public Optional<DespesaCartao> porId(Long id) {
		return repo.findById(id);
	}

	@Override
	public DespesaCartao save(DespesaCartao d) {
		return repo.save(d);
	}

	@Override
	public List<DespesaCartao> porMesId(Long mes) {
		return repo.findByMesIdOrderByTerceirosAscNomeAsc(mes);
	}

}
