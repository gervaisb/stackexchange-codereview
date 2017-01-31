#!/bin/bash
mvn dependency:copy-dependencies > /dev/null
java -cp target/classes/:target/dependency/jsoup-1.10.2.jar cli.Init $1
