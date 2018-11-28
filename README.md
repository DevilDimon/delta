# Delta
A test task for the position of Backend Developer.

# Running tests
Go to the directory with build.gradle and run `./gradlew test`.
You may need to make the Gradle wrapper executable beforehand using `chmod +x gradlew`.

# Example
The example of the delta method execution can be found in DeltaApp.java. Run it with `./gradlew run`.

# Logs

Logs are written to target/rolling/runlog.log. The contents of the file are archived after each run in subsequent files within target/rolling2/ folder.