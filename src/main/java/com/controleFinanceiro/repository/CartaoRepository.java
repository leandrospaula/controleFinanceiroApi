package com.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.entity.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	List<Cartao> findByUsuarioId(Long id);
}
