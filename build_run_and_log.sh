#!/bin/bash
kill `jps -m | grep APIServer | awk {'print $1'}`
ant
run.sh
tail -f nohup.out