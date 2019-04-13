package com.fernando.oliveira.reservas.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.fernando.oliveira.reservas.domain.Reserva;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Reserva obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Reserva obj);
	
	void sendHtmlEmail(MimeMessage msg);


}
