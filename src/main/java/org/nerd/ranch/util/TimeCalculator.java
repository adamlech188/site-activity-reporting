package org.nerd.ranch.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.nerd.ranch.domain.ActivityEvent;

public final class TimeCalculator {

	final static long ONE_MINUTE_IN_SECONDS = 60l;
	final static long ONE_HOUR_IN_MINUTES = 60l;
	final static long TWELVE_HOURS = 12l;

	public static boolean isActivityEventLessThan12Hours(final ActivityEvent activityEvent) {
		LocalDateTime timeStamp = activityEvent.getTimeStamp();
		long difference = timeStamp.until(LocalDateTime.now(), ChronoUnit.SECONDS);
		return difference <= TWELVE_HOURS * ONE_HOUR_IN_MINUTES * ONE_MINUTE_IN_SECONDS;
	}
}
