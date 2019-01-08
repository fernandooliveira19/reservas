package com.fernando.oliveira.reservas.domain.utils;

import java.text.ParseException;
import java.util.Date;

public class DateUtils {

	
	public static Date parseStringToDate(String data, String pattern) {
		
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(data, "dd/MM/yyyy HH:mm");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static String parseDateToString(Date date) {
		
		return date.toString();
		
	}
}
