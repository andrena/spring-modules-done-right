package de.andrena.et14.spring.konferenz;

import java.util.ArrayList;
import java.util.List;

public class KonferenzService implements IKonferenzService {

	private KonferenzDao konferenzDao;
	private List<KonferenzListener> listeners;

	@Override
	public List<Konferenz> ladeAlleKonferenzen() {
		List<KonferenzEntity> entities = konferenzDao.loadAll();
		List<Konferenz> result = new ArrayList<>();
		for (KonferenzEntity entity : entities) {
			result.add(entity.toDto());
		}
		return result;
	}

	@Override
	public Konferenz erstelleKonferenz(Konferenz konferenz) {
		KonferenzEntity konferenzEntity = new KonferenzEntity();
		konferenzEntity.updateFrom(konferenz);
		konferenzDao.persist(konferenzEntity);
		return konferenzEntity.toDto();
	}

	@Override
	public void aendereKonferenz(Konferenz konferenz) {
		Long id = konferenz.getId();
		KonferenzEntity konferenzEntity = konferenzDao.findById(id);
		konferenzEntity.updateFrom(konferenz);
		konferenzDao.persist(konferenzEntity);
		informListeners(konferenz);
	}

	private void informListeners(Konferenz konferenz) {
		for (KonferenzListener konferenzListener : listeners) {
			konferenzListener.konferenzGeaendert(konferenz);
		}
	}

	public void setKonferenzDao(KonferenzDao konferenzDao) {
		this.konferenzDao = konferenzDao;
	}

	public void setListeners(List<KonferenzListener> listeners) {
		this.listeners = listeners;
	}
}
