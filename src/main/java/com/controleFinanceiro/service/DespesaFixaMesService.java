package com.controleFinanceiro.service;

import java.util.List;

import com.controleFinanceiro.entity.DespesaFixaMes;

public interface DespesaFixaMesService {

	void apagar(Long id);

	Double getPrevisaoAno(int ano, int mes, Long despesa);

	Double getPrevisaoGeral(int ano, int mes, Long despesa);

	List<DespesaFixaMes> porMesId(Long mesId);

	DespesaFixaMes save(DespesaFixaMes d);

}
