package com.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.entity.DespesaFixa;

public interface DespesaFixaRepository extends JpaRepository<DespesaFixa, Long>{
	
	List<DespesaFixa> findByUsuarioIdOrderByAtivoDescNomeAsc(Long id);
	
	List<DespesaFixa> findByUsuarioIdAndAtivoOrderByNomeAsc(Long id, boolean ativo);

}
