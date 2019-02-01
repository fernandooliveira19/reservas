package com.fernando.oliveira.reservas.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.NumberFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.utils.ReservaDateUtils;
import com.fernando.oliveira.reservas.repository.ViajanteRepository;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class AutorizacaoAcessoService {

	@Autowired
	private ViajanteRepository repository;

	@Transactional
	public Viajante insert(Viajante entity) {

		entity.setDataInclusao(new Date());
		repository.save(entity);

		return entity;
	}

	private static final String LINHA = "\n";

	public ByteArrayInputStream gerarAutorizacao(Reserva reserva) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(getTitulo("AUTORIZAÇÃO DE LOCAÇÃO POR TEMPORADA"));
			document.add(getSubTitulo("Rua Rio de Janeiro, 50 – apto 617/618 - Edifício Bandeirantes"));
			document.add(getDadosProprietario());
			document.add(getDadosViajante(reserva));
			document.add(getPrazoLocacao(reserva));
			
			document.add(getTabelaOcupantes());
			document.add(getDataContrato());
			document.add(getAssinatura());

			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(PdfReportService.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	

	



	private Element getDadosProprietario() {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add("Eu, Fernando Augusto Machado de Oliveira, proprietário do apartamento 617-" + 
				"618, inscrito pelo CPF: 296.830.188-82, autorizo o sr (a) responsável pela " + 
				"locação:");
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		return paragraph;
	}
	
	private Element getDadosViajante(Reserva reserva) {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		paragraph.add(LINHA);
		Paragraph nome = new Paragraph("Nome: ",new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0)));
		paragraph.add(nome);
		paragraph.add("   " + reserva.getViajante().getNome());
		paragraph.add(LINHA);
		Paragraph email = new Paragraph("E-mail: ",new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0)));
		paragraph.add(email);
		paragraph.add("   " + reserva.getViajante().getEmail());
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add("e os ocupantes descritos abaixo a utilizar o apartamento no período de:");
		paragraph.add(LINHA);
		
		
		return paragraph;
	}

	private Element getTitulo(String titulo) {
		Paragraph paragraph = new Paragraph(titulo,
				new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		return paragraph;
	}

	private Element getSubTitulo(String titulo) {
		Paragraph paragraph = new Paragraph(titulo,
				new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0)));
		return paragraph;
	}

	

	private Element getPrazoLocacao(Reserva reserva) {
		Paragraph paragraph = new Paragraph();
		paragraph.add(LINHA);
		
		paragraph.add(LINHA);
		paragraph.add("Entrada:    ");
		
		String[] inicio = reserva.getDataEntradaFormatada().split(" ");
		Phrase dataInicio = new Phrase(inicio[0]);
		paragraph.add(dataInicio);
		String horaInicio = inicio[1];
		

		paragraph.add("   após    " + new Phrase(horaInicio));
		
		paragraph.add(LINHA);

		paragraph.add("Saída:      ");
		String[] termino = reserva.getDataSaidaFormatada().split(" ");
		Phrase dataTermino = new Phrase(termino[0]);
		paragraph.add(dataTermino);
		Phrase horaTermino = new Phrase(termino[1]);
		paragraph.add("   até as   " + horaTermino);
		
		
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(
				"A permanência do locatário no imóvel pode ensejar multas e indenização, nos termos da lei.");

		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}
	
	

	
	private Element getTabelaOcupantes() {
		try {
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);

			table.setWidths(new int[] { 1, 4, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("  ", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Nome", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Idade", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Documento", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			

			
			for(int i = 1; i < 11; i++) {
				
				PdfPCell cell;

				cell = new PdfPCell(new Phrase( String.valueOf(i)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("                         "));
//				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("                         "));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("                           "));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

			
			
			}
			
			
			
			return table;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Element getDataContrato() {
		Paragraph paragraph = new Paragraph();
		
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
	
		paragraph.add("Guarujá,  ");
		paragraph.add(ReservaDateUtils.getDataAtual());
		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}

	private Element getAssinatura() {
		Paragraph paragraph = new Paragraph();

		try {
			
			Image img = Image.getInstance("assinatura_small.jpg");
			
			paragraph.add(img);
			paragraph.add("____________________________________________");
			paragraph.add(LINHA);
			paragraph.add("Locador: Fernando Augusto Machado de Oliveira");

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return paragraph;
	}

	

}
