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
public class ContratoService {

	@Autowired
	private ViajanteRepository repository;

	@Transactional
	public Viajante insert(Viajante entity) {

		entity.setDataInclusao(new Date());
		repository.save(entity);

		return entity;
	}

	private static final String LINHA = "\n";

	public ByteArrayInputStream gerarContrato(Reserva reserva) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			Paragraph titulo = new Paragraph("CONTRATO DE LOCAÇÃO POR TEMPORADA");
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.add("\n\n");

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(getTitulo("CONTRATO DE LOCAÇÃO POR TEMPORADA"));
			document.add(getSubTitulo("I. IDENTIFICAÇÃO DAS PARTES"));
			document.add(getIdentificacaoPartes(reserva));
			document.add(getSubTitulo("II. DO IMÓVEL LOCADO"));
			document.add(getImovelLocado());
			document.add(getSubTitulo("III. DO REGULAMENTO INTERNO DO EDIFICIO"));
			document.add(getRegulamentoInterno());
			document.add(getSubTitulo("IV. DO PRAZO DE LOCAÇÃO"));
			document.add(getPrazoLocacao(reserva));
			document.add(getSubTitulo("V. DO PREÇO E DO PAGAMENTO"));
			document.add(getPrecoPagamento(reserva));
			document.add(getSubTitulo("VI. DO CANCELAMENTO DA LOCAÇÃO"));
			document.add(getCancelamentoLocacao());
			document.add(getDataContrato());
			document.add(getAssinatura());

			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(PdfReportService.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
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

	private Element getIdentificacaoPartes(Reserva reserva) {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		paragraph.add(LINHA);
		paragraph.add("As pessoas abaixo identificadas firmam o presente contrato de locação por "
				+ "temporada, nos termos da legislação brasileira, em especial, de acordo com a "
				+ "Lei de Locação (Lei nº 8.245/91):");
		paragraph.add(LINHA);
		paragraph.add("Locador: Fernando Augusto Machado de Oliveira, CPF: 296.830.188-82, "
				+ "residente na rua Vicente Aprígio da Silva,600 – 08431-090 – São Paulo - SP");
		paragraph.add(LINHA);
		paragraph.add("Endereço eletrônico: f19@uol.com.br");
		paragraph.add(LINHA);
		paragraph.add("Responsável locação:");
		paragraph.add(LINHA);
		paragraph.add("Nome: " + reserva.getViajante().getNome());
		paragraph.add(LINHA);
		paragraph.add("E-mail: " + reserva.getViajante().getEmail());
		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}

	private Element getImovelLocado() {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.add(LINHA);
		paragraph.add("O imóvel locado está situado na rua Rio de Janeiro, 50 – apto 617/618 – Centro "
				+ "– Guarujá – SP. Trata-se de imóvel com as seguintes características:");
		paragraph.add(LINHA);
		paragraph.add("Possui ambiente com 2 quartos, sendo 1 com suíte, sala, cozinha, banheiro. O "
				+ "apartamento possui 1 cama de casal, 2 bicamas, 2 colchões de solteiro extras, "
				+ "mesa com 6 cadeiras, banheiro social com box.");
		paragraph.add(LINHA);
		paragraph.add("Ainda possui TV 32' na sala e de 20' no quarto, modem Wi-fi, secadora de "
				+ "roupas, 3 ventiladores, armário para acomodação de bagagens, cômoda, "
				+ "micro-ondas, fogão de quatro bocas, geladeira, liquidificador, sanduicheira grill "
				+ "e utensílios de cozinha.");
		paragraph.add(LINHA);
		paragraph.add("O edifício possui portaria 24 horas, monitoramento por câmeras, WI-FI no hall "
				+ "de entrada, 3 elevadores, serviço de praia com cadeiras e 2 guarda-sóis (a "
				+ "solicitar na recepção).");
		paragraph.add(LINHA);
		paragraph.add("Observações:");
		paragraph.add(LINHA);
		paragraph.add("É necessário levar roupa de cama, mesa e banho");
		paragraph.add(LINHA);
		paragraph.add("É recomendável levar travesseiros");
		paragraph.add(LINHA);
		paragraph.add("NÃO possui garagem, mas há vagas nas ruas próximas e " + "estacionamentos");
		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}

	private Element getRegulamentoInterno() {
		Paragraph paragraph = new Paragraph();
		paragraph.add(LINHA);
		paragraph.add("O imóvel pode acomodar no máximo 8 pessoas. Sendo que crianças até " + "5 anos não contam.");
		paragraph.add(LINHA);
		paragraph.add("Banhistas devem entrar e sair pela porta lateral e usar o elevador "
				+ "reservado para banhistas, sem areia no corpo");
		paragraph.add(LINHA);
		paragraph.add(
				"Estacionamento é reservado para embarque e desembarque de " + "bagagem e é limitado a 15 minutos");
		paragraph.add(LINHA);
		paragraph.add("Animais devem ser levados no colo e/ou com coleira. Não será tolerada "
				+ "sujeira produzida por animais em áreas comuns");
		paragraph.add(LINHA);
		paragraph.add("Latidos, gritos, e abusos com aparelhos sonoros, que incomodem os "
				+ "demais condôminos, não serão tolerados em horário nenhum");
		paragraph.add(LINHA);
		paragraph.add("É vetado pendurar roupas ou quaisquer outros objetos nas janelas");
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}

	private Element getPrazoLocacao(Reserva reserva) {
		Paragraph paragraph = new Paragraph();
		paragraph.add(LINHA);
		paragraph.add("A locação tem:");
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add("início:     ");
		
		String[] inicio = reserva.getDataEntradaFormatada().split(" ");
		Phrase dataInicio = new Phrase(inicio[0]);
		paragraph.add(dataInicio);
		String horaInicio = inicio[1];
		

		paragraph.add("   após    " + new Phrase(horaInicio));
		
		paragraph.add(LINHA);

		paragraph.add("término: ");
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
	
	private Element getPrecoPagamento(Reserva reserva) {
		Paragraph paragraph = new Paragraph();
		paragraph.add(LINHA);
		String textoSinal = atribuirTextoPagamentoSinal(reserva);
		paragraph.add(textoSinal);
		paragraph.add(LINHA);
		
		String textoPagamentoRestante = atribuirTextoRestantePagamento(reserva);
		paragraph.add(textoPagamentoRestante);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add("Com isso totalizando, o locatário pagará pela importância de: ");
		paragraph.add(ReservaDateUtils.formatarValorMonetario(reserva.getValorTotal()));
		paragraph.add(", o qual já está incluso a taxa de limpeza.");
		paragraph.add(LINHA);
		
		return paragraph;
	}

	private String atribuirTextoPagamentoSinal(Reserva reserva) {

		StringBuilder textoSinal = new StringBuilder();
		if (reserva.getLancamentos() != null && !reserva.getLancamentos().isEmpty()) {

			Lancamento sinal = reserva.getLancamentos().get(0);

			if (sinal.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {

				if (sinal.getValorLancamento().equals(reserva.getValorTotal())) {
					textoSinal.append("O locatário efetuou o pagamento no valor de: ");
					textoSinal.append(ReservaDateUtils.formatarValorMonetario(sinal.getValorLancamento()));
				} else {
					textoSinal.append("O locatário efetuou o pagamento no valor de: ");
					textoSinal.append(ReservaDateUtils.formatarValorMonetario(sinal.getValorLancamento()));
					textoSinal.append(" a titulo de sinal");
				}
			}

			if (sinal.getSituacaoPagamento().equals(SituacaoPagamento.PENDENTE)) {

				textoSinal.append("O locatário efetuará o pagamento no valor de: ");
				textoSinal.append(ReservaDateUtils.formatarValorMonetario(sinal.getValorLancamento()));
				textoSinal.append(" a titulo de sinal");

			}

		}
		return textoSinal.toString();
	}
	
	private String atribuirTextoRestantePagamento(Reserva reserva) {
		StringBuilder texto = new StringBuilder();
		
		if (reserva.getLancamentos() != null && !reserva.getLancamentos().isEmpty()
				&& reserva.getLancamentos().size() > 1) {

			Lancamento lancamento = reserva.getLancamentos().get(1);

			if (lancamento.getSituacaoPagamento().equals(SituacaoPagamento.PENDENTE)) {

				if (lancamento.getDataLancamento().equals(reserva.getDataEntrada())) {
					texto.append("o restante de ");
					texto.append(ReservaDateUtils.formatarValorMonetario(lancamento.getValorLancamento()));
					texto.append(" será pago na recepção ao ingressar no imóvel.");
					
				} else {
					texto.append("o restante de ");
					texto.append(ReservaDateUtils.formatarValorMonetario(lancamento.getValorLancamento()));
					texto.append(" pago até o dia ");
					texto.append(ReservaDateUtils.formatarDataLocal(lancamento.getDataLancamento()));
				}
			}

			
		}

		
		return texto.toString();
	}

	private Element getCancelamentoLocacao() {
		Paragraph paragraph = new Paragraph();
		paragraph.add(LINHA);
		paragraph.add("O locatário pode cancelar a locação mediante comunicado por escrito através "
				+ "do e-mail do locador ao endereço contido na descrição das partes. O "
				+ "cancelamento implica nas seguintes penalidades que variam de acordo com a "
				+ "antecedência do aviso de cancelamento:");
		paragraph.add(LINHA);
		paragraph.add("Abaixo segue a tabela referente a devolução do valor depositado em caso de "
				+ "desistência por parte do locatário");
		paragraph.add(LINHA);

		paragraph.add(getTabelaCancelamento());

		paragraph.add(LINHA);
		paragraph.add("Em caso de imprevistos, existe a possibilidade de trocar a data de locação, "
				+ "mediante acordo com o locatário e agenda disponível.");
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		paragraph.add(LINHA);
		return paragraph;
	}

	private Element getTabelaCancelamento() {
		try {
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(60);

			table.setWidths(new int[] { 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("ANTECEDÊNCIA", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("DEVOLUÇÃO", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			PdfPCell cell;

			cell = new PdfPCell(new Phrase("Mais de 7 dias da locação"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("100 % do valor depositado"));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Entre 7 e 2 dias da locação"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("50 % do valor depositado"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Menos de 2 dias da locação"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("0 % do valor depositado"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			return table;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Element getDataContrato() {
		Paragraph paragraph = new Paragraph();
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
