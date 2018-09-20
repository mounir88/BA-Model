package de.htwg.gib.egkterminal.model.medikationsplan.unmarshal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;

class MedikationsplanTest {

	private static final String TESTPAKET_PATH = "src/de/htwg/gib/egkterminal/model/medikationsplan/testpaket";
	private static List<File> testFiles;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testFiles = Arrays.asList(new File(TESTPAKET_PATH).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		}));
	}

	@Test
	void testMedikationsplanValid() throws JAXBException {
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
