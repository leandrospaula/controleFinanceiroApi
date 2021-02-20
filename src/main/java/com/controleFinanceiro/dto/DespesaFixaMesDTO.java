package com.controleFinanceiro.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DespesaFixaMesDTO {

	private Long id;
	@NotNull(message = "Vinculo de despesa fixa com mês não encontrou o mês")
	private MesDTO mes;
	@NotNull(message = "Vinculo de despesa fixa com mês não encontrou a despesa fixa")
	private DespesaFixaDTO despesaFixa;
	private boolean ativo;
	private Double valor;
	private Double previsao;
}
