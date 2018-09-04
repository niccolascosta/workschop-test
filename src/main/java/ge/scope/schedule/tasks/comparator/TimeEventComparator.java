
package ge.scope.schedule.tasks.comparator;

import java.util.Comparator;

import ge.scope.schedule.tasks.model.Event;

public class TimeEventComparator implements Comparator<Event> {

	@Override
	public int compare(Event o1, Event o2) {
		return Long.compare(o2.getDurationInMinutes(), o1.getDurationInMinutes());
	}

}
