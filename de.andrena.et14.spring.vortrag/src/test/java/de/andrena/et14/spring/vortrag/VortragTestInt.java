package de.andrena.et14.spring.vortrag;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.joda.time.ReadableInstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.andrena.et14.spring.konferenz.IKonferenzService;
import de.andrena.et14.spring.konferenz.Konferenz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VortragTestIntSpringConfig.class)
public class VortragTestInt {

	@Inject
	private IVortragService vortragClient;

	@Inject
	private IKonferenzService konferenzClient;

	private Konferenz konferenz;

	@Before
	public void bereiteKonferenzVor() {
		konferenz = erstelleKonferenz();
	}

	private Konferenz erstelleKonferenz() {
		Konferenz konferenz = new Konferenz();
		konferenz.setName("Entwicklertag");
		konferenz.setOrt("Karlsruhe");
		konferenz.setDatum(new LocalDate(2014, 5, 21));
		return konferenzClient.erstelleKonferenz(konferenz);
	}

	@Test
	public void speichertUndLaedtVortragZuKonferenz() {
		Vortrag erstellterVortrag = erstelleVortragZuKonferenz(konferenz);

		List<Vortrag> vortraege = vortragClient.ladeAlleVortraege(konferenz);
		assertThat(vortraege, contains(erstellterVortrag));
	}

	private Vortrag erstelleVortragZuKonferenz(Konferenz konferenz) {
		Vortrag vortrag = new Vortrag(konferenz);
		vortrag.setTitel("Demo-Vortrag");
		Vortrag erstellterVortrag = vortragClient.erstelleVortrag(vortrag);
		return erstellterVortrag;
	}

	@Test
	public void informiertVortragendeBeiAenderungAnKonferenz() throws InterruptedException {
		Vortrag erstellterVortrag = erstelleVortragZuKonferenz(konferenz);
		Thread.sleep(50);

		konferenz.setOrt("Stuttgart");
		konferenzClient.aendereKonferenz(konferenz);

		List<Vortrag> vortraege = vortragClient.ladeAlleVortraege(konferenz);
		assertThatVortragendeWereInformed(erstellterVortrag, vortraege);
	}

	private void assertThatVortragendeWereInformed(Vortrag erstellterVortrag,
			List<Vortrag> vortraege) {
		for (Vortrag vortrag : vortraege) {
			assertThatVortragendeWereInformed(erstellterVortrag, vortrag);
		}
	}

	private void assertThatVortragendeWereInformed(Vortrag erstellterVortrag, Vortrag updatedVortrag) {
		assertThat(updatedVortrag.getLastInformedVortragende(),
				is(Matchers.<ReadableInstant> greaterThan(erstellterVortrag
						.getLastInformedVortragende())));
	}

	@Test
	public void informiertKeineVortragendenVonAndererKonferenz() throws InterruptedException {
		erstelleVortragZuKonferenz(konferenz);
		Konferenz andereKonferenz = erstelleKonferenz();
		Vortrag vortragZuAndererKonferenz = erstelleVortragZuKonferenz(andereKonferenz);
		Thread.sleep(50);

		konferenz.setOrt("Stuttgart");
		konferenzClient.aendereKonferenz(konferenz);

		List<Vortrag> vortraege = vortragClient.ladeAlleVortraege(andereKonferenz);
		assertThatVortragendeWereNotInformed(vortragZuAndererKonferenz, vortraege);
	}

	private void assertThatVortragendeWereNotInformed(Vortrag erstellterVortrag,
			List<Vortrag> vortraege) {
		for (Vortrag vortrag : vortraege) {
			assertThatVortragendeWereNotInformed(erstellterVortrag, vortrag);
		}
	}

	private void assertThatVortragendeWereNotInformed(Vortrag erstellterVortrag,
			Vortrag updatedVortrag) {
		assertThat(updatedVortrag.getLastInformedVortragende(),
				is(erstellterVortrag.getLastInformedVortragende()));
	}
}
