package de.andrena.et14.spring.vortrag;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.andrena.et14.spring.konferenz.IKonferenzService;
import de.andrena.et14.spring.konferenz.Konferenz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring-testint-vortrag-config.xml" })
public class VortragTestInt {

	@Inject
	private IVortragService vortragClient;

	@Inject
	private IKonferenzService konferenzClient;

	@Test
	public void speichertUndLaedtVortragZuKonferenz() {
		Konferenz konferenz = erstelleKonferenz();

		Vortrag vortrag = new Vortrag(konferenz);
		vortrag.setTitel("Demo-Vortrag");
		Vortrag erstellterVortrag = vortragClient.erstelleVortrag(vortrag);

		List<Vortrag> vortraege = vortragClient.ladeAlleVortraege(konferenz);
		assertThat(vortraege, contains(erstellterVortrag));
	}

	private Konferenz erstelleKonferenz() {
		Konferenz konferenz = new Konferenz();
		konferenz.setName("Entwicklertag");
		konferenz.setOrt("Karlsruhe");
		konferenz.setDatum(new LocalDate(2014, 5, 21));
		return konferenzClient.erstelleKonferenz(konferenz);
	}
}
