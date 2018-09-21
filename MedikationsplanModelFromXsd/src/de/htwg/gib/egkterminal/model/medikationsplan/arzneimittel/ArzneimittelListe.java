package de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ArzneimittelListe {

	@XmlElement(name = "arzneimittel", required = true)
	private List<Arzneimittel> arzneimittelListe;

	public List<Arzneimittel> getArzneimittelListe() {
		return arzneimittelListe;
	}

	public void setArzneimittelListe(List<Arzneimittel> arzneimittelListe) {
		this.arzneimittelListe = arzneimittelListe;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Arzneimittel {

		private String pharmazentralnummer;
		private String handelsname;
		@XmlElement(name = "arzneistoff")
		private List<Arzneistoff> arzneistoffe;

		public String getPharmazentralnummer() {
			return pharmazentralnummer;
		}

		public void setPharmazentralnummer(String pharmazentralnummer) {
			this.pharmazentralnummer = pharmazentralnummer;
		}

		public String getHandelsname() {
			return handelsname;
		}

		public void setHandelsname(String handelsname) {
			this.handelsname = handelsname;
		}

		public List<Arzneistoff> getArzneistoffe() {
			return arzneistoffe;
		}

		public void setArzneistoffe(List<Arzneistoff> arzneistoffe) {
			this.arzneistoffe = arzneistoffe;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Arzneistoff {

			private String wirkstoff;
			private String wirkstaerke;

			public String getWirkstoff() {
				return wirkstoff;
			}

			public void setWirkstoff(String wirkstoff) {
				this.wirkstoff = wirkstoff;
			}

			public String getWirkstaerke() {
				return wirkstaerke;
			}

			public void setWirkstaerke(String wirkstaerke) {
				this.wirkstaerke = wirkstaerke;
			}

		}

	}

}
