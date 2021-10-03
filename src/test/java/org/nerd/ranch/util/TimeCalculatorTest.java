package org.nerd.ranch.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nerd.ranch.domain.ActivityEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TimeCalculatorTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private ActivityEvent activityEvent;

	@BeforeEach
	public void setUp() {
		activityEvent = new ActivityEvent();
	}

	@Test
	public void testZoneId() {
		ZoneId  zoneId = ZoneId.of("UTC");
		String defaultZoneId = TimeZone.getDefault().getID();
		logger.info("Default time zone is: {}", defaultZoneId);
	}
	
	@Test
	public void testTimeDifference_MoreThan12Hours() {
	   activityEvent.setTimeStamp(LocalDateTime.now().minusHours(13));
	   boolean result =	TimeCalculator.isActivityEventLessThan12Hours(activityEvent);
	   assertFalse(result);
	}

	@Test
	public void testTimeDifference_LessThan12Hours() {
	   activityEvent.setTimeStamp(LocalDateTime.now().minusHours(11));
	   boolean result =	TimeCalculator.isActivityEventLessThan12Hours(activityEvent);
	   assertTrue(result);
	}
	
	@Test
	public void testTimeDifference_MoreThan721Minutes() {
	   activityEvent.setTimeStamp(LocalDateTime.now().minusMinutes(721));
	   boolean result =	TimeCalculator.isActivityEventLessThan12Hours(activityEvent);
	   assertFalse(result);
	}
	
	@Test
	public void testTimeDifference_MoreThan43201Seconds() {
	   activityEvent.setTimeStamp(LocalDateTime.now().minusSeconds(43201));
	   boolean result =	TimeCalculator.isActivityEventLessThan12Hours(activityEvent);
	   assertFalse(result);
	}
	
	@Test
	public void testTimeDifference_Exactly4320Seconds() {
	   activityEvent.setTimeStamp(LocalDateTime.now().minusSeconds(43200));
	   boolean result =	TimeCalculator.isActivityEventLessThan12Hours(activityEvent);
	   assertTrue(result);
	}

	@Test
	public void testTimeDifference_Exactly12Hours() {
	   activityEvent.setTimeStamp(LocalDateTime.now().minusHours(12));
	   boolean result =	TimeCalculator.isActivityEventLessThan12Hours(activityEvent);
	   assertTrue(result);
	}
}
