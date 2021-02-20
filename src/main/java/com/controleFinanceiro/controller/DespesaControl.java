package com.controleFinanceiro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.dto.DespesaDTO;
import com.controleFinanceiro.entity.Despesa;
import com.controleFinanceiro.service.DespesaService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/despesa")
public class DespesaControl {

	@Autowired
	private DespesaService service;

	@GetMapping(path = "id/{id}")
	public ResponseEntity<Resposta<DespesaDTO>> porId(@PathVariable("id") Long id) {
		Resposta<DespesaDTO> r = new Resposta<DespesaDTO>();

		Optional<Despesa> d = service.porId(id);

		if (!d.isPresent()) {
			r.getErros().add("Dados não encontrados");
			return ResponseEntity.badRequest().body(r);
		}

		DespesaDTO dto = DTOConverter.converterDespesaDTO(d.get());
		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@GetMapping(path = "mes/{id}")
	public ResponseEntity<Resposta<List<DespesaDTO>>> porMes(@PathVariable("id") Long mes) {
		Resposta<List<DespesaDTO>> r = new Resposta<List<DespesaDTO>>();

		List<DespesaDTO> dtos = new ArrayList<>();
		List<Despesa> despesas = service.porMesId(mes);

		despesas.forEach(d -> dtos.add(DTOConverter.converterDespesaDTO(d)));
		r.setDados(dtos);
		return ResponseEntity.ok(r);
	}

	@PutMapping()
	public ResponseEntity<Resposta<DespesaDTO>> salvar(@Valid @RequestBody DespesaDTO dto, BindingResult result) {
		Resposta<DespesaDTO> r = new Resposta<DespesaDTO>();

		Despesa d = DTOConverter.converterDTODespesa(dto);

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

		dto = DTOConverter.converterDespesaDTO(d);

		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Resposta<String>> deletar(@PathVariable("id") Long id) {
		Resposta<String> r = new Resposta<String>();

		Optional<Despesa> d = service.porId(id);

		if (!d.isPresent()) {
			r.getErros().add("Dados não encontrados");
			return ResponseEntity.badRequest().body(r);
		}

		try {
			service.deletar(d.get());
		} catch (Exception e) {
			r.getErros().add("Erro: " + e.getMessage());
		}

		r.setDados("Item apagado com sucesso!");
		return ResponseEntity.ok(r);
	}

}
