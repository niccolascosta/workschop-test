
package ge.scope.schedule.tasks.config;

import java.time.LocalTime;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

import ge.scope.schedule.tasks.enun.TypeEvent;
import ge.scope.schedule.tasks.model.Event;

public class ScheduleConfig {

	public static final Pattern READ_LINE_PATTERN = Pattern.compile("^(.+)\\s(\\d+)?\\s?((min)|(lightning))$");
	public static final Integer EVENT_NAME_GROUP = 1;
	public static final Integer EVENT_DURATION_GROUP = 2;
	public static final Integer EVENT_TYPE_GROUP = 3;
	public static final Long MORNING_DURATION = 180l;
	public static final Long LUNCH_DURATION = 60l;
	public static final Long AFTERNOON_DURATION = 240l;
	public static final Event TASK_MEET_COLLEAGUES = new Event("The Meet Your Colleagues Event", NumberUtils.LONG_ZERO, TypeEvent.MINUTE, LocalTime.of(16, 00), LocalTime.of(17, 00));
	public static final String NEW_LINE = System.getProperty("line.separator");
	public static final String TEMPLATE_EVENT = "%s %s";
}
