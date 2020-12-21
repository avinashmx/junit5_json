# junit5_json

JUnit 5 Dynamic Container and Test via JSON

### Invoke via Maven Surefire
```
mvn clean install test
```

### Invoking via JUnit5 Runner
```
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin/java -jar junit-platform-console-standalone-1.7.0-all.jar   -c com.gent00.tests.JSONDynamicTest -cp target/junit_scenario-1.0-SNAPSHOT-jar-with-dependencies.jar  --reports-dir=reports --details=verbose
```