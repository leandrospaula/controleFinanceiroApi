package com.controleFinanceiro.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.dto.UsuarioDTO;
import com.controleFinanceiro.entity.Usuario;
import com.controleFinanceiro.security.service.UserService;
import com.controleFinanceiro.service.UsuarioService;
import com.controleFinanceiro.util.DTOConverter;
import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControl {

	@Autowired
	private UsuarioService service;

	@GetMapping(path = "id")
	public ResponseEntity<Resposta<UsuarioDTO>> porId() {
		Resposta<UsuarioDTO> response = new Resposta<UsuarioDTO>();

		Optional<Usuario> u = service.porId(UserService.authenticaded().getId());
		if (!u.isPresent()) {
			response.getErros().add("Usuário não encontrado");
			return ResponseEntity.badRequest().body(response);
		}

		response.setDados(DTOConverter.converterUsuarioDTO(u.get()));

		return ResponseEntity.ok(response);
	}

	@PutMapping()
	public ResponseEntity<Resposta<UsuarioDTO>> salvar(@Valid @RequestBody UsuarioDTO dto, BindingResult result,
			@RequestParam(value = "reset", defaultValue = "false") boolean reset) {
		Resposta<UsuarioDTO> response = new Resposta<UsuarioDTO>();
		Usuario u = DTOConverter.converterDTOUsuario(dto, reset);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			u = service.save(u);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		response.setDados(DTOConverter.converterUsuarioDTO(u));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping()
	public ResponseEntity<Resposta<UsuarioDTO>> criar(@Valid @RequestBody UsuarioDTO dto, BindingResult result) {
		Resposta<UsuarioDTO> response = new Resposta<UsuarioDTO>();
		Usuario u = DTOConverter.converterDTOUsuario(dto, true);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			u = service.save(u);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		response.setDados(DTOConverter.converterUsuarioDTO(u));
		return ResponseEntity.ok(response);
	}

}
