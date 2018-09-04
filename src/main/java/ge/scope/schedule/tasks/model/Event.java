
package ge.scope.schedule.tasks.model;

import java.time.LocalTime;

import org.apache.commons.lang3.math.NumberUtils;

import ge.scope.schedule.tasks.enun.TypeEvent;

public class Event {

	private final String name;
	private final Long duration;
	private final TypeEvent typeEvent;
	private LocalTime startEvent;
	private LocalTime endEvent;

	public Event(String name, Long duration, TypeEvent typeEvent) {
		this.name = name;
		this.duration = duration;
		this.typeEvent = typeEvent;
	}

	public Event(String name, Long duration, TypeEvent typeEvent, LocalTime startEvent, LocalTime endEvent) {
		this.name = name;
		this.duration = duration;
		this.typeEvent = typeEvent;
		this.startEvent = startEvent;
		this.endEvent = endEvent;
	}

	@Override
	public String toString() {
		if (this.duration.longValue() == NumberUtils.LONG_ZERO || !TypeEvent.MINUTE.equals(this.typeEvent)) {
			return this.name;
		}
		return this.name + " - " + this.duration + " " + this.typeEvent;
	}

	public Long getDurationInMinutes() {
		return this.typeEvent.calculateDurationInMinute(this.duration);
	}

	public String getName() {
		return this.name;
	}

	public LocalTime getStartEvent() {
		return this.startEvent;
	}

	public LocalTime getEndEvent() {
		return this.endEvent;
	}

}
