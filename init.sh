#!/bin/bash
git checkout -b $1
mvn clean compile dependency:copy-dependencies
rm -rf src
mkdir -p src/main/java
mkdir -p src/test/java
java -cp target/classes/:target/dependency/jsoup-1.10.2.jar cli.Init $1
rm -rf target
git add .
git commit -m "Initialization"
