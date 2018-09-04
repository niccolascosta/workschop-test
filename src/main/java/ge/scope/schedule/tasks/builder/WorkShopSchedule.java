
package ge.scope.schedule.tasks.builder;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import ge.scope.schedule.tasks.comparator.EventComparator;
import ge.scope.schedule.tasks.config.ScheduleConfig;
import ge.scope.schedule.tasks.enun.TypeEvent;
import ge.scope.schedule.tasks.model.Event;
import ge.scope.schedule.tasks.model.Schedule;
import ge.scope.schedule.tasks.model.Track;
import ge.scope.schedule.tasks.model.Workshop;

public class WorkShopSchedule {

	public Workshop buildWorkShop(Stream<String> stream) {
		List<Event> events = this.convertStreamToEvents(stream);
		Workshop workshop = new Workshop();
		while (!events.isEmpty()) {
			workshop.addTrack(this.createTrack(events));
		}

		return workshop;
	}

	private List<Event> convertStreamToEvents(Stream<String> stream) {
		return stream.map(this::convertLineInEvent).filter(Objects::nonNull).sorted(new EventComparator()).collect(Collectors.toList());
	}

	private Event convertLineInEvent(String line) {
		Matcher matcher = ScheduleConfig.READ_LINE_PATTERN.matcher(line);
		if (!matcher.find()) {
			return null;
		}
		String name = matcher.group(ScheduleConfig.EVENT_NAME_GROUP);
		TypeEvent typeEvent = TypeEvent.getTypeEventByName(matcher.group(ScheduleConfig.EVENT_TYPE_GROUP));
		Long duration = this.convertDurationTime(matcher.group(ScheduleConfig.EVENT_DURATION_GROUP));
		return new Event(name, duration, typeEvent);
	}

	private Long convertDurationTime(String duration) {
		if (StringUtils.isBlank(duration)) {
			return NumberUtils.LONG_ONE;
		}
		return Long.parseLong(duration);
	}

	private Track createTrack(List<Event> events) {
		Track track = new Track();
		track.addSchedule(new Schedule(ScheduleConfig.MORNING_DURATION, LocalTime.of(9, 00)).buildMorningSchedule(events));
		track.addSchedule(new Schedule(ScheduleConfig.AFTERNOON_DURATION, LocalTime.of(13, 00), ScheduleConfig.TASK_MEET_COLLEAGUES).buildAfternoonSchedule(events));
		return track;
	}

}
