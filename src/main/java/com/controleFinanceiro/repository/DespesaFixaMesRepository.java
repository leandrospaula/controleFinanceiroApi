package com.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controleFinanceiro.entity.DespesaFixaMes;

public interface DespesaFixaMesRepository extends JpaRepository<DespesaFixaMes, Long> {

	List<DespesaFixaMes> findByMesIdOrderByDespesaFixaNomeAsc(Long id);

	@Query("select SUM(d.valor)/COUNT(id) from DespesaFixaMes d where ((d.mes.ano = ?1 and d.mes.mes < ?2) or (d.mes.ano < ?1)) and d.despesaFixa.id = ?3")
	Double getPrevisaoGeral(int ano, int mes, Long despesa);

	@Query("select SUM(d.valor)/COUNT(id) from DespesaFixaMes d where (d.mes.ano = ?1 and d.mes.mes < ?2) and d.despesaFixa.id = ?3")
	Double getPrevisaoAno(int ano, int mes, Long despesa);

}
