# ----------< PREPARATION STAGE >------------
FROM eclipse-temurin:17-jdk-jammy AS deps

WORKDIR /build

# Copy the mvnw wrapper with executable permissions.
COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

# Download dependencies as a separate step to take advantage of Docker's caching.
# Leverage a cache mount to /root/.m2 so that subsequent builds don't have to
# re-download packages.
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests

# ----------< BUILD STAGE >------------
FROM deps AS package

WORKDIR /build

COPY src/ src/

# Build the application while skipping tests, and extract the artifact name and version
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    ARTIFACT_ID=$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout) && \
    VERSION=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout) && \
    mv target/${ARTIFACT_ID}-${VERSION}.jar target/app.jar

# ----------< EXTRACT STAGE >------------
# Extract application layers for optimized Spring Boot layering
FROM package AS extract

WORKDIR /build

# Use Spring Boot's layertools to extract dependencies, snapshots, and application layers
RUN java -Djarmode=tools -jar target/app.jar extract --layers --launcher --destination target/extracted

# ----------< FINAL STAGE >------------
FROM eclipse-temurin:17-jre-jammy AS final

LABEL maintainer="Justedlev"
LABEL name="config-center"
LABEL description="Config Center"

ENV SERVER_PORT=5000

# Create a non-privileged user that the app will run under.
# See https://docs.docker.com/go/dockerfile-user-best-practices/
ARG UID=10001
ARG USERNAME=appuser
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/home/${USERNAME}" \
    --shell "/sbin/nologin" \
    --uid "${UID}" \
    ${USERNAME}
USER ${USERNAME}

# Copy the executable from the "package" stage.
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./

COPY /docker/.vmoptions /.vmoptions
COPY --chmod=0755 /docker/entrypoint.sh /entrypoint
COPY --chmod=0755 /docker/healthcheck.sh /healthcheck

EXPOSE ${SERVER_PORT}

# Define the entry point for the Spring Boot application
ENTRYPOINT [ "sh", "/entrypoint" ]

# Define a health check
HEALTHCHECK --interval=30s --timeout=5s --retries=3 --start-period=15s CMD [ "/healthcheck" ]
