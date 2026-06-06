# Engineering Playground

A multi-module Gradle project for experimenting with Java, Spring Boot, Kafka, and MongoDB.

## Tech Stack

- Java 21
- Spring Boot 3.3
- Apache Kafka (KRaft)
- MongoDB 7
- Gradle 8.14

## Modules

| Module | Description |
|--------|-------------|
| `java-core/collections-demo` | List, Set, Map implementations & performance |
| `java-core/concurrency-demo` | Virtual threads, structured concurrency |
| `kafka/producer-consumer-demo` | Kafka producer & consumer with Spring |
| `spring-boot/rest-api-demo` | REST API basics |
| `spring-boot/mongodb-demo` | MongoDB with Spring Data |
| `spring-boot/security-demo` | Spring Security basics |
| `mongodb/aggregation-examples` | MongoDB aggregation pipeline scripts |

## Getting Started

```bash
# Start infrastructure
docker compose -f docker/docker-compose.yml up -d

# Build all modules
./gradlew build
```

## Requirements

- Java 21+
- Docker & Docker Compose
