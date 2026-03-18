# Quarkus + Jimmer Example

A modern REST API example built with [Quarkus](https://quarkus.io/), [Jimmer ORM](https://babyfish-ct.github.io/jimmer-doc/), and [quarkus-jimmer-extension](https://github.com/flynndi/quarkus-jimmer-extension) in Kotlin.

## Tech Stack

- **Quarkus 3.32.3** with virtual threads
- **Jimmer ORM 0.9.120** with KSP code generation
- **Kotlin 2.3.20**
- **PostgreSQL** with Liquibase migrations
- **GraalVM native image** support
- **Testcontainers** for integration tests

## Project Structure

```
quarkus-jimmer-example-model/       # Entities, DTOs, repositories
  src/main/kotlin/.../entity/       # Jimmer @Entity interfaces
  src/main/kotlin/.../repository/   # KRepository interfaces
  src/main/dto/                     # Jimmer DTO definitions (.dto files)

quarkus-jimmer-example-service/     # REST API, services, config
  src/main/kotlin/.../resource/     # JAX-RS resources
  src/main/kotlin/.../service/      # Service layer with transactions
  src/main/resources/               # Application config, Liquibase changelogs
  src/test/                         # Integration tests with Testcontainers
```

## Running

### Prerequisites

- Java 25+
- Docker (for PostgreSQL)

### Start PostgreSQL

```bash
docker run -d --name postgres -p 5432:5432 \
  -e POSTGRES_DB=quarkus_jimmer \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  postgres:16.2
```

### Run in dev mode

```bash
./gradlew quarkusDev
```

### Run tests

```bash
./gradlew test
```

### Build native image

```bash
docker build -f Dockerfile.native -t quarkus-jimmer-example:native .
```

## API Endpoints

| Method | Path           | Description         |
|--------|----------------|---------------------|
| GET    | /hello         | Health check        |
| GET    | /authors       | List all authors    |
| GET    | /authors/{id}  | Get author by ID    |
| GET    | /books         | List all books      |
| GET    | /books/{id}    | Get book by ID      |
| POST   | /books         | Create a book       |
| PUT    | /books/{id}    | Update a book       |
| DELETE | /books/{id}    | Delete a book       |

## License

[MIT](LICENSE)
