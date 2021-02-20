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

import com.controleFinanceiro.dto.CartaoDTO;
import com.controleFinanceiro.entity.Cartao;
import com.controleFinanceiro.security.service.UserService;
import com.controleFinanceiro.service.CartaoService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/cartao")
public class CartaoControl {

	@Autowired
	private CartaoService service;

	@GetMapping(path = "id/{id}")
	public ResponseEntity<Resposta<CartaoDTO>> getById(@PathVariable("id") Long id) {

		Resposta<CartaoDTO> r = new Resposta<CartaoDTO>();

		Optional<Cartao> c = service.porId(id);

		if (!c.isPresent()) {
			r.getErros().add("Dados não encontrados");
			return ResponseEntity.badRequest().body(r);
		}

		CartaoDTO dto = DTOConverter.converterCartaoDTO(c.get());
		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@GetMapping(path = "user")
	public ResponseEntity<Resposta<List<CartaoDTO>>> getByUsuario() {

		Resposta<List<CartaoDTO>> r = new Resposta<List<CartaoDTO>>();

		List<Cartao> cartoes = service.porUsuario(UserService.authenticaded().getId());

		List<CartaoDTO> dto = new ArrayList<>();

		cartoes.forEach(c -> dto.add(DTOConverter.converterCartaoDTO(c)));

		r.setDados(dto);
		return ResponseEntity.ok(r);
	}

	@PutMapping()
	public ResponseEntity<Resposta<CartaoDTO>> salvar(@Valid @RequestBody CartaoDTO dto, BindingResult result) {
		Resposta<CartaoDTO> r = new Resposta<CartaoDTO>();

		Cartao c = DTOConverter.converterDTOCartao(dto);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> r.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(r);
		}

		try {
			c = service.save(c);
		} catch (Exception e) {
			r.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(r);
		}

		r.setDados(dto = DTOConverter.converterCartaoDTO(c));
		return ResponseEntity.ok(r);
	}

}
