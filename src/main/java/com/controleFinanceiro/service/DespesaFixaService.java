package com.controleFinanceiro.service;

import java.util.List;
import java.util.Optional;

import com.controleFinanceiro.entity.DespesaFixa;

public interface DespesaFixaService {
	
	Optional<DespesaFixa> porId(Long id);
	
	List<DespesaFixa> porUsuarioId(Long usuarioId);
	
	List<DespesaFixa> porUsuarioIdEAtivo(Long usuario, boolean ativo);
	
	DespesaFixa save(DespesaFixa d);

}
