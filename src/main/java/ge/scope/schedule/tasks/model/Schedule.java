
package ge.scope.schedule.tasks.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import ge.scope.schedule.tasks.comparator.TimeEventComparator;
import ge.scope.schedule.tasks.config.ScheduleConfig;
import ge.scope.schedule.tasks.enun.TypeEvent;

public class Schedule {

	private final List<Event> events = new ArrayList<>();
	private Long duration;
	private Event eventRequired;
	private LocalTime timeCurrent;

	public Schedule(Long duration, LocalTime time) {
		this.duration = duration;
		this.timeCurrent = time;
	}

	public Schedule(Long duration, LocalTime time, Event eventRequired) {
		this.duration = duration;
		this.timeCurrent = time;
		this.eventRequired = eventRequired;
	}

	public void addEvent(Event event) {
		if (this.duration < event.getDurationInMinutes()) {
			return;
		}
		this.events.add(event);
		this.timeCurrent = this.timeCurrent.plusMinutes(event.getDurationInMinutes());
		this.duration -= event.getDurationInMinutes();
		if (Objects.nonNull(this.eventRequired) && this.isInTheInterval()) {
			this.events.add(this.eventRequired);
			this.eventRequired = null;
		}
	}

	private boolean isInTheInterval() {
		LocalTime startEvent = this.eventRequired.getStartEvent();
		LocalTime endEvent = this.eventRequired.getEndEvent();
		return !this.timeCurrent.isBefore(startEvent) && (this.timeCurrent.isBefore(endEvent) || this.timeCurrent.equals(endEvent));
	}

	public Schedule buildMorningSchedule(List<Event> events) {
		this.buildSchedule(events);
		this.events.add(new Event("Lunch", ScheduleConfig.LUNCH_DURATION, TypeEvent.BREAK));
		return this;
	}

	public Schedule buildAfternoonSchedule(List<Event> events) {
		this.buildSchedule(events);
		Collections.sort(this.events, new TimeEventComparator());
		return this;
	}

	private void buildSchedule(List<Event> events) {
		events.forEach(this::addEvent);
		events.removeAll(this.events);
	}

	public List<Event> getEvents() {
		return this.events;
	}
}
