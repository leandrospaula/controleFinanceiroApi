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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.dto.DespesaFixaDTO;
import com.controleFinanceiro.entity.DespesaFixa;
import com.controleFinanceiro.security.service.UserService;
import com.controleFinanceiro.service.DespesaFixaService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/despesa-fixa")
public class DespesaFixaControl {

	@Autowired
	private DespesaFixaService service;

	@GetMapping(path = "id/{id}")
	public ResponseEntity<Resposta<DespesaFixaDTO>> porId(@PathVariable("id") Long id) {
		Resposta<DespesaFixaDTO> r = new Resposta<DespesaFixaDTO>();

		Optional<DespesaFixa> d = service.porId(id);

		if (!d.isPresent()) {
			r.getErros().add("Dados não encontrados");
			return ResponseEntity.badRequest().body(r);
		}

		DespesaFixaDTO dto = DTOConverter.converterDespesaFixaDTO(d.get());
		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@GetMapping()
	public ResponseEntity<Resposta<List<DespesaFixaDTO>>> porUsuario(
			@RequestParam(value = "ativos", required = false) Boolean ativos) {
		Resposta<List<DespesaFixaDTO>> d = new Resposta<List<DespesaFixaDTO>>();

		Long usuario = UserService.authenticaded().getId();
		List<DespesaFixaDTO> dtos = new ArrayList<>();
		List<DespesaFixa> despesas = new ArrayList<>();
		if (ativos == null) {
			despesas = service.porUsuarioId(usuario);
		} else {
			despesas = service.porUsuarioIdEAtivo(usuario, ativos);
		}
		despesas.forEach(dep -> dtos.add(DTOConverter.converterDespesaFixaDTO(dep)));

		d.setDados(dtos);
		return ResponseEntity.ok(d);
	}

	@PutMapping()
	public ResponseEntity<Resposta<DespesaFixaDTO>> salvar(@Valid @RequestBody DespesaFixaDTO dto,
			BindingResult result) {
		Resposta<DespesaFixaDTO> r = new Resposta<DespesaFixaDTO>();

		DespesaFixa d = DTOConverter.converterDTODespesaFixa(dto);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> r.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(r);
		}

		try {
			d = service.save(d);
		} catch (Exception e) {
			r.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(r);
		}

		dto = DTOConverter.converterDespesaFixaDTO(d);

		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

//	@DeleteMapping(path = "{id}")
//	public ResponseEntity<Resposta<String>> deletar(@PathVariable("id") Long id) {
//		Resposta<String> r = new Resposta<String>();
//
//		Optional<Despesa> d = service.porId(id);
//
//		if (!d.isPresent()) {
//			r.getErros().add("Dados não encontrados");
//			return ResponseEntity.badRequest().body(r);
//		}
//
//		try {
//			service.deletar(d.get());
//		} catch (Exception e) {
//			r.getErros().add("Erro: " + e.getMessage());
//		}
//
//		r.setDados("Item apagado com sucesso!");
//		return ResponseEntity.ok(r);
//	}

}
