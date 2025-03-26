#!/bin/sh
########################################################################################################################
#
# Entrypoint to the application
# Reference:
#   https://docs.oracle.com/en/java/javase/17/docs/specs/man/java.html#java-command-line-argument-files
#   https://docs.spring.io/spring-boot/reference/packaging/container-images/efficient-images.html
#
########################################################################################################################

# Run the Java application with the specified parameters
java @.vmoptions org.springframework.boot.loader.JarLauncher
