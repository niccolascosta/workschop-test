
package ge.scope.schedule.tasks.comparator;

import java.util.Comparator;

import ge.scope.schedule.tasks.model.Event;

public class EventComparator implements Comparator<Event> {

	@Override
	public int compare(Event o1, Event o2) {
		Long d1 = o1.getDurationInMinutes();
		Long d2 = o2.getDurationInMinutes();
		if (d1 % 6 == d2 % 6) {
			if (d1 % 6 == 0) {
				return Long.compare(d2, d1);
			} else {
				return Long.compare(d2, d1);
			}
		}
		if (d1 % 6 == 0) {
			return -1;
		}
		return 1;
	}

}
