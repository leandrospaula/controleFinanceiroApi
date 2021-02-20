package com.controleFinanceiro.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {

	public static String gerarBcrypt(String senha) {
		if (senha == null) {
			return null;
		}
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return bcrypt.encode(senha);
	}

}
