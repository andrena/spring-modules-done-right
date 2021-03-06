package de.andrena.et14.spring.vortrag;

import java.util.ArrayList;
import java.util.List;

import de.andrena.et14.spring.konferenz.Konferenz;
import de.andrena.et14.spring.konferenz.KonferenzDao;
import de.andrena.et14.spring.konferenz.KonferenzEntity;

public class VortragService implements IVortragService {

	private VortragDao vortragDao;
	private KonferenzDao konferenzDao;

	@Override
	public Vortrag erstelleVortrag(Vortrag vortrag) {
		Konferenz konferenz = vortrag.getKonferenz();
		KonferenzEntity konferenzEntity = konferenzDao.findById(konferenz.getId());

		VortragEntity vortragEntity = new VortragEntity();
		vortragEntity.setKonferenz(konferenzEntity);
		vortragEntity.updateFrom(vortrag);
		vortragDao.persist(vortragEntity);

		return vortragEntity.toDto();
	}

	@Override
	public List<Vortrag> ladeAlleVortraege(Konferenz konferenz) {
		List<VortragEntity> entities = vortragDao.findAllForKonferenzId(konferenz.getId());
		List<Vortrag> result = new ArrayList<>();
		for (VortragEntity vortragEntity : entities) {
			result.add(vortragEntity.toDto());
		}
		return result;
	}

	@Override
	public void informiereVortragende(Konferenz konferenz) {
		for (VortragEntity vortragEntity : vortragDao.findAllForKonferenzId(konferenz.getId())) {
			System.out.println("Sende EMail an alle Referenten von " + vortragEntity.getTitel());
			vortragEntity.updateLastInformedVortragende();
		}
	}

	public void setVortragDao(VortragDao vortragDao) {
		this.vortragDao = vortragDao;
	}

	public void setKonferenzDao(KonferenzDao konferenzDao) {
		this.konferenzDao = konferenzDao;
	}
}
