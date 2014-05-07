package de.andrena.et14.spring.vortrag;

import java.util.List;

import de.andrena.et14.spring.konferenz.Konferenz;
import de.andrena.et14.spring.konferenz.KonferenzListener;

public class BenachrichtigeReferentenBeiKonferenzAenderung implements KonferenzListener {

	private IVortragService vortragService;

	@Override
	public void konferenzGeaendert(Konferenz konferenz) {
		List<Vortrag> alleVortraege = vortragService.ladeAlleVortraege(konferenz);
		for (Vortrag vortrag : alleVortraege) {
			System.out.println("Sende EMail an alle Referenten von " + vortrag.getTitel());
		}
	}

	public void setVortragService(IVortragService vortragService) {
		this.vortragService = vortragService;
	}
}
