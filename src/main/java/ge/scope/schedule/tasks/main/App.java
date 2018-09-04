
package ge.scope.schedule.tasks.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import ge.scope.schedule.tasks.builder.WorkShopSchedule;
import ge.scope.schedule.tasks.logger.Logger;
import ge.scope.schedule.tasks.model.Workshop;

public class App {

	public static void main(String[] args) {
		Logger log = Logger.getLogger();

		if (args.length < 1) {
			log.error("Enter the file that contains the events");
			System.exit(1);
		}
		String fileName = args[0];
		Path path = Paths.get(fileName);
		try (Stream<String> stream = Files.lines(path)) {
			Workshop workShop = new WorkShopSchedule().buildWorkShop(stream);
			System.out.println(workShop);
		} catch (IOException e) {
			log.error("Cannot read from input file: " + path.toAbsolutePath());
			System.exit(1);
		}

	}

}
