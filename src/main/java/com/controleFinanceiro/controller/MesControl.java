package com.controleFinanceiro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.dto.MesDTO;
import com.controleFinanceiro.entity.Mes;
import com.controleFinanceiro.entity.Usuario;
import com.controleFinanceiro.security.service.UserService;
import com.controleFinanceiro.service.MesService;
import com.controleFinanceiro.service.UsuarioService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/mes")
public class MesControl {

	@Autowired
	private MesService service;

	@Autowired
	private UsuarioService userService;

	@GetMapping(path = "usuario")
	public ResponseEntity<Resposta<List<MesDTO>>> porUsuario() {
		Resposta<List<MesDTO>> d = new Resposta<List<MesDTO>>();

		List<MesDTO> dtos = new ArrayList<>();
		List<Mes> meses = service.porUsuarioId(UserService.authenticaded().getId());

		meses.forEach(dep -> dtos.add(DTOConverter.converterMesDTO(dep)));

		return ResponseEntity.ok(d);
	}

	@GetMapping(path = "anos")
	public ResponseEntity<Resposta<List<Integer>>> anosPorUsuario() {
		Resposta<List<Integer>> resposta = new Resposta<List<Integer>>();
		List<Integer> anos = service.anosPorUsuario(UserService.authenticaded().getId());
		resposta.setDados(anos);

		return ResponseEntity.ok(resposta);
	}

	@GetMapping(path = "{mes}/{ano}")
	public ResponseEntity<Resposta<MesDTO>> mesAnterior(@PathVariable("mes") int mes, @PathVariable("ano") int ano) {
		Resposta<MesDTO> resposta = new Resposta<MesDTO>();

		// Se janeiro, buscar ano anterior e dezembro;
		if (mes == 1) {
			ano -= 1;
			mes = 12;
		} else {
			mes -= 1;
		}

		Optional<Mes> m = service.findMesAnterior(UserService.authenticaded().getId(), mes, ano);
		if (!m.isPresent()) {
			resposta.getErros().add("Nenhum mês anterior encontrado");
			return ResponseEntity.badRequest().body(resposta);
		}

		resposta.setDados(DTOConverter.converterMesDTO(m.get()));

		return ResponseEntity.ok(resposta);
	}

	@GetMapping(path = "ano/{ano}")
	public ResponseEntity<Resposta<List<MesDTO>>> porAno(@PathVariable("ano") int ano) {
		Resposta<List<MesDTO>> resposta = new Resposta<List<MesDTO>>();

		List<MesDTO> dtos = new ArrayList<>();
		Long usuario = UserService.authenticaded().getId();
		List<Mes> meses = service.porUsuarioIdAno(usuario, ano);

		if (meses.isEmpty()) {
			Optional<Usuario> u = userService.porId(usuario);
			if (!u.isPresent()) {
				resposta.getErros().add("Usuário não encontrado para carregamento de mêses");
				return ResponseEntity.badRequest().body(resposta);
			}
			for (int i = 1; i < 13; i++) {
				Mes m = new Mes();
				m.setAno(ano);
				m.setEconomia(0.0);
				m.setEncerrado(false);
				m.setMes(i);
				m.setSalario(0.0);
				m.setUsuario(u.get());
				m = service.save(m);
				meses.add(m);
			}
		}

		meses.forEach(dep -> dtos.add(DTOConverter.converterMesDTO(dep)));
		resposta.setDados(dtos);
		return ResponseEntity.ok(resposta);
	}

	@PutMapping()
	public ResponseEntity<Resposta<MesDTO>> salvar(@Valid @RequestBody MesDTO dto, BindingResult result) {
		Resposta<MesDTO> m = new Resposta<MesDTO>();

		Mes d = DTOConverter.converterDTOMes(dto);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> m.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(m);
		}

		try {
			d = service.save(d);
		} catch (Exception e) {
			m.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(m);
		}

		dto = DTOConverter.converterMesDTO(d);

		m.setDados(dto);
		return ResponseEntity.ok(m);
	}

}
