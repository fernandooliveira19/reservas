package com.fernando.oliveira.reservas.domain.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.DateFormatter;

import org.apache.commons.lang3.time.DateUtils;

public class ReservaDateUtils {

	
	public static Date parseStringToDate(String data, String pattern) {
		
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(data, pattern);
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
	
	public static String formatarDataLocal(LocalDate localDate) {
		
		Date date = parseStringToDate(localDate.toString(), "yyyy-MM-dd");
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
		return df.format(date);
		
	}
	
	public static String getDataAtual() {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat("dd '  de  ' MMMM '  de  ' yyyy", local);
		return dateFormat.format(data);

	}
	
	public static String formatarValorMonetario(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		
		return nf.format(valor);
	}

	public static boolean isLancamentoAntesCheckIn(LocalDate dataLancamento, LocalDateTime dataEntrada) {
		
		Date lancamento = converterLocalDate(dataLancamento);
		Date entrada = converterLocalDateTime(dataEntrada);
		
		
		return lancamento.before(entrada);
	}
	
	public static Date converterLocalDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date converterLocalDateTime(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
