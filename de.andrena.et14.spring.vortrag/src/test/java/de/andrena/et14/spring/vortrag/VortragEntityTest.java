package de.andrena.et14.spring.vortrag;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadableInstant;
import org.junit.After;
import org.junit.Test;

public class VortragEntityTest {

	@After
	public void resetTime() {
		DateTimeUtils.setCurrentMillisSystem();
	}

	@Test
	public void updateActuallyUpdatesTime() {
		VortragEntity vortragEntity = new VortragEntity();
		DateTime lastInformedVortragende = vortragEntity.getLastInformedVortragende();
		DateTimeUtils.setCurrentMillisFixed(DateTime.now().plusHours(1).getMillis());

		vortragEntity.updateLastInformedVortragende();

		assertThat(vortragEntity.getLastInformedVortragende(),
				Matchers.is(Matchers.<ReadableInstant> greaterThan(lastInformedVortragende)));
	}

}
