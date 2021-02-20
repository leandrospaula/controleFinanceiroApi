package com.controleFinanceiro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.Mes;
import com.controleFinanceiro.repository.MesRepository;
import com.controleFinanceiro.service.MesService;

@Service
public class MesServiceImpl implements MesService {

	@Autowired
	MesRepository repo;

	@Override
	public List<Mes> porAno(int ano) {
		return repo.findByAno(ano);
	}

	@Override
	public Optional<Mes> porId(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Mes> porUsuarioId(Long usuarioId) {
		return repo.findByUsuarioId(usuarioId);
	}

	@Override
	public List<Mes> porUsuarioIdAno(Long usuarioId, int ano) {
		return repo.findByUsuarioIdAndAnoOrderByMesAsc(usuarioId, ano);
	}

	@Override
	public Mes save(Mes m) {
		return repo.save(m);
	}

	@Override
	public List<Integer> anosPorUsuario(Long usuario) {
		return repo.findDistinctYears(usuario);
	}

	@Override
	public Optional<Mes> findMesAnterior(Long usuario, int mes, int ano) {
		return repo.findByUsuarioIdAndMesEqualsAndAnoEquals(usuario, mes, ano);
	}

}
