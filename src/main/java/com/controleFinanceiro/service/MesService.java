package com.controleFinanceiro.service;

import java.util.List;
import java.util.Optional;

import com.controleFinanceiro.entity.Mes;

public interface MesService {

	List<Integer> anosPorUsuario(Long usuario);

	Optional<Mes> findMesAnterior(Long usuario, int mes, int ano);

	List<Mes> porAno(int ano);

	Optional<Mes> porId(Long id);

	List<Mes> porUsuarioId(Long usuarioId);

	List<Mes> porUsuarioIdAno(Long usuarioId, int ano);

	Mes save(Mes m);

}
