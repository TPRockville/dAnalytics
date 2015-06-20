#!/bin/bash -e

# use java8
update-alternatives --set java `update-alternatives --display java|grep priority|grep 1.8.0|awk -F' ' '{print $1}'`

conf="$2"

if [ "$conf" == "" ]; then
 conf="_default"
fi

zip=`pwd`/`ls -1 *.zip| head -n 1`

mkdir -p /var/log/service
chmod a+rw /var/log/service

mkdir -p /opt/installed

cd /opt/installed
# unzip it
unzip $zip
# we assume the zip will contain a subdir, like: blah-1.0-212342
# find the one we just extracted
srv=`ls -1t|head -n 1`

# link it to /opt/service
ln -sf /opt/installed/$srv /opt/service

rm -rf /opt/service/logs
ln -sf /var/log/service /opt/service/logs

cat >> /etc/rc.local <<EOF
cd /opt/service
ulimit -H -n 10000
export CONF="$conf"
nohup ./bin/start.sh -f 0<&- >>/var/log/service/out.log 2>&1 &
EOF

cat > /etc/logrotate.d/servicelogs <<EOF
/var/log/service/out.log {
    size 100M
    notifempty
    copytruncate
    compress
    delaycompress
    rotate 7
    maxage 100
}
EOF
