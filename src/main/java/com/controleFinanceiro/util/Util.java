package com.controleFinanceiro.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

	public static int getAno(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);

		return calendar.get(Calendar.YEAR);
	}

}
