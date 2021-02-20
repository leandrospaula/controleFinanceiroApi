package com.controleFinanceiro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.DespesaFixaMes;
import com.controleFinanceiro.repository.DespesaFixaMesRepository;
import com.controleFinanceiro.service.DespesaFixaMesService;

@Service
public class DespesaFixaMesServiceImpl implements DespesaFixaMesService {

	@Autowired
	DespesaFixaMesRepository repo;

	@Override
	public List<DespesaFixaMes> porMesId(Long mesId) {
		return repo.findByMesIdOrderByDespesaFixaNomeAsc(mesId);
	}

	@Override
	public DespesaFixaMes save(DespesaFixaMes d) {
		return repo.save(d);
	}

	@Override
	public void apagar(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Double getPrevisaoAno(int ano, int mes, Long despesa) {
		return repo.getPrevisaoAno(ano, mes, despesa);
	}

	@Override
	public Double getPrevisaoGeral(int ano, int mes, Long despesa) {
		return repo.getPrevisaoGeral(ano, mes, despesa);
	}

}
