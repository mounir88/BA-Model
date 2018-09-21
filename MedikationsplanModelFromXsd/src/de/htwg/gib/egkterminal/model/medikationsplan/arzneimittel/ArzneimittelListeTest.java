package de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe.Arzneimittel;

class ArzneimittelListeTest {

	private static final File ARZNEIMITTEL_XML = new File(
			"src/de/htwg/gib/egkterminal/model/medikationsplan/arzneimittel/ArzneimittelListe.xml");

	@Test
	void testLoadDarreichungsformen() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(ArzneimittelListe.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		ArzneimittelListe arzneimittel = (ArzneimittelListe) unmarshaller.unmarshal(ARZNEIMITTEL_XML);
		assertNotNull(arzneimittel.getArzneimittelListe());
	}

	@Test
	void testGetArzneimittelForPharmazentralnummer() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(ArzneimittelListe.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		List<Arzneimittel> arzneimittelListe = ((ArzneimittelListe) unmarshaller.unmarshal(ARZNEIMITTEL_XML))
				.getArzneimittelListe();

		String pharmazentralnummer = "1578681";
		Arzneimittel arzneimittel = arzneimittelListe.stream()
				.filter(arznei -> pharmazentralnummer.equals(arznei.getPharmazentralnummer())).findAny().orElse(null);
		assertEquals("Bepanthen", arzneimittel.getHandelsname());
	}
}
