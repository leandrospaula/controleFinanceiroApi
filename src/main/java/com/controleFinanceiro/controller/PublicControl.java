package com.controleFinanceiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.util.Resposta;

@RestController
@RequestMapping("/api/public")
public class PublicControl {

	@GetMapping()
	public ResponseEntity<Resposta<String>> getById() {

		Resposta<String> r = new Resposta<String>();
		r.setDados("Ok");
		return ResponseEntity.ok(r);
	}
}
