#!/bin/bash

# Run every 30 seconds
while true
do
    echo "Hello"
    M=`curl -X GET --cookie "session-id=b8afc16b-1b1e-4a79-825b-360f141fa0f8" http://localhost:8080/numberOfMessages`;
    for i in $(seq 1 $M)
    do
        echo "Hi"
        curl -X GET --cookie "session-id=b8afc16b-1b1e-4a79-825b-360f141fa0f8" http://localhost:8080/mail/$i
    done
    sleep 1
done

# Fetch mail page with admin cookie (faked with big guid for session id)
# curl -X GET --cookie "session-id=b8afc16b-1b1e-4a79-825b-360f141fa0f8" http://localhost:8080/hiddenadminmailpage
