package com.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.entity.DespesaCartao;

public interface DespesaCartaoRepository extends JpaRepository<DespesaCartao, Long>{
	
	List<DespesaCartao> findByCartaoId(Long id);
	
	List<DespesaCartao> findByMesIdOrderByTerceirosAscNomeAsc(Long id);

}
