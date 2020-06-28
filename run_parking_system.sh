#jar file name
jarName=ParkingSystem-0.0.1-SNAPSHOT.jar

#directory where jar is present
dir=target
mvn clean package
java -jar $dir/$jarName