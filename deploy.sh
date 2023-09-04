#!/bin/bash

./gradlew shadowJar
./gradlew publish -Pgpr.user=kaisdukes -Pgpr.token=$GITHUB_TOKEN