#!/usr/bin/env bash
set -x
mvn clean package
tomcat_pid=`ps -ef|grep "apache-tomcat-8.5.31" | grep -v "grep" | awk '{print $2}'`
echo $tomcat_pid
kill -9 $tomcat_pid
echo "tomcat home : $TOMCAT_HOME"
rm -rf $TOMCAT_HOME/webapps/expense-manager.war
rm -rf $TOMCAT_HOME/webapps/expense-manager
cp target/expense-manager-1.0-SNAPSHOT.war $TOMCAT_HOME/webapps/expense-manager.war
sh $TOMCAT_HOME/bin/startup.sh
