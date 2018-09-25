package de.htwg.gib.egkterminal.model.medikationsplan.testpaket;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.Medikation;
import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;

public class ListMedikation {

	private static final String TESTPAKET_PATH = "src/de/htwg/gib/egkterminal/model/medikationsplan/testpaket";
	private static List<File> testFiles;
	static TreeSet<String> pzn = new TreeSet<>();

	public static void main(String[] args) throws JAXBException {
		testFiles = Arrays.asList(new File(TESTPAKET_PATH).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		}));
		JAXBContext jc = JAXBContext.newInstance(MedikationsPlan.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		testFiles.forEach(testFile -> {
			MedikationsPlan plan = null;
			try {

				plan = (MedikationsPlan) unmarshaller.unmarshal(testFile);
				plan.getBlock().forEach(block -> {
					block.getMedikationFreitextRezeptur().stream().filter(item -> (item instanceof Medikation))
							.forEach(item -> {
								Medikation medikation = (Medikation) item;
								if (medikation.getPharmazentralnummer() != null) {
									pzn.add(medikation.getPharmazentralnummer().toString());
								}
							});
				});
			} catch (JAXBException e) {
			}

		});
		System.out.println("count=" + pzn.size());
		pzn.forEach(pzn -> System.out.println(pzn));
	}

}
