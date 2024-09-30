<div id="header" align="center">
    <h1>Config Center</h1>
    <h3>🧰 Configuration Server</h3>
</div>

<div id="badges" align="center">

[![language](https://img.shields.io/badge/Java%2017-e6892e.svg?logo=openjdk&logoColor=white)](https://github.com/justedlev/config-center)
[![framework](https://img.shields.io/badge/Spring%20Boot%203-6DB33F.svg?logo=springboot&logoColor=white)](https://docs.spring.io/spring-boot/index.html)
[![Docker Image Version](https://img.shields.io/docker/v/justedlev/config-center?logo=docker&label=config-center)](https://hub.docker.com/repository/docker/justedlev/config-center)
[![license](https://img.shields.io/github/license/justedlev/config-center)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![stars](https://img.shields.io/github/stars/justedlev/config-center)](https://github.com/justedlev/config-center/star)
[![issues](https://img.shields.io/github/issues/justedlev/config-center)](https://github.com/justedlev/config-center/issues)

</div>

## 📋 About

Simple configuration server based on `Spring Boot 3`

## 🧾 References

- [Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.typesafe-configuration-properties.relaxed-binding.environment-variables)
- [Docker CLI](https://docs.docker.com/reference/cli/docker/compose/)

## ▶️ Run

### <a href="#"><img src="https://github.com/JetBrains/logos/raw/refs/heads/master/web/intellij-idea/intellij-idea.svg" width="16"/></a> Intellij

You can use the simple [run configuration](/.run/Default.run.xml), that based on [.env](/.env)
and [jvm options](/.vmoptions) to run the app locally

### 🐳 Docker

First build your amazing 😁 `docker compose` file and then run using the below cmd

```shell
docker compose build -d
```

> [!TIP]
> I already prepared some image on [🐳 docker hub](https://hub.docker.com/repository/docker/justedlev/config-center)

### 🗂️ Docker compose

An example of the `docker compose` file based on the same [.env](/.env) as in [Run](#-run)

```yaml
name: justedlev-msrv
services:
  configuration-server:
    container_name: ${SPRING_APPLICATION_NAME}
    image: justedlev/config-center:latest
    build:
      context: .
    env_file:
      - .env
    ports:
      - '8976:${SERVER_PORT}'
    volumes:
      - ./config.d:${SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATION}
      - ./logs:/var/log/${SPRING_APPLICATION_NAME}
```
