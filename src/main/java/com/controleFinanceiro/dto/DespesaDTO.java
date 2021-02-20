package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DespesaDTO {

	private Long id;
	@NotNull(message = "Uma despesa deve estar vinculada a um mês")
	private MesDTO mes;
	@NotNull(message = "Uma despesa deve ter uma data")
	private int data;
	@NotNull(message = "Uma despesa deve ter um identificador")
	private String nome;
	@NotNull(message = "Uma despesa deve ter um valor")
	private Double valor;
}
