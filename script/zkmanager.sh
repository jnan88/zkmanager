#!/bin/bash
# ---------------------------------------------------------
# java项目启动脚本
# ---------------------------------------------------------
run_home=$(cd $(dirname $0);pwd)
run_name=$(basename $0)
run_day=`date +%Y%m%d`
run_time=`date +%H%M`
run_day_time=`date +%Y%m%d%H%M`
# ---------------------------------------------------------
JAVA=/www/env/java/bin/java
NAME=zkmanager
JVM_OPTS=""
JAVA_OPTS=" -jar ${JVM_OPTS} -Dapp.name=${NAME} -Dspring.profiles.active=prod"
APP_HOME=/www/web/zkmanager
APP_CMD=${NAME}.jar
CONSOLE_LOG_FILE=${run_home}/run-${NAME}.log
# ---------------------------------------------------------
PIDS=0
function getpid(){
  PIDS=$(ps ax | grep -i ${NAME} | grep java | grep -v grep | awk '{print $1}')
  if [ ! $PIDS ];then
      PIDS=0
  fi
}
function status(){
getpid
if [ $PIDS -ne 0 ]; then
  echo "$NAME is running! (pid=$PIDS)"
else
  echo "$NAME is not running"
fi
}
function stop(){
getpid
if [ -z "$PIDS" ]; then
  echo "No ${NAME} to stop"
  exit 1
else
  echo "stop ${NAME} server"
  kill -s TERM $PIDS
  sleep 5
  getpid
  if [ $PIDS -ne 0 ]; then
       echo "Stop $NAME [Failed] (pid=$PIDS)"
  else
       echo "Stop $NAME success [OK]"
  fi
fi
}
function start(){
if [ $PIDS -ne 0 ]; then
  echo "================================"
  echo "warn: $APP_NAME already started! (pid=$PIDS)"
  echo "================================"
else
  echo -n "Starting $NAME ..."
  nohup $JAVA $JAVA_OPTS $APP_HOME/$APP_CMD "$NAME" >> "$CONSOLE_LOG_FILE" 2>&1 < /dev/null &
  sleep 5
  getpid

  if [ $PIDS -ne 0 ]; then
       echo "(pid=$PIDS) [OK]"
       echo "log on $CONSOLE_LOG_FILE"
  else
       echo "[Failed]"
  fi
fi
}

case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     sleep 3
     start
     ;;
   'status')
     status
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status}"
     exit 1
esac
