package org.nerd.ranch.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nerd.ranch.dto.ActivityEventDTO;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ActivityEventsControllerTest {

	private ActivityEventDTO event;
	private ActivityEventDTO event2;
	private ActivityEventDTO event3;
	private ActivityEventDTO event4;
	private ActivityEventDTO nullEvent;

	@Inject
	private ActivityEventsController activityEventsController;

	@BeforeEach
	public void before() {

		event = new ActivityEventDTO(16);
		event2 = new ActivityEventDTO(5);
		event3 = new ActivityEventDTO(32);
		event4 = new ActivityEventDTO(4);
		nullEvent = new ActivityEventDTO();
	}

	@Test
	public void testCreateActivityEndpoint() {
		given().when().header("Content-type", "application/json").body(event).post("/activity/somekey").then()
				.statusCode(200);
	}

	@Test
	public void testCreateActivityEndpoint_BadRequestException() {
		given().when().header("Content-type", "application/json").body(nullEvent).post("/activity/somekey").then()
				.statusCode(400);
	}

	@Test
	public void testGetActivityEndpoint_NotFoundException() {
		given().when().get("/activity/someKey/total").then().statusCode(404);
	}

	@Test
	public void test_GetActivityEndpoint() {
		activityEventsController.createActivity("somekey", event);
		activityEventsController.createActivity("somekey", event2);
		activityEventsController.createActivity("somekey", event3);
		activityEventsController.createActivity("somekey", event4);
		given().when().get("/activity/somekey/total").then().statusCode(200).body("value", equalTo(57));
	}
}
