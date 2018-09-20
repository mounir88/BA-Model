package de.htwg.gib.egkterminal.model.medikationsplan.testpaket;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class FixEncoding {

	public static void main(String[] args) {
		String bmpXmlEncoding = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!DOCTYPE MP>";
		String testPaketPath = "src/de/htwg/gib/egkterminal/model/medikationsplan/testpaket";
		List<File> testFiles = Arrays.asList(new File(testPaketPath).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		}));
		testFiles.forEach(file -> {
			try {
				String fileContent = new String(Files.readAllBytes(file.toPath()), Charset.forName("ISO-8859-1"));
				try (PrintStream out = new PrintStream(file, Charset.forName("ISO-8859-1"))) {
					out.print(bmpXmlEncoding + fileContent);
					System.out.println("fixed: " + file.getName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

	}

}
