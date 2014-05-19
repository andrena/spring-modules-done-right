package de.andrena.et14.spring.konferenz;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring-daotest-konferenz-config.xml" })
@Transactional
public class KonferenzDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private KonferenzDao konferenzDao;

	@Test
	public void konferenzLaesstSichPersistieren() {
		KonferenzEntity konferenz = new KonferenzEntity();
		konferenzDao.persist(konferenz);
		entityManager.flush();
		entityManager.clear();

		KonferenzEntity persistedKonferenz = konferenzDao.findById(konferenz.getId());
		assertThat(persistedKonferenz, is(notNullValue()));
	}
}
