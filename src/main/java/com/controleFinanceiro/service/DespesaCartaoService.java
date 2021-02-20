package com.controleFinanceiro.service;

import java.util.List;
import java.util.Optional;

import com.controleFinanceiro.entity.DespesaCartao;

public interface DespesaCartaoService {
	
	void deletar(DespesaCartao d);
	
	List<DespesaCartao> porCartaoId(Long cartaoId);
	
	List<DespesaCartao> porMesId(Long mes);
	
	Optional<DespesaCartao> porId(Long id);
	
	DespesaCartao save(DespesaCartao d);

}
