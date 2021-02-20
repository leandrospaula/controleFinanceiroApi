package com.controleFinanceiro.security.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.controleFinanceiro.security.UserDetail;

public class UserService {

	public static UserDetail authenticaded() {
		try {
			return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		} catch (Exception e) {
			return null;
		}
	}

}
