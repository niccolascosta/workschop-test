## workschop-test

### Prerequisites for Building

* Java 1.8 or above
* Maven 3.3.9 or above

### How to Build

```bash
cd /path/to/project-root
mvn clean package
```
This build task compiles the code, builds a jar file in `target/` directory.

### How to Run the Program

```bash
java -jar /path/to/project-root/target/tasks-0.0.1-SNAPSHOT.jar /path/to/project-root/src/test/resources/tasks
```
