#!/bin/bash

# 설정
HEALTH_CHECK_PATH="/actuator/health"
BLUE_PORT=8081
GREEN_PORT=8082
WAIT_SECONDS=10
MAX_RETRIES=10
NGINX_CONF="/etc/nginx/conf.d/donet.conf"

# 현재 실행 중인 컨테이너 확인
ACTIVE_SERVICE=$(docker ps --filter "name=blue-server" --format "{{.Names}}")

if [[ "$ACTIVE_SERVICE" == *"blue-server"* ]]; then
  NEW_SERVICE="green-server"
  OLD_SERVICE="blue-server"
  NEW_PORT=$GREEN_PORT
  OLD_PORT=$BLUE_PORT
else
  NEW_SERVICE="blue-server"
  OLD_SERVICE="green-server"
  NEW_PORT=$BLUE_PORT
  OLD_PORT=$GREEN_PORT
fi

echo "[INFO] Starting new service: $NEW_SERVICE"

docker compose pull $NEW_SERVICE

# 기존 컨테이너가 실행 중이면 중지
docker compose stop $NEW_SERVICE
docker compose rm -f $NEW_SERVICE

# 새 컨테이너 실행
docker compose up -d $NEW_SERVICE

# 헬스체크
echo "[INFO] Waiting $WAIT_SECONDS seconds for container startup..."
sleep $WAIT_SECONDS

echo "[INFO] Checking health status..."
SUCCESS=false
for ((i=1;i<=MAX_RETRIES;i++)); do
  STATUS=$(curl -s http://localhost:$NEW_PORT$HEALTH_CHECK_PATH | jq -r '.status')

  if [ "$STATUS" == "UP" ]; then
    echo "[SUCCESS] $NEW_SERVICE is healthy."
    SUCCESS=true
    break
  fi

  echo "[WAITING] Retry $i/$MAX_RETRIES... (status=$STATUS)"
  sleep 3
done

if [ "$SUCCESS" != "true" ]; then
  echo "[ERROR] Health check failed. Rolling back."
  docker compose stop $NEW_SERVICE
  docker compose rm -f $NEW_SERVICE
  exit 1
fi

# Nginx 설정 전환
echo "[INFO] Switching Nginx upstream to $NEW_SERVICE..."

if [ "$NEW_PORT" -eq "$BLUE_PORT" ]; then
  sudo sed -i 's/server 127.0.0.1:8082;/server 127.0.0.1:8081;/' $NGINX_CONF
else
  sudo sed -i 's/server 127.0.0.1:8081;/server 127.0.0.1:8082;/' $NGINX_CONF
fi

sudo nginx -t && sudo nginx -s reload

# 이전 컨테이너 정리
echo "[INFO] Stopping and removing old service: $OLD_SERVICE"
docker compose stop $OLD_SERVICE
docker compose rm -f $OLD_SERVICE

echo "[DONE] Deployed new version to $NEW_SERVICE and cleaned up $OLD_SERVICE."