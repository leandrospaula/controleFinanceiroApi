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

import com.controleFinanceiro.dto.DespesaCartaoDTO;
import com.controleFinanceiro.entity.DespesaCartao;
import com.controleFinanceiro.entity.Mes;
import com.controleFinanceiro.service.DespesaCartaoService;
import com.controleFinanceiro.service.MesService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/despesa-cartao")
public class DespesaCartaoControl {

	@Autowired
	private DespesaCartaoService service;

	@Autowired
	private MesService mesService;

	@GetMapping(path = "id/{id}")
	public ResponseEntity<Resposta<DespesaCartaoDTO>> getById(@PathVariable("id") Long id) {

		Resposta<DespesaCartaoDTO> r = new Resposta<DespesaCartaoDTO>();

		Optional<DespesaCartao> d = service.porId(id);

		if (!d.isPresent()) {
			r.getErros().add("Dados não encontrados");
			return ResponseEntity.badRequest().body(r);
		}

		DespesaCartaoDTO dto = DTOConverter.converterDespesaCartaoDTO(d.get());
		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@GetMapping(path = "cartao/{id}")
	public ResponseEntity<Resposta<List<DespesaCartaoDTO>>> getByUsuario(@PathVariable("id") Long cartaoId) {

		Resposta<List<DespesaCartaoDTO>> r = new Resposta<List<DespesaCartaoDTO>>();

		List<DespesaCartao> despesas = service.porCartaoId(cartaoId);

		List<DespesaCartaoDTO> dto = new ArrayList<>();

		despesas.forEach(d -> dto.add(DTOConverter.converterDespesaCartaoDTO(d)));

		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@GetMapping(path = "mes/{id}")
	public ResponseEntity<Resposta<List<DespesaCartaoDTO>>> getByMes(@PathVariable("id") Long mes) {
		Resposta<List<DespesaCartaoDTO>> resposta = new Resposta<List<DespesaCartaoDTO>>();

		List<DespesaCartao> despesas = service.porMesId(mes);
		List<DespesaCartaoDTO> dto = new ArrayList<>();

		despesas.forEach(d -> dto.add(DTOConverter.converterDespesaCartaoDTO(d)));
		resposta.setDados(dto);
		return ResponseEntity.ok(resposta);
	}

	@PutMapping()
	public ResponseEntity<Resposta<DespesaCartaoDTO>> salvar(@Valid @RequestBody DespesaCartaoDTO dto,
			BindingResult result) {
		Resposta<DespesaCartaoDTO> r = new Resposta<DespesaCartaoDTO>();

		DespesaCartao d = DTOConverter.converterDTODespesaCartao(dto);
		boolean novo = d.getId() == null;

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> r.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(r);
		}

		try {
			d.setValor(d.getValor() / dto.getVezes());
			d = service.save(d);
		} catch (Exception e) {
			r.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(r);
		}

		if (novo && dto.getVezes() > 1) {
			int vezes = dto.getVezes() - 1;
			int mes = d.getMes().getMes() + 1;
			int ano = d.getMes().getAno();
			Optional<Mes> m = null;
			while (vezes > 0) {
				if (mes > 12) {
					mes = 1;
					ano += 1;
				}
				m = mesService.findMesAnterior(d.getMes().getUsuario().getId(), mes, ano);

				if (!m.isPresent()) {
					for (int i = 1; i < 13; i++) {
						Mes mm = new Mes();
						mm.setAno(ano);
						mm.setEconomia(0.0);
						mm.setEncerrado(false);
						mm.setMes(i);
						mm.setSalario(0.0);
						mm.setUsuario(d.getMes().getUsuario());
						mesService.save(mm);
					}
					m = mesService.findMesAnterior(dto.getMes().getUsuario().getId(), mes, ano);
				}

				DespesaCartao dc = new DespesaCartao();
				dc.setCartao(d.getCartao());
				dc.setId(null);
				dc.setMes(m.get());
				dc.setNome(d.getNome());
				dc.setTerceiros(d.isTerceiros());
				dc.setValor(d.getValor());
				service.save(dc);
				if (!dc.isTerceiros()) {
					m.get().setTotalCartao(m.get().getTotalCartao() + dc.getValor());
				}
				mesService.save(m.get());
				mes += 1;
				vezes -= 1;
			}

		}

		r.setDados(dto = DTOConverter.converterDespesaCartaoDTO(d));
		return ResponseEntity.ok(r);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Resposta<String>> deletar(@PathVariable("id") Long id) {
		Optional<DespesaCartao> d = service.porId(id);

		Resposta<String> r = new Resposta<String>();

		if (!d.isPresent()) {
			r.getErros().add("Dado não encontrado");
			return ResponseEntity.badRequest().body(r);
		}

		try {
			service.deletar(d.get());
		} catch (Exception e) {
			r.getErros().add("Erro: " + e.getMessage());
			return ResponseEntity.badRequest().body(r);
		}

		r.setDados("Item apagado com sucesso!");
		return ResponseEntity.ok(r);
	}

}
