
package ge.scope.schedule.tasks.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ge.scope.schedule.tasks.config.ScheduleConfig;

public class Track {

	private final List<Schedule> schedule = new ArrayList<>();

	public void addSchedule(Schedule schedule) {
		this.schedule.add(schedule);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<Event> events = this.schedule.stream().flatMap(schedule -> schedule.getEvents().stream()).collect(Collectors.toList());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
		LocalTime startTime = LocalTime.of(9, 00);
		for (Event event : events) {
			String timeFormated = startTime.format(formatter);
			builder.append(String.format(ScheduleConfig.TEMPLATE_EVENT, timeFormated, event));
			builder.append(ScheduleConfig.NEW_LINE);
			startTime = startTime.plusMinutes(event.getDurationInMinutes());
		}
		return builder.toString();
	}
}
