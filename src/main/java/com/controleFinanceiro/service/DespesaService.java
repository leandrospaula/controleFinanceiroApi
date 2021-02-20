package com.controleFinanceiro.service;

import java.util.List;
import java.util.Optional;

import com.controleFinanceiro.entity.Despesa;

public interface DespesaService {

	void deletar(Despesa d);

	Optional<Despesa> porId(Long id);

	List<Despesa> porMesId(Long mesId);

	Despesa save(Despesa d);

}
