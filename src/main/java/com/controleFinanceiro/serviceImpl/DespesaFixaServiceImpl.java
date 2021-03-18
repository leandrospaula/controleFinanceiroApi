package com.controleFinanceiro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.DespesaFixa;
import com.controleFinanceiro.repository.DespesaFixaRepository;
import com.controleFinanceiro.security.service.UserService;
import com.controleFinanceiro.service.DespesaFixaService;

@Service
public class DespesaFixaServiceImpl implements DespesaFixaService {
	
	@Autowired
	DespesaFixaRepository repo;

	@Override
	public DespesaFixa save(DespesaFixa d) {
		return repo.save(d);
	}

	@Override
	public List<DespesaFixa> porUsuarioId(Long usuarioId) {
		return repo.findByUsuarioIdOrderByAtivoDescNomeAsc(usuarioId);
	}

	@Override
	public Optional<DespesaFixa> porId(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<DespesaFixa> porUsuarioIdEAtivo(Long usuario, boolean ativo) {
		return repo.findByUsuarioIdAndAtivoOrderByNomeAsc(usuario, ativo);
	}

}
