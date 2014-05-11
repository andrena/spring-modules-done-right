package de.andrena.et14.spring.vortrag;

import de.andrena.et14.spring.konferenz.Konferenz;
import de.andrena.et14.spring.konferenz.KonferenzListener;

public class BenachrichtigeReferentenBeiKonferenzAenderung implements KonferenzListener {

	private IVortragService vortragService;

	@Override
	public void konferenzGeaendert(Konferenz konferenz) {
		vortragService.informiereVortragende(konferenz);
	}

	public void setVortragService(IVortragService vortragService) {
		this.vortragService = vortragService;
	}
}
