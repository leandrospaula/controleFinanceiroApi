package com.controleFinanceiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controleFinanceiro.entity.Mes;

public interface MesRepository extends JpaRepository<Mes, Long> {

	List<Mes> findByAno(int ano);

	List<Mes> findByUsuarioId(Long usuarioId);

	List<Mes> findByUsuarioIdAndAnoOrderByMesAsc(Long usuarioId, int ano);

	@Query("select distinct m.ano from Mes m where m.usuario.id = :id order by 1 desc")
	List<Integer> findDistinctYears(@Param("id") Long usuario);

	Optional<Mes> findByUsuarioIdAndMesEqualsAndAnoEquals(Long usuario, int mes, int ano);
}
