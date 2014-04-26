package de.andrena.et14.spring.konferenz;

import java.util.ArrayList;
import java.util.List;

public class KonferenzService implements IKonferenzService {

	private KonferenzDao konferenzDao;

	@Override
	public List<Konferenz> ladeAlleKonferenzen() {
		List<KonferenzEntity> entities = konferenzDao.loadAll();
		List<Konferenz> result = new ArrayList<>();
		for (KonferenzEntity entity : entities) {
			result.add(entity.toDto());
		}
		return result;
	}

	public void setKonferenzDao(KonferenzDao konferenzDao) {
		this.konferenzDao = konferenzDao;
	}
}
