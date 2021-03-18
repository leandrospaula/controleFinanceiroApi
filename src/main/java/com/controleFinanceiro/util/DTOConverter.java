package com.controleFinanceiro.util;

import com.controleFinanceiro.dto.CartaoDTO;
import com.controleFinanceiro.dto.DespesaCartaoDTO;
import com.controleFinanceiro.dto.DespesaDTO;
import com.controleFinanceiro.dto.DespesaFixaDTO;
import com.controleFinanceiro.dto.DespesaFixaMesDTO;
import com.controleFinanceiro.dto.MesDTO;
import com.controleFinanceiro.dto.UsuarioDTO;
import com.controleFinanceiro.entity.Cartao;
import com.controleFinanceiro.entity.Despesa;
import com.controleFinanceiro.entity.DespesaCartao;
import com.controleFinanceiro.entity.DespesaFixa;
import com.controleFinanceiro.entity.DespesaFixaMes;
import com.controleFinanceiro.entity.Mes;
import com.controleFinanceiro.entity.Usuario;

public class DTOConverter {

	// Cartão
	public static Cartao converterDTOCartao(CartaoDTO dto) {
		Cartao c = new Cartao();

		c.setAtivo(dto.isAtivo());
		c.setId(dto.getId());
		c.setNome(dto.getNome());
		c.setUsuario(converterDTOUsuario(dto.getUsuario(), false));
		c.setFechamento(dto.getFechamento());
		c.setVencimento(dto.getVencimento());

		return c;
	}

	public static CartaoDTO converterCartaoDTO(Cartao c) {
		CartaoDTO dto = new CartaoDTO();

		dto.setAtivo(c.isAtivo());
		dto.setId(c.getId());
		dto.setNome(c.getNome());
		dto.setUsuario(converterUsuarioDTO(c.getUsuario()));
		dto.setFechamento(c.getFechamento());
		dto.setVencimento(c.getVencimento());

		return dto;
	}

	// Despesa Cartao
	public static DespesaCartao converterDTODespesaCartao(DespesaCartaoDTO dto) {
		DespesaCartao d = new DespesaCartao();

		d.setCartao(converterDTOCartao(dto.getCartao()));
		d.setId(dto.getId());
		d.setMes(converterDTOMes(dto.getMes()));
		d.setNome(dto.getNome());
		d.setTerceiros(dto.isTerceiros());
		d.setValor(dto.getValor());

		return d;
	}

	public static DespesaCartaoDTO converterDespesaCartaoDTO(DespesaCartao c) {
		DespesaCartaoDTO dto = new DespesaCartaoDTO();

		dto.setCartao(converterCartaoDTO(c.getCartao()));
		dto.setId(c.getId());
		dto.setMes(converterMesDTO(c.getMes()));
		dto.setNome(c.getNome());
		dto.setTerceiros(c.isTerceiros());
		dto.setValor(c.getValor());

		return dto;
	}

	// Despesa
	public static Despesa converterDTODespesa(DespesaDTO dto) {
		Despesa d = new Despesa();
		d.setData(dto.getData());
		d.setId(dto.getId());
		d.setMes(converterDTOMes(dto.getMes()));
		d.setNome(dto.getNome());
		d.setValor(dto.getValor());

		return d;
	}

	public static DespesaDTO converterDespesaDTO(Despesa d) {
		DespesaDTO dto = new DespesaDTO();

		dto.setData(d.getData());
		dto.setId(d.getId());
		dto.setMes(converterMesDTO(d.getMes()));
		dto.setNome(d.getNome());
		dto.setValor(d.getValor());

		return dto;
	}

	// Despesa Fixa
	public static DespesaFixa converterDTODespesaFixa(DespesaFixaDTO dto) {
		DespesaFixa d = new DespesaFixa();

		d.setId(dto.getId());
		d.setNome(dto.getNome());
		d.setUsuario(converterDTOUsuario(dto.getUsuario(), false));
		d.setVencimento(dto.getVencimento());
		d.setCalculo(dto.getCalculo());
		d.setTipo(dto.getTipo());
		d.setValor(dto.getValor());
		d.setAtivo(dto.isAtivo());
		d.setParte(dto.getParte());

		return d;
	}

	public static DespesaFixaDTO converterDespesaFixaDTO(DespesaFixa d) {
		DespesaFixaDTO dto = new DespesaFixaDTO();

		dto.setId(d.getId());
		dto.setNome(d.getNome());
		dto.setUsuario(converterUsuarioDTO(d.getUsuario()));
		dto.setVencimento(d.getVencimento());
		dto.setCalculo(d.getCalculo());
		dto.setTipo(d.getTipo());
		dto.setValor(d.getValor());
		dto.setAtivo(d.isAtivo());
		dto.setParte(d.getParte());

		return dto;
	}

	// DespesaFixaMes
	public static DespesaFixaMes converterDTODespesaFixaMes(DespesaFixaMesDTO dto) {
		DespesaFixaMes d = new DespesaFixaMes();

		d.setAtivo(dto.isAtivo());
		d.setDespesaFixa(converterDTODespesaFixa(dto.getDespesaFixa()));
		d.setId(dto.getId());
		d.setMes(converterDTOMes(dto.getMes()));
		d.setValor(dto.getValor());
		d.setPrevisao(dto.getPrevisao());

		return d;
	}

	public static DespesaFixaMesDTO converterDespesaFixaMesDTO(DespesaFixaMes d) {
		DespesaFixaMesDTO dto = new DespesaFixaMesDTO();

		dto.setAtivo(d.isAtivo());
		dto.setDespesaFixa(converterDespesaFixaDTO(d.getDespesaFixa()));
		dto.setId(d.getId());
		dto.setMes(converterMesDTO(d.getMes()));
		dto.setValor(d.getValor());
		dto.setPrevisao(d.getPrevisao());

		return dto;
	}

	// Mes
	public static Mes converterDTOMes(MesDTO dto) {
		Mes m = new Mes();

		m.setAno(dto.getAno());
		m.setEconomia(dto.getEconomia());
		m.setEncerrado(dto.isEncerrado());
		m.setId(dto.getId());
		m.setMes(dto.getMes());
		m.setSalario(dto.getSalario());
		m.setUsuario(converterDTOUsuario(dto.getUsuario(), false));
		m.setLivre(dto.getLivre());
		m.setTotalCartao(dto.getTotalCartao());
		m.setTotalFixo(dto.getTotalFixo());
		m.setTotalGasto(dto.getTotalGasto());

		return m;
	}

	public static MesDTO converterMesDTO(Mes m) {
		MesDTO dto = new MesDTO();

		dto.setAno(m.getAno());
		dto.setEconomia(m.getEconomia());
		dto.setEncerrado(m.isEncerrado());
		dto.setId(m.getId());
		dto.setMes(m.getMes());
		dto.setSalario(m.getSalario());
		dto.setUsuario(converterUsuarioDTO(m.getUsuario()));
		dto.setLivre(m.getLivre());
		dto.setTotalCartao(m.getTotalCartao());
		dto.setTotalFixo(m.getTotalFixo());
		dto.setTotalGasto(m.getTotalGasto());

		return dto;
	}

	// Usuario
	public static Usuario converterDTOUsuario(UsuarioDTO dto, boolean troca) {
		Usuario u = new Usuario();

		u.setEmail(dto.getEmail());
		u.setId(dto.getId());
		u.setNome(dto.getNome());
		if (troca) {
			u.setSenha(Bcrypt.gerarBcrypt(dto.getSenha()));
		} else {
			u.setSenha(dto.getSenha());
		}
		return u;
	}

	public static UsuarioDTO converterUsuarioDTO(Usuario u) {
		UsuarioDTO dto = new UsuarioDTO();

		dto.setEmail(u.getEmail());
		dto.setId(u.getId());
		dto.setNome(u.getNome());

		return dto;
	}

}
