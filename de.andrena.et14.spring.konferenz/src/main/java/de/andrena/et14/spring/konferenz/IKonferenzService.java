package de.andrena.et14.spring.konferenz;

import java.util.List;

import javax.transaction.Transactional;

import de.andrena.et14.spring.common.HttpInvoker;

public interface IKonferenzService extends HttpInvoker {

	@Transactional
	List<Konferenz> ladeAlleKonferenzen();

	@Transactional
	Konferenz erstelleKonferenz(Konferenz konferenz);

	@Transactional
	void aendereKonferenz(Konferenz konferenz);

}
