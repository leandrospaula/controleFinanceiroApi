package com.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.entity.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{
	
	List<Despesa> findByMesIdOrderByDataAsc(Long id);

}
