package de.andrena.et14.spring.vortrag;

import java.util.List;

import javax.transaction.Transactional;

import de.andrena.et14.spring.konferenz.Konferenz;

public interface IVortragService {

	@Transactional
	Vortrag erstelleVortrag(Vortrag vortrag);

	@Transactional
	List<Vortrag> ladeAlleVortraege(Konferenz konferenz);

	@Transactional
	void informiereVortragende(Konferenz konferenz);

}
