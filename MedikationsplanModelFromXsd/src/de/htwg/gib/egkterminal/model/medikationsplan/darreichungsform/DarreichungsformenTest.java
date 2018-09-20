package de.htwg.gib.egkterminal.model.medikationsplan.darreichungsform;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.darreichungsform.Darreichungsformen.Schluesseltabelle.Darreichungsform;

class DarreichungsformenTest {

	private static final File DARREICHUNGSFORMEN_XML = new File(
			"src/de/htwg/gib/egkterminal/model/medikationsplan/darreichungsform/S_BMP_DARREICHUNGSFORM_V1.02.xml");

	@Test
	void testLoadDarreichungsformen() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Darreichungsformen.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Darreichungsformen darreichungsformen = (Darreichungsformen) unmarshaller.unmarshal(DARREICHUNGSFORMEN_XML);
		assertNotNull(darreichungsformen.getSchluesseltabelle().getKodierungen());
	}

	@Test
	void testCodeBonEqualsBonbons() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Darreichungsformen.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Darreichungsformen darreichungsformen = (Darreichungsformen) unmarshaller.unmarshal(DARREICHUNGSFORMEN_XML);
		List<Darreichungsform> darreichungsformKodierungen = darreichungsformen.getSchluesseltabelle().getKodierungen();

		String code = "BON";
		String form = darreichungsformKodierungen.stream().filter(dosiereinheit -> code.equals(dosiereinheit.getCode()))
				.map(Darreichungsform::getForm).findAny().orElse("");
		assertEquals("Bonbons", form);
	}

	@Test
	void testCodeNotFound() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Darreichungsformen.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Darreichungsformen darreichungsformen = (Darreichungsformen) unmarshaller.unmarshal(DARREICHUNGSFORMEN_XML);
		List<Darreichungsform> darreichungsformKodierungen = darreichungsformen.getSchluesseltabelle().getKodierungen();

		String code = "ThisIsAnInvalidCode";
		String form = darreichungsformKodierungen.stream().filter(dosiereinheit -> code.equals(dosiereinheit.getCode()))
				.map(Darreichungsform::getForm).findAny().orElse("");
		assertEquals("", form);
	}

}
