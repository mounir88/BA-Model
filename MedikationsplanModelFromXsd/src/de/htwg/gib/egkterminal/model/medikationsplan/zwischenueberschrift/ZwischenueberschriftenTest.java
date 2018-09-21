package de.htwg.gib.egkterminal.model.medikationsplan.zwischenueberschrift;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.zwischenueberschrift.Zwischenueberschriften.Schluesseltabelle.Zwischenueberschrift;

class ZwischenueberschriftenTest {

	private static final File ZWISCHENUEBERSCHRIFTEN_XML = new File(
			"src/de/htwg/gib/egkterminal/model/medikationsplan/zwischenueberschrift/S_BMP_ZWISCHENUEBERSCHRIFT_V1.01.xml");

	@Test
	void testLoadZwischenueberschriften() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Zwischenueberschriften.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Zwischenueberschriften zwischenueberschriften = (Zwischenueberschriften) unmarshaller
				.unmarshal(ZWISCHENUEBERSCHRIFTEN_XML);
		assertNotNull(zwischenueberschriften.getSchluesseltabelle().getKodierungen());
	}

	@Test
	void testCode418EqualsSelbstmedikation() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Zwischenueberschriften.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Zwischenueberschriften zwischenueberschriften = (Zwischenueberschriften) unmarshaller
				.unmarshal(ZWISCHENUEBERSCHRIFTEN_XML);
		List<Zwischenueberschrift> zwischenueberschriftKodierungen = zwischenueberschriften.getSchluesseltabelle()
				.getKodierungen();

		String code = "418";
		String ueberschrift = zwischenueberschriftKodierungen.stream()
				.filter(dosiereinheit -> code.equals(dosiereinheit.getCode()))
				.map(Zwischenueberschrift::getUeberschrift).findAny().orElse("");
		assertEquals("Selbstmedikation", ueberschrift);
	}

	@Test
	void testCodeNotFound() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Zwischenueberschriften.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Zwischenueberschriften zwischenueberschriften = (Zwischenueberschriften) unmarshaller
				.unmarshal(ZWISCHENUEBERSCHRIFTEN_XML);
		List<Zwischenueberschrift> zwischenueberschriftKodierungen = zwischenueberschriften.getSchluesseltabelle()
				.getKodierungen();

		String code = "ThisIsAnInvalidCode";
		String form = zwischenueberschriftKodierungen.stream()
				.filter(dosiereinheit -> code.equals(dosiereinheit.getCode()))
				.map(Zwischenueberschrift::getUeberschrift).findAny().orElse("");
		assertEquals("", form);
	}

}
