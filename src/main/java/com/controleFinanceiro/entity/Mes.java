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
@Table(name = "mes")
@Data
public class Mes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1448316870332326518L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private int mes;
	@Column(nullable = false)
	private int ano;
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	@Column()
	private boolean encerrado = false;
	@Column()
	private Double salario;
	@Column()
	private Double economia;
	@Column()
	private Double livre;
	@Column(name = "total_cartao")
	private Double totalCartao = 0.0;
	@Column(name = "total_fixo")
	private Double totalFixo = 0.0;
	@Column(name = "total_gasto")
	private Double totalGasto = 0.0;

}
