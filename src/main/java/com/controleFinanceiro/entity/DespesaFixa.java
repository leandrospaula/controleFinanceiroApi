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
@Table(name = "despesa_fixa")
@Data
public class DespesaFixa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170908925151226807L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	private int vencimento;
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	@Column()
	private int ano;
	@Column(length = 1)
	private String tipo;
	@Column(length = 1)
	private String calculo;
	@Column()
	private boolean ativo;
	@Column(nullable = false)
	private Double valor;
	@Column()
	private Double parte;
	
}
