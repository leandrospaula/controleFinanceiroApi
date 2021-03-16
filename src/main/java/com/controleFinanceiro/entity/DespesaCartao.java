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
@Table(name = "despesa_cartao")
@Data
public class DespesaCartao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8593184393649912409L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@JoinColumn(name = "cartao", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Cartao cartao;
	@Column(nullable = false)
	private Double valor;
	@JoinColumn(name = "mes", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Mes mes;
	@Column()
	private boolean terceiros = false;

}
