package org.nerd.ranch.domain;

import java.time.LocalDateTime;

public class ActivityEvent {

	private Integer value;

	private LocalDateTime timeStamp;

	public ActivityEvent() {
	}

	public ActivityEvent(final Integer value, final LocalDateTime timeStamp) {
		super();
		this.value = value;
		this.timeStamp = timeStamp;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(final Integer value) {
		this.value = value;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
