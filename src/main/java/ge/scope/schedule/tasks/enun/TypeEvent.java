
package ge.scope.schedule.tasks.enun;

import java.util.Arrays;
import java.util.Optional;

public enum TypeEvent {
	MINUTE(1l, "min"),
	LIGHTENING(5l, "lightning"),
	BREAK(1l, "break");

	private Long multiplier;
	private String name;

	private TypeEvent(Long multiplier, String name) {
		this.multiplier = multiplier;
		this.name = name;
	}

	public Long calculateDurationInMinute(Long duration) {
		return this.multiplier * duration;
	}

	public static TypeEvent getTypeEventByName(String name) {
		Optional<TypeEvent> optionalTypeEvent = Arrays.stream(TypeEvent.values()).filter((typeEvent) -> typeEvent.name.equalsIgnoreCase(name)).findAny();
		return optionalTypeEvent.orElse(null);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
