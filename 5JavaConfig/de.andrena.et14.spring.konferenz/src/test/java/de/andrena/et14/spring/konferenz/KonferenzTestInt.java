package de.andrena.et14.spring.konferenz;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KonferenzTestIntSpringConfig.class)
public class KonferenzTestInt {

	@Inject
	private IKonferenzService konferenzClient;

	@Test
	public void erstelltUndLaedtKonferenz() {
		Konferenz konferenz = new Konferenz();
		konferenz.setName("Entwicklertag");
		konferenz.setOrt("Karlsruhe");
		konferenz.setDatum(new LocalDate(2014, 5, 21));
		Konferenz erstellteKonferenz = konferenzClient.erstelleKonferenz(konferenz);

		List<Konferenz> konferenzen = konferenzClient.ladeAlleKonferenzen();
		assertThat(konferenzen, hasItem(erstellteKonferenz));
	}
}
