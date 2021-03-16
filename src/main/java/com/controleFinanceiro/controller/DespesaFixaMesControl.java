package com.controleFinanceiro.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.controleFinanceiro.dto.DespesaFixaMesDTO;
import com.controleFinanceiro.entity.DespesaFixaMes;
import com.controleFinanceiro.service.DespesaFixaMesService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/despesa-fixa-mes")
public class DespesaFixaMesControl {

	@Autowired
	private DespesaFixaMesService service;

	@GetMapping(path = "mes/{id}")
	public ResponseEntity<Resposta<List<DespesaFixaMesDTO>>> porMes(@PathVariable("id") Long mes) {
		Resposta<List<DespesaFixaMesDTO>> d = new Resposta<List<DespesaFixaMesDTO>>();

		List<DespesaFixaMesDTO> dtos = new ArrayList<>();
		List<DespesaFixaMes> despesas = service.porMesId(mes);

		despesas.forEach(dep -> dtos.add(DTOConverter.converterDespesaFixaMesDTO(dep)));
		d.setDados(dtos);
		return ResponseEntity.ok(d);
	}

	@PutMapping()
	public ResponseEntity<Resposta<DespesaFixaMesDTO>> salvar(@Valid @RequestBody DespesaFixaMesDTO dto,
			BindingResult result) {
		Resposta<DespesaFixaMesDTO> r = new Resposta<DespesaFixaMesDTO>();

		DespesaFixaMes d = DTOConverter.converterDTODespesaFixaMes(dto);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> r.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(r);
		}

		if (d.getValor() == null) {
			Double previsao = 0.0;
			if (d.getDespesaFixa().getCalculo().equals("A")) {
				previsao = service.getPrevisaoAno(d.getMes().getAno(), d.getMes().getMes(), d.getDespesaFixa().getId());
			} else {
				previsao = service.getPrevisaoGeral(d.getMes().getAno(), d.getMes().getMes(), d.getDespesaFixa().getId());
			}
			d.setPrevisao(previsao);
		}
		try {
			d = service.save(d);
		} catch (Exception e) {
			r.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(r);
		}

		dto = DTOConverter.converterDespesaFixaMesDTO(d);

		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Resposta<String>> deletar(@PathVariable("id") Long id) {
		Resposta<String> r = new Resposta<String>();

		try {
			service.apagar(id);
		} catch (Exception e) {
			r.getErros().add("Erro: " + e.getMessage());
		}

		r.setDados("Item apagado com sucesso!");
		return ResponseEntity.ok(r);
	}

}
