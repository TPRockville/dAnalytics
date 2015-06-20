#!/bin/bash

# $projectName
# OPTIONS:
#   -f: start in foreground
#   -p <filename>: log the pid to a file (useful to kill it later)



## you probably need to fix this ..
configfile="conf/openfda.yml"
overridefile="openfda.properties"

if [ "$APP_HOME" == "" ]; then
    BIN_DIR=`dirname $0`
    if [ `uname -s` == "Darwin" ]; then
      APP_HOME=`(cd $BIN_DIR"/.."; pwd -P)`
    else
      APP_HOME=`readlink -f $BIN_DIR"/.."`
    fi
fi

PROJECT_NAME=openfda

if [ "$MAX_HEAP_SIZE" == "" ]; then
  system_memory_in_mb=`free -m | awk '/Mem:/ {print $2}'`
  LT_2G=$((((system_memory_in_mb / 2)) < 2000))
  RAW_HEAP_SIZE="1500" # set a default of 2G
  if [ "$LT_2G" != "1" ]; then
    # take 80% of the memory up to 32G
    RAW_HEAP_SIZE=`echo $system_memory_in_mb"* .80"|bc -l|sed s/\\\\..*//g`
  fi

  MAX_HEAP_SIZE=$RAW_HEAP_SIZE"M"
  # this could be reduced to something like: min(100M * # cores, max_heap * .24)
  if [ "$NEW_HEAP_SIZE" == "" ]; then
    # keep the new generation relatively small 25-30% of max
    NEW_HEAP_SIZE=`echo $RAW_HEAP_SIZE"*.28"|bc -l|sed s/\\\\..*//g`M
  fi
  if [ "$MIN_HEAP_SIZE" == "" ]; then
    MIN_HEAP_SIZE=$MAX_HEAP_SIZE
    if [ "$RAW_HEAP_SIZE" -gt "8192" ]; then
      MIN_HEAP_SIZE=$((RAW_HEAP_SIZE - (RAW_HEAP_SIZE / 4)))"M"
    fi
  fi
fi

if [ "$JVM_OPTS" == "" ]; then
  JVM_OPTS="-server -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$APP_HOME/log"
  JVM_OPTS="$JVM_OPTS -XX:+OptimizeStringConcat -XX:+UseStringDeduplication"
  # make the mx & ms the same to avoid pauses
  JVM_OPTS="$JVM_OPTS -Xmx$MAX_HEAP_SIZE -Xms$MIN_HEAP_SIZE -Xmn$NEW_HEAP_SIZE"
fi


launch_service()
{
    pidpath=$1
    foreground=$2
    props=$3
    config=$APP_HOME"/"$4

    override=$APP_HOME/conf/$CONF/$overridefile
    log "override file: $override"
    if [ "$CONF" != "" ] && [ -r $override ]; then
      parms="-Doverrides=$override"
    fi

    if [ "x$pidpath" != "x" ]; then
        parms="$parms -Dpidfile=$pidpath"
    fi

    if [ "$debug" == "yes" ]; then
        parms="$parms -Ddw.logging.level=DEBUG"
    fi

    # set to root
    cd $APP_HOME

    # The foreground option will tell Daemon not
    # to close stdout/stderr, but it's up to us not to background.
    if [ "x$foreground" != "x" ]; then
        parms="$parms -Dforeground=yes"
        export JAVA_OPTS="$JVM_OPTS $parms"
        log $APP_HOME/bin/$PROJECT_NAME server $config
        exec $APP_HOME/bin/$PROJECT_NAME server $config
    # Startup Daemon, background it, and write the pid.
    else
        export JAVA_OPTS="$JVM_OPTS $parms"
        log $APP_HOME/bin/$PROJECT_NAME server $config
        exec $APP_HOME/bin/$PROJECT_NAME server $config <&- &
        [ ! -z $pidpath ] && printf "%d" $! > $pidpath
    fi

    return $?
}

log()
{
  if [ "$debug" == "yes" ]; then
    echo $@
  fi
}

# Parse any command line options.
args=`getopt fahdp:bD: "$@"`
eval set -- "$args"


while true; do
    case "$1" in
        -p)
            pidfile="$2"
            shift 2
        ;;
        -f)
            foreground="yes"
            shift
        ;;
        -d)
            debug="yes"
            shift
        ;;
        -h)
            echo "Usage: $0 [-f] [-h] [-p pidfile]"
            exit 0
        ;;
        -D)
            properties="$properties -D$2"
            shift 2
        ;;
        --)
            shift
            break
        ;;
        *)
            echo "Error parsing arguments!" >&2
            exit 1
        ;;
    esac
done

# Start up the service
launch_service "$pidfile" "$foreground" "$properties" "$configfile"

exit $?

# vi:ai sw=4 ts=4 tw=0 et
