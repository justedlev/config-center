<div id="header" align="center">
    <h1>Kloudy</h1>
    <h3>🗃️ File storage</h3>
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

## ⚠️ Requirements

## ▶️ Run

### 🛠️ Intellij

### 🚢 Docker

### 🗂️ Docker compose

```yaml
name: justedlev-msrv
services:
  server:
    container_name: config-center
    image: justedlev/config-center:latest
    build:
      context: .
    env_file:
      - .env
    ports:
      - '8976:${SERVER_PORT}'
```
