package org.nerd.ranch.controller;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.nerd.ranch.dto.ActivityEventDTO;
import org.nerd.ranch.service.ActivityEventService;

@Path("/activity")
public class ActivityEventsController {

	private ActivityEventService service;

	@Inject
	public ActivityEventsController(final ActivityEventService service) {
		this.service = service;
	}

	@POST
	@Path("/{key}")
	public Response createActivity(@PathParam(value = "key") final String key, ActivityEventDTO activityEventDTO) throws BadRequestException {
		service.createActivityEvents(key, activityEventDTO);
		return Response.ok().build();
	}

	@GET
	@Path("/{key}/total")
	public Response getActivity(@PathParam(value = "key") final String key) throws NotFoundException {
		ActivityEventDTO response = service.getActivityEventTotals(key);
		return Response.ok(response).build();
	}

}
