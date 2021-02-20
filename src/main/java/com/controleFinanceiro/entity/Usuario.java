package com.controleFinanceiro.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6104669364568328822L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String email;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false, length = 100)
	private String nome;

}
	