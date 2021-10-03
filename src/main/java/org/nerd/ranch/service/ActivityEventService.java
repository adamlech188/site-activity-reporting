package org.nerd.ranch.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.nerd.ranch.domain.ActivityEvent;
import org.nerd.ranch.dto.ActivityEventDTO;
import org.nerd.ranch.util.TimeCalculator;

@ApplicationScoped
public class ActivityEventService {

	private Map<String, List<ActivityEvent>> activityEventTotalsMap = new ConcurrentHashMap<>();

	public ActivityEventDTO getActivityEventTotals(final String key) {
		List<ActivityEvent> result = activityEventTotalsMap.get(key);
		if (null == result) {
			throw new NotFoundException("Events don't exist for activity: " + key);
		}
		Integer value = this.calculateActivityEventTotals(key);
		return new ActivityEventDTO(value);
	}

	public ActivityEventDTO createActivityEvents(final String key, final ActivityEventDTO activityEventDTO) {

		Integer newValue = activityEventDTO.getValue();
		if (null == activityEventDTO.getValue()) {
			throw new BadRequestException("Provide value for activity events");
		}
		List<ActivityEvent> existingActivityEvents = activityEventTotalsMap.get(key);
		if (null == existingActivityEvents) {
			existingActivityEvents = new ArrayList<>();
		}
		ActivityEvent activityEvent = new ActivityEvent(newValue, LocalDateTime.now());
		existingActivityEvents.add(activityEvent);
		activityEventTotalsMap.put(key, existingActivityEvents);
		return activityEventDTO;
	}

	private Integer calculateActivityEventTotals(final String key) {

		List<ActivityEvent> events = activityEventTotalsMap.get(key);
		Optional<Integer> result = events.stream().filter(ae -> TimeCalculator.isActivityEventLessThan12Hours(ae))
				.map(ae -> ae.getValue()).reduce((v1, v2) -> v1 + v2);
		return result.get();

	}
}
