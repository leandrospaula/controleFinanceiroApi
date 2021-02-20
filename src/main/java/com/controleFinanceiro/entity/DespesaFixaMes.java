package com.controleFinanceiro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "despesa_fixa_mes")
@Data
public class DespesaFixaMes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2016306925812592890L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "mes", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Mes mes;
	@JoinColumn(name = "despesa_fixa", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private DespesaFixa despesaFixa;
	@Column()
	private boolean ativo = true;
	@Column()
	private Double valor;
	private Double previsao;

}
