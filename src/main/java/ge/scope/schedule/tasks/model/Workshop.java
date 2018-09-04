
package ge.scope.schedule.tasks.model;

import java.util.ArrayList;
import java.util.List;

import ge.scope.schedule.tasks.config.ScheduleConfig;

public class Workshop {

	private final List<Track> tracks = new ArrayList<>();

	public void addTrack(Track track) {
		this.tracks.add(track);
	}

	@Override
	public String toString() {
		Integer indexTrack = 1;
		String newLine = ScheduleConfig.NEW_LINE;
		StringBuilder builder = new StringBuilder();
		builder.append("Workshop: " + newLine);
		for (Track track : this.tracks) {
			builder.append("Trark" + indexTrack + ":" + newLine);
			builder.append(track);
			builder.append(newLine);
			indexTrack++;
		}
		return builder.toString();
	}
}
