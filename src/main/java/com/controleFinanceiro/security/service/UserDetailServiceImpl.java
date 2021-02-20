package com.controleFinanceiro.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.entity.Usuario;
import com.controleFinanceiro.repository.UsuarioRepository;
import com.controleFinanceiro.security.UserDetail;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> u = repo.findByEmail(username);

		if (!u.isPresent()) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetail(u.get().getId(), u.get().getEmail(), u.get().getSenha());
	}

}
