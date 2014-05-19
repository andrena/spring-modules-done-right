package de.andrena.et14.spring.vortrag;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.rules.ExternalResource;

public class FixedDateTimeRule extends ExternalResource {
	@Override
	protected void after() {
		resumeRegularSystemTime();
	}

	private void resumeRegularSystemTime() {
		DateTimeUtils.setCurrentMillisSystem();
	}

	public void setTo(DateTime time) {
		DateTimeUtils.setCurrentMillisFixed(time.getMillis());
	}
}
