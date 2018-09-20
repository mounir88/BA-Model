package de.htwg.gib.egkterminal.model.medikationsplan.dosiereinheit;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.dosiereinheit.Dosiereinheiten.Schluesseltabelle.Dosiereinheit;

class DosiereinheitenTest {

	private static final File DOSIEREINHEITEN_XML = new File(
			"src/de/htwg/gib/egkterminal/model/medikationsplan/dosiereinheit/S_BMP_DOSIEREINHEIT_V1.01.xml");

	@Test
	void testLoadDosiereinheiten() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Dosiereinheiten.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Dosiereinheiten dosiereinheiten = null;
		dosiereinheiten = (Dosiereinheiten) unmarshaller.unmarshal(DOSIEREINHEITEN_XML);
		assertNotNull(dosiereinheiten.getSchluesseltabelle().getKodierungen());
	}

	@Test
	void testCode0EqualsMessbecher() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Dosiereinheiten.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Dosiereinheiten dosiereinheiten = null;
		dosiereinheiten = (Dosiereinheiten) unmarshaller.unmarshal(DOSIEREINHEITEN_XML);
		List<Dosiereinheit> dosiereinheitKodierung = dosiereinheiten.getSchluesseltabelle().getKodierungen();

		String code = "0";
		String bezeichnung = dosiereinheitKodierung.stream()
				.filter(dosiereinheit -> code.equals(dosiereinheit.getCode())).map(Dosiereinheit::getEinheit).findAny()
				.orElse("");
		assertEquals("Messbecher", bezeichnung);
	}

}
