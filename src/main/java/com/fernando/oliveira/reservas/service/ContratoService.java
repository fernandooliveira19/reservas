package com.fernando.oliveira.reservas.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.oliveira.reservas.domain.Lancamento;
import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.enums.FormaPagamento;
import com.fernando.oliveira.reservas.domain.enums.SituacaoPagamento;
import com.fernando.oliveira.reservas.domain.utils.ReservaUtils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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

	private static final String LINHA = "\n";

	// @Autowired
	// private SmtpEmailService smtpEmailService;

	public ByteArrayInputStream gerarContrato(Reserva reserva) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

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
		paragraph.add(LINHA);
		Paragraph responsavel = new Paragraph("Responsável locação:",
				new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0)));
		paragraph.add(responsavel);

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
		paragraph.add("Ainda possui TV 32' na sala e duas 32' nos quartos, modem Wi-fi, secadora de "
				+ "roupas, 3 ventiladores, armário para acomodação de bagagens, cômoda, panela de arroz, "
				+ "micro-ondas, fogão de quatro bocas, geladeira, liquidificador, sanduicheira grill "
				+ "e utensílios de cozinha.");
		paragraph.add(LINHA);
		paragraph.add("O edifício possui portaria 24 horas, monitoramento por câmeras, WI-FI no hall "
				+ "de entrada, 3 elevadores, serviço de praia com cadeiras e 2 guarda-sóis (a "
				+ "solicitar na recepção) (*).");
		paragraph.add(LINHA);
		
		paragraph.add(LINHA);
		
		paragraph.add(LINHA);
		Paragraph observacao = new Paragraph("Observações:",
				new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0)));
		paragraph.add(observacao);

		Font zapfdingbats = new Font();
		Chunk bullet = new Chunk("\u2022", zapfdingbats);
		paragraph.add(LINHA);
		paragraph.add(bullet + "  É necessário levar roupa de cama, mesa e banho");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  É recomendável levar travesseiros");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  NÃO possui garagem, mas há vagas nas ruas próximas e " + "estacionamentos");
		paragraph.add(LINHA);
		paragraph.add(bullet + " (*) No momento, devido as regras da prefeitura em relação ao COVID-19, não está "
				+ "sendo permitido a montagem de guarda-sóis e cadeiras de praia na faixa de areia, "
				+ "portanto o serviço de praia encontra-se indisponível." );
		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}

	private Element getRegulamentoInterno() {
		Paragraph paragraph = new Paragraph();

		Font zapfdingbats = new Font();
		Chunk bullet = new Chunk("\u2022", zapfdingbats);

		paragraph.add(LINHA);
		paragraph.add(bullet + "  O imóvel pode acomodar no máximo 8 pessoas. Sendo que crianças até "
				+ "5 anos não contam.");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  Banhistas devem entrar e sair pela porta lateral e usar o elevador "
				+ "reservado para banhistas, sem areia no corpo");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  Estacionamento é reservado para embarque e desembarque de "
				+ "bagagem e é limitado a 15 minutos");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  Animais devem ser levados no colo e/ou com coleira. Não será tolerada "
				+ "sujeira produzida por animais em áreas comuns");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  Latidos, gritos, e abusos com aparelhos sonoros, que incomodem os "
				+ "demais condôminos, não serão tolerados em horário nenhum");
		paragraph.add(LINHA);
		paragraph.add(bullet + "  É vetado pendurar roupas ou quaisquer outros objetos nas janelas");
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
		paragraph.add("A permanência do locatário no imóvel pode ensejar multas e indenização, nos termos da lei.");

		paragraph.add(LINHA);
		paragraph.add(LINHA);

		return paragraph;
	}

	private Element getPrecoPagamento(Reserva reserva) {
		Paragraph paragraph = new Paragraph();
		paragraph.add(LINHA);

		boolean pagamentoSite = isPagamentoSite(reserva);

		if (pagamentoSite) {

			String textoPagamentoSite = atribuirTextoPagamentoSite(reserva);
			paragraph.add(textoPagamentoSite);
			paragraph.add(LINHA);
			paragraph.add(LINHA);
			paragraph.add(LINHA);
			paragraph.add(LINHA);

		} else {

			String textoSinal = atribuirTextoPagamentoSinal(reserva);
			paragraph.add(textoSinal);
			paragraph.add(LINHA);
			paragraph.add(LINHA);

			String textoPagamentoRestante = atribuirTextoRestantePagamento(reserva);
			paragraph.add(textoPagamentoRestante);

			paragraph.add(LINHA);
			paragraph.add(LINHA);
			paragraph.add("Com isso totalizando, o locatário pagará pela importância de: ");
			paragraph.add(ReservaUtils.formatarValorMonetario(reserva.getValorTotal()));
			paragraph.add(", o qual já está incluso a taxa de limpeza.");
			paragraph.add(LINHA);
		}

		return paragraph;
	}

	private String atribuirTextoPagamentoSite(Reserva reserva) {
		StringBuilder texto = new StringBuilder();
		texto.append("O locatário efetuou o pagamento no valor de:  ");
		texto.append(ReservaUtils.formatarValorMonetario(reserva.getValorTotal()));
		texto.append("  através do site www.aluguetemporada.com.br. Esse valor já está incluso a taxa de limpeza e a "
				+ "taxa de serviço do site.");
		return texto.toString();
	}

	private boolean isPagamentoSite(Reserva reserva) {

		if (reserva.getLancamentos() != null && reserva.getLancamentos().size() > 0) {

			for (Lancamento lancamento : reserva.getLancamentos()) {

				if (lancamento.getFormaPagamento().equals(FormaPagamento.SITE)) {
					return true;
				}

			}

		}
		return false;
	}

	private String atribuirTextoPagamentoSinal(Reserva reserva) {

		StringBuilder textoSinal = new StringBuilder();
		if (reserva.getLancamentos() != null && !reserva.getLancamentos().isEmpty()) {

			if (reserva.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {
				
				textoSinal.append("O locatário efetuou o pagamento no valor de: ");
				textoSinal.append(ReservaUtils.formatarValorMonetario(reserva.getValorTotal()));

			} else {

				Lancamento sinal = reserva.getLancamentos().get(0);

				if (sinal.getSituacaoPagamento().equals(SituacaoPagamento.PAGO)) {

					if (sinal.getValorLancamento().equals(reserva.getValorTotal())) {
						textoSinal.append("O locatário efetuou o pagamento no valor de: ");
						textoSinal.append(ReservaUtils.formatarValorMonetario(sinal.getValorLancamento()));
					} else {
						textoSinal.append("O locatário efetuou o pagamento no valor de: ");
						textoSinal.append(ReservaUtils.formatarValorMonetario(sinal.getValorLancamento()));
						textoSinal.append(" a titulo de sinal");
					}
				}

				if (sinal.getSituacaoPagamento().equals(SituacaoPagamento.PENDENTE)) {

					textoSinal.append("O locatário efetuará o pagamento no valor de: ");
					textoSinal.append(ReservaUtils.formatarValorMonetario(sinal.getValorLancamento()));
					textoSinal.append(" a titulo de sinal");

				}
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

				if (!ReservaUtils.isLancamentoAntesCheckIn(lancamento.getDataLancamento(), reserva.getDataEntrada())
						&& lancamento.getFormaPagamento().equals(FormaPagamento.LOCAL)) {
					texto.append("O restante de ");
					texto.append(ReservaUtils.formatarValorMonetario(lancamento.getValorLancamento()));
					texto.append(" será pago na recepção ao ingressar no imóvel.");

				} else {
					texto.append("o restante de ");
					texto.append(ReservaUtils.formatarValorMonetario(lancamento.getValorLancamento()));
					texto.append(" será pago até o dia ");
					texto.append(ReservaUtils.formatarDataLocal(lancamento.getDataLancamento()));

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
		paragraph.add(ReservaUtils.getDataAtual());
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

	public void sendContract(Reserva reserva) {
		// smtpEmailService.sendOrderConfirmationHtmlEmail(reserva);
	}

}
