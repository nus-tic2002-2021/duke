#!/bin/sh

# clean build dir
rm -rf target


curl -sL https://www-eu.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip -o maven.zip
sudo apt-get update
sudo apt-get -y install unzip
unzip -d /usr/share maven.zip
rm maven.zip

mvnlnk=/usr/bin/mvn
if [ ! -L $(mvnlnk) ]; then
  sudo rm $(mvnlnk)
fi

sudo ln -s /usr/share/apache-maven-3.6.3/bin/mvn /usr/bin/mvn
echo "M2_HOME=/usr/share/apache-maven-3.6.3" | sudo tee -a /etc/environment

# source code test
mvn clean
mvn install
mvn compile
mvn test

# package into jar
mvn package
# test jar output
java -jar NoelLim/Duke.jar < ./src/test/resources/linux-test/input.txt > ./src/test/resources/linux-test/actual.txt

cmp --print-chars ./src/test/resources/linux-test/actual.txt ./src/test/resources/linux-test/expected.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    cat ./src/test/resources/linux-test/actual.txt
    cat ./src/test/resources/linux-test/expected.txt
    echo "Test result: FAILED"
    exit 1
fi