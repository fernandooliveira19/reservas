package com.fernando.oliveira.reservas.domain.utils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class ReservaDateUtils {

	
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
	
	public static String formatarDataLocal(LocalDateTime data) {
		
		DateTimeFormatter formatter = DateTimeFormatter
		  .ofLocalizedDateTime(FormatStyle.MEDIUM)
		  .withLocale(new Locale("pt", "br"));
		return data.format(formatter);
		
	}
}
