package org.nerd.ranch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nerd.ranch.dto.ActivityEventDTO;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ActivityEventServiceTest {

	@Inject
	private ActivityEventService activityEventService;

	private ActivityEventDTO activityEventDTO;
	private ActivityEventDTO activityEventDTO2;
	private ActivityEventDTO activityEventDTO3;
	private ActivityEventDTO activityEventDTO4;

	@BeforeEach
	public void setUp() {
		activityEventDTO = new ActivityEventDTO(16);
		activityEventDTO2 = new ActivityEventDTO(5);
		activityEventDTO3 = new ActivityEventDTO(32);
		activityEventDTO4 = new ActivityEventDTO(4);
	}

	@Test
	public void test_CreateActivityEvents() {
		activityEventService.createActivityEvents("someEvent", activityEventDTO);
		activityEventService.createActivityEvents("someEvent", activityEventDTO2);
		activityEventService.createActivityEvents("someEvent", activityEventDTO3);
		activityEventService.createActivityEvents("someEvent", activityEventDTO4);
		ActivityEventDTO result = activityEventService.getActivityEventTotals("someEvent");
		assertEquals(57, result.getValue());

	}
}
