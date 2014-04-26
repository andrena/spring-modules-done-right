package de.andrena.et14.spring.konferenz;

import java.util.List;

import javax.transaction.Transactional;

public interface IKonferenzService {

	@Transactional
	List<Konferenz> ladeAlleKonferenzen();

}
