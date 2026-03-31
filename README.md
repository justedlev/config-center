<div id="header" align="center">
    <h1>Config Center</h1>
    <h3>🧰 Configuration Server</h3>
</div>

<div id="badges" align="center">

[![language](https://img.shields.io/badge/Java%2021-e6892e.svg?logo=openjdk&logoColor=white)](https://github.com/justedlev/config-center)
[![framework](https://img.shields.io/badge/Spring%20Boot%204-6DB33F.svg?logo=springboot&logoColor=white)](https://docs.spring.io/spring-boot/index.html)
[![Docker Image Version](https://img.shields.io/docker/v/justedlev/config-center?logo=docker&label=config-center)](https://hub.docker.com/repository/docker/justedlev/config-center)
[![license](https://img.shields.io/github/license/justedlev/config-center)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![stars](https://img.shields.io/github/stars/justedlev/config-center)](https://github.com/justedlev/config-center/star)
[![issues](https://img.shields.io/github/issues/justedlev/config-center)](https://github.com/justedlev/config-center/issues)

</div>

## 📋 About

Simple configuration server based on `🍃 Spring Boot 4` and `🐰 RabbitMQ`

## 🧾 References

- [Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.typesafe-configuration-properties.relaxed-binding.environment-variables)
- [Docker CLI](https://docs.docker.com/reference/cli/docker/compose/)

## ▶️ Run

### 🐳 Docker

First build your amazing 😁 `docker compose` file and then run using the below cmd

```shell
docker compose build -d
```

> [!TIP]
> I already prepared some image on [🐳 docker hub](https://hub.docker.com/repository/docker/justedlev/config-center)

### 🗂️ Docker compose

An example of the `docker compose` file based on the same [.env](/.env)

```yaml
name: justedlev-msrv
services:
  config.justedlev.com:
    tty: true
    environment:
      - SERVER_PORT=8888
      - SPRING_APPLICATION_NAME=config-center
      - SPRING_SECURITY_USER_NAME={example}
      - SPRING_SECURITY_USER_PASSWORD={example}
      - SPRING_SECURITY_USER_ROLES=system
      - SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATION=/config.d
      - LOGGING_FILE_PATH=/var/log/$${SPRING_APPLICATION_NAME}
      - SPRING_RABBITMQ_HOST=rabbitmq
      - SPRING_RABBITMQ_PORT=5672
      - SPRING_RABBITMQ_USERNAME={example}
      - SPRING_RABBITMQ_PASSWORD={example}
    container_name: config-center
    image: justedlev/config-center:latest
    ports:
      - '8888:8888'
    healthcheck:
      test: [ "CMD", "curl", "-k", "-f", "http://localhost:$${SERVER_PORT}/actuator/health" ]
      interval: 30s
      timeout: 10s
      retries: 5
      start_period: 30s
    depends_on:
      rabbitmq:
        condition: service_healthy
    volumes:
      - ./config.d:$${SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATION}
      - ./logs/$${SPRING_APPLICATION_NAME}:$${LOGGING_FILE_PATH}
    deploy:
      resources:
        limits:
          cpus: "0.5"
          memory: 250MB
          
  #🐰 RabbitMQ
  rabbitmq:
    tty: true
    environment:
      - RABBITMQ_DEFAULT_USER=rabbit
      - RABBITMQ_DEFAULT_PASS=rabbit
      - RABBITMQ_SERVER_ADDITIONAL_ERL_ARGS=-rabbit disk_free_limit 2147483648
    container_name: rabbitmq
    image: rabbitmq:management
    hostname: rabbitmq
    restart: unless-stopped
    volumes:
      - ./data/rabbitmq:/var/lib/rabbitmq
    ports:
      - "5672:5672"   # AMQP
      - "15672:15672" # Management UI
    healthcheck:
      test: [ "CMD", "rabbitmq-diagnostics", "-q", "ping" ]
      interval: 30s
      timeout: 10s
      retries: 5
      start_period: 30s
    deploy:
      resources:
        limits:
          cpus: "0.5"
          memory: 500MB
```
