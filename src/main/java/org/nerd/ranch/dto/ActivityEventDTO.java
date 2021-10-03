package org.nerd.ranch.dto;

public class ActivityEventDTO {

	public ActivityEventDTO() {
	}

	public ActivityEventDTO(Integer value) {
		this.value = value;
	}

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(final Integer value) {
		this.value = value;
	}

}
