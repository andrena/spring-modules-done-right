package de.andrena.et14.spring.vortrag;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.andrena.et14.spring.konferenz.KonferenzDao;
import de.andrena.et14.spring.konferenz.KonferenzEntity;
import de.andrena.et14.spring.konferenz.springconfig.KonferenzDaoSpringConfig;
import de.andrena.et14.spring.persistenz.springconfig.PersistenzSpringConfig;
import de.andrena.et14.spring.vortrag.springconfig.VortragDaoSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenzSpringConfig.class, VortragDaoSpringConfig.class,
		KonferenzDaoSpringConfig.class })
@Transactional
public class VortragDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private KonferenzDao konferenzDao;

	@Inject
	private VortragDao vortragDao;

	@Test
	public void vortragLaesstSichPersistieren() {
		KonferenzEntity konferenz = createKonferenz();

		VortragEntity vortrag = createVortragForKonferenz(konferenz);
		entityManager.flush();
		entityManager.clear();

		VortragEntity persistedVortrag = vortragDao.findById(vortrag.getId());
		assertThat(persistedVortrag, is(notNullValue()));
		assertThat(persistedVortrag.getKonferenz(), is(notNullValue()));
		assertThat(persistedVortrag.getKonferenz().getId(), is(konferenz.getId()));
	}

	private KonferenzEntity createKonferenz() {
		KonferenzEntity konferenz = new KonferenzEntity();
		konferenzDao.persist(konferenz);
		return konferenz;
	}

	private VortragEntity createVortragForKonferenz(KonferenzEntity konferenz) {
		VortragEntity vortrag = new VortragEntity();
		vortrag.setKonferenz(konferenz);
		vortragDao.persist(vortrag);
		return vortrag;
	}

	@Test
	public void vortraegeLassenSichZuEinerSpezifischenKonferenzFinden() {
		KonferenzEntity eineKonferenz = createKonferenz();
		VortragEntity vortragZuEinerKonferenz = createVortragForKonferenz(eineKonferenz);
		KonferenzEntity andereKonferenz = createKonferenz();
		createVortragForKonferenz(andereKonferenz);

		List<VortragEntity> gefundeneVortraege = vortragDao.findAllForKonferenzId(eineKonferenz
				.getId());
		assertThat(gefundeneVortraege, Matchers.contains(vortragZuEinerKonferenz));
	}
}
