echo "Building the project: %date% %time%"
mvn -v
mvn clean package spring-boot:repackage
