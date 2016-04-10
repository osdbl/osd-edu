@echo off
java -Djava.util.logging.config.file=conf/logging.properties -cp bin net.croz.osd.edu.ConsoleShapesApp
echo Exit Code is: %errorlevel%