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
@Table(name = "despesa")
@Data
public class Despesa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1614511112797585696L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "mes", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Mes mes;
	private int data;
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(nullable = false)
	private Double valor;
}
