#!/bin/sh
########################################################################################################################
#
# Healthcheck script to ensure that the app is running
# Reference:
#   https://docs.spring.io/spring-boot/api/rest/actuator/health.html
#
########################################################################################################################

echo "$0: checking..."
CONTEXTPATH=${SERVER_SERVLET_CONTEXTPATH:-}
URL="http://localhost:$SERVER_PORT$CONTEXTPATH/actuator/health"
RESPONSE=$(curl -s --fail "$URL")
STATUS=$(echo "$RESPONSE" | grep -o '"status":"[^"]*"' | cut -d ':' -f2 | tr -d '"')
echo "$0: status: $STATUS"

if [ "$STATUS" = "UP" ]; then
  exit 0
else
  exit 1
fi
