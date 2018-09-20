package de.htwg.gib.egkterminal.model.medikationsplan.unmarshal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;

class MedikationsplanTest {

	static String testPaketPath;
	static List<File> testFiles;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testPaketPath = "src/de/htwg/gib/egkterminal/model/medikationsplan/testpaket";
		testFiles = Arrays.asList(new File(testPaketPath).listFiles());
	}

	@Test
	void testInstanceIdValid() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(MedikationsPlan.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		testFiles.forEach(testFile -> {
			MedikationsPlan plan = null;
			try {
				plan = (MedikationsPlan) unmarshaller.unmarshal(testFile);
			} catch (JAXBException e) {
				fail(e.getMessage());
			}
			assertTrue(plan.getInstanzId().length() == 32);
			assertNotNull(plan.getPatient());
			assertNotNull(plan.getErsteller());
			assertNotNull(plan.getBlock());
			assertNotNull(plan.getVersionsnummer());
			assertNotNull(plan.getSprachLaenderkennzeichen());
		});

	}

}
