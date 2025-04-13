.PHONY: build run test docker up lint format

build:
	mvnw clean install

run:
	mvnw spring-boot:run

test:
	mvnw test

docker:
	docker build -t justedlev/app:latest -f docker/Dockerfile .

up:
	docker compose -f docker/compose up -d

lint:
	mvnw checkstyle:check

format:
	mvnw spotless:apply

clean:
	mvnw clean

help:
	@echo "Available commands:"
	@echo "  build     - Build the project"
	@echo "  run       - Run Spring Boot"
	@echo "  test      - Run tests"
	@echo "  docker    - Build Docker image"
	@echo "  up        - Run docker-compose"
	@echo "  lint      - Run code style checks"
	@echo "  format    - Auto-format code"
	@echo "  clean     - Run clean"
