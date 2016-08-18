#!/usr/bin/env bash

echo "Building LifeLog Image Annotator"
./gradlew clean build installApp --daemon

echo "Starting LifeLog Image Annotator"

# start server
./build/install/lifelogia/bin/lifelogia server configuration.yml & LLIA_PID=$!

#call the "handle_kill" function when signals are received.
trap handle_kill SIGINT SIGTERM SIGKILL

function handle_kill() {
        echo ""
        echo "Stopping LifeLog Image Annotator..."
        kill -9 $LLIA_PID
        exit
}

while :
do
    sleep 4
done