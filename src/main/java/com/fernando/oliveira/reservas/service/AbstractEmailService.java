package com.fernando.oliveira.reservas.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fernando.oliveira.reservas.domain.Reserva;



public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Override
	public void sendOrderConfirmationEmail(Reserva obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Reserva obj) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(obj.getViajante().getEmail());
		msg.setFrom(sender);
		msg.setSubject("Pedido confirmado: "+ obj.getId());
		msg.setText(obj.toString());
		return msg;
	}
	
	protected String htmlFromTemplatePedido(Reserva obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
		
	}
	@Override
	public void sendOrderConfirmationHtmlEmail(Reserva obj) {
		
		try {
			MimeMessage mm = prepareMimeMessageFromReserva(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromReserva(Reserva obj) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getViajante().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado: " + obj.getId() );
		mmh.setText(htmlFromTemplatePedido(obj), true);
//		mmh.addAttachment(attachmentFilename, file);
		return mimeMessage;
		
	}
	

}
