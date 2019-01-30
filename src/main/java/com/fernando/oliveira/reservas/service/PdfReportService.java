package com.fernando.oliveira.reservas.service;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fernando.oliveira.reservas.domain.Reserva;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfReportService {

	@Value("${contrato.titulo}")
	private String contratoTitulo;
	
	@Value("${contrato.item01.titulo}")
	private String contratoItem01Titulo;
	
	@Value("${contrato.item01.paragrafo.01}")
	private String contratoItem01Paragrafo01;
	
	@Value("${contrato.item01.paragrafo.02}")
	private String contratoItem01Paragrafo02;
	
	@Value("${contrato.item01.paragrafo.03}")
	private String contratoItem01Paragrafo03;
	
	@Value("${contrato.item01.paragrafo.04}")
	private String contratoItem01Paragrafo04;
	
	@Value("${contrato.item01.paragrafo.05}")
	private String contratoItem01Paragrafo05;
	
	
	//item 02
	@Value("${contrato.item02.titulo}")
	private String contratoItem02Titulo;
	
	@Value("${contrato.item02.paragrafo.01}")
	private String contratoItem02Paragrafo01;
	
	@Value("${contrato.item02.paragrafo.02}")
	private String contratoItem02Paragrafo02;
	
	@Value("${contrato.item02.paragrafo.03}")
	private String contratoItem02Paragrafo03;
	
	@Value("${contrato.item02.paragrafo.04}")
	private String contratoItem02Paragrafo04;
	
	@Value("${contrato.item02.paragrafo.05}")
	private String contratoItem02Paragrafo05;
	
	@Value("${contrato.item02.paragrafo.06}")
	private String contratoItem02Paragrafo06;
	
	@Value("${contrato.item02.paragrafo.07}")
	private String contratoItem02Paragrafo07;
	
	@Value("${contrato.item02.paragrafo.08}")
	private String contratoItem02Paragrafo08;
	
	@Value("${contrato.item02.paragrafo.09}")
	private String contratoItem02Paragrafo09;
	
	//item 03
	@Value("${contrato.item03.titulo}")
	private String contratoItem03Titulo;
	
	@Value("${contrato.item03.paragrafo.01}")
	private String contratoItem03Paragrafo01;
	
	@Value("${contrato.item03.paragrafo.02}")
	private String contratoItem03Paragrafo02;
	
	@Value("${contrato.item03.paragrafo.03}")
	private String contratoItem03Paragrafo03;
	
	@Value("${contrato.item03.paragrafo.04}")
	private String contratoItem03Paragrafo04;
	
	@Value("${contrato.item03.paragrafo.05}")
	private String contratoItem03Paragrafo05;
	
	@Value("${contrato.item03.paragrafo.06}")
	private String contratoItem03Paragrafo06;
	
	//item 04
	@Value("${contrato.item04.titulo}")
	private String contratoItem04Titulo;
		
	@Value("${contrato.item04.paragrafo.01}")
	private String contratoItem04Paragrafo01;
	
	@Value("${contrato.item04.paragrafo.02}")
	private String contratoItem04Paragrafo02;
	
	@Value("${contrato.item04.paragrafo.03}")
	private String contratoItem04Paragrafo03;
	
	@Value("${contrato.item04.paragrafo.04}")
	private String contratoItem04Paragrafo04;
	
	//item 05
//		@Value("${contrato.item05.titulo}")
//		private String contratoItem05Titulo;
//			
//		@Value("${contrato.item05.paragrafo.01}")
//		private String contratoItem05Paragrafo01;
	

	public ByteArrayInputStream gerarContrato(Reserva reserva) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			Paragraph titulo = new Paragraph(contratoTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.add("\n\n");
			
			Paragraph item01 = new Paragraph(contratoItem01Titulo);
			item01.setAlignment(Element.ALIGN_LEFT);
			item01.add("\n");
			item01.add(contratoItem01Paragrafo01);
			item01.add("\n");
			item01.add(contratoItem01Paragrafo02);
			item01.add("\n");
			item01.add(contratoItem01Paragrafo03);
			item01.add("\n");
			item01.add(contratoItem01Paragrafo04);
			item01.add("\n");
			item01.add(contratoItem01Paragrafo05);

			Paragraph identificacaoPartes = new Paragraph(contratoItem01Titulo);
			identificacaoPartes.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph nomeResponsavel = new Paragraph("Nome: " + reserva.getViajante().getNome());
			Paragraph emailResponsavel = new Paragraph("E-mail: " + reserva.getViajante().getEmail());

//			Paragraph imovelLocado = new Paragraph(IMOVEL_LOCADO);
//			imovelLocado.setAlignment(Element.ALIGN_LEFT);

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(60);
			table.setWidths(new int[] { 1, 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Population", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			// for (City city : cities) {
			//
			// PdfPCell cell;
			//
			// cell = new PdfPCell(new Phrase(city.getId().toString()));
			// cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// table.addCell(cell);
			//
			// cell = new PdfPCell(new Phrase(city.getName()));
			// cell.setPaddingLeft(5);
			// cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			// cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			// table.addCell(cell);
			//
			// cell = new PdfPCell(new Phrase(String.valueOf(city.getPopulation())));
			// cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			// cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			// cell.setPaddingRight(5);
			// table.addCell(cell);
			// }

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(titulo);
			document.add(item01);
//			document.add(identificacaoPartes);
//			document.add(nomeResponsavel);
//			document.add(emailResponsavel);
//			document.add(imovelLocado);
			// document.add(table);

			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(PdfReportService.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public void gerarPDF() {
		File file = new File("arquivo.pdf");
		Document doc = new Document();
		doc.open();

		Paragraph p = new Paragraph();
		p.setAlignment(1);
		try {
			doc.add(p);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));

			doc.close();
			Desktop.getDesktop().open(file);
		} catch (Exception e) {

		}

	}
}
