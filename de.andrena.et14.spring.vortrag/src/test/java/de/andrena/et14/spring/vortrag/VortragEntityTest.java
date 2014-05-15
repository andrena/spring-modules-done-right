package de.andrena.et14.spring.vortrag;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.Rule;
import org.junit.Test;

public class VortragEntityTest {

	@Rule
	public FixedDateTimeRule fixedDateTime = new FixedDateTimeRule();

	@Test
	public void updateActuallyUpdatesTime() {
		VortragEntity vortragEntity = new VortragEntity();
		DateTime lastInformedVortragende = vortragEntity.getLastInformedVortragende();
		fixedDateTime.setTo(DateTime.now().plusHours(1));

		vortragEntity.updateLastInformedVortragende();

		assertThat(vortragEntity.getLastInformedVortragende(),
				Matchers.is(Matchers.<ReadableInstant> greaterThan(lastInformedVortragende)));
	}

}
