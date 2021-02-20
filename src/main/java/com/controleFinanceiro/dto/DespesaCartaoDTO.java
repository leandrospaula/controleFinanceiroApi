package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DespesaCartaoDTO {

	private Long id;
	@NotNull(message = "Uma despesa de cartão deve ter um identificador")
	private String nome;
	@NotNull(message = "Uma despesa de cartão deve estar vinculada a um cartão")
	private CartaoDTO cartao;
	@NotNull(message = "Uma despesa de cartão deve ter um valor")
	private Double valor;
	@NotNull(message = "Uma despesa de cartão deve estar vinculada a um mês")
	private MesDTO mes;
	private boolean terceiros;
	private int vezes = 1;

}
