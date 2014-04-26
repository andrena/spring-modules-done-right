package de.andrena.et14.spring.konferenz;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring-testint-konferenz-config.xml" })
public class KonferenzTestInt {

	@Autowired
	private IKonferenzService konferenzClient;

	@Test
	public void laedtAlleKonferenzen() {
		List<Konferenz> konferenzen = konferenzClient.ladeAlleKonferenzen();
		assertThat(konferenzen, is(notNullValue()));
	}
}
