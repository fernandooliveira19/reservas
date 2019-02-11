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
