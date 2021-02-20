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
@Table(name = "cartao")
@Data
public class Cartao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1289391424697038526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column()
	private boolean ativo = true;
	@Column()
	private Integer fechamento;
	private Integer vencimento;
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

}
