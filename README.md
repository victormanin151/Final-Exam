# Football Matches API

A **Spring Boot REST API** that provides information about football **teams, players, matches, and player pair statistics**.

The application loads match data from **CSV files**, processes it using **DTOs and services**, and stores it in a **PostgreSQL database**.

The API allows users to:

* retrieve match information
* search for players
* filter teams
* analyze how long two players have played together

---

# Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* REST API
* DTO Pattern
* CommandLineRunner
* CSV parsing

---

# Architecture

The project follows a **layered architecture**.

```
Controller → Service → Repository → Database
```

Data loading flow:

```
CSV files → DTOs → Services → PostgreSQL
```

---

# Project Structure

```
src/main/java/com/myown/Final/Exam

controller
    ApiController
    MatchController
    PlayerController
    PlayerPairController
    TeamController

service
    MatchService
    PlayerService
    PlayerPairService
    PlayerRecordService
    TeamService

dto
    ApiInfoDto
    MatchInputDto
    MatchOutputDto
    PlayerInputDto
    PlayerOutputDto
    PlayerRecordInputDto
    PlayerRecordOutputDto
    TeamDto
    PairMinutesDto

model
    Player
    Team
    Match
    PlayerRecord
    PlayerPair

loader
    TeamCsvLoader
    PlayerCsvLoader
    MatchCsvLoader
    PlayerRecordCsvLoader
    
repository
    MatchRepository
    PLayerRecordRepository
    PlayerRepository
    TeamRepository
```

---

# Data Source

Initial data is loaded from **CSV files** located in:

```
src/main/resources/files
```

Files used:

```
teams.csv
players.csv
matches.csv
records.csv
```

The CSV files are parsed when the application starts using **CommandLineRunner loaders**.

---

# CSV Loaders

The loaders run automatically when the application starts.

Execution order:

| Order | Loader                |
| ----- | --------------------- |
| 1     | TeamCsvLoader         |
| 2     | PlayerCsvLoader       |
| 3     | MatchCsvLoader        |
| 4     | PlayerRecordCsvLoader |

Example loader flow:

```
teams.csv → TeamDto → TeamService → PostgreSQL
players.csv → PlayerInputDto → PlayerService → PostgreSQL
matches.csv → MatchInputDto → MatchService → PostgreSQL
records.csv → PlayerRecordInputDto → PlayerRecordService → PostgreSQL
```

---

# Date Parsing

Match dates from the CSV file support multiple formats:

```
M/d/yyyy
MM/dd/yyyy
yyyy-MM-dd
d-M-yyyy
```

Dates are parsed into:

```
LocalDate
```

before being stored in PostgreSQL.

---

# Player Records Logic

Player records describe **when a player was on the field during a match**.

If the CSV file contains `null` for the ending minute:

* **90 minutes** are assumed for normal matches
* **120 minutes** are assumed for matches that went to penalties

Penalty matches are detected while parsing match scores.

Example score formats:

```
2-1
1(4)-1(3)
```

---

# API Endpoints

Base URL

```
http://localhost:8080
```

---

# API Info

Returns general API information.

### GET `/api`

Example response

```
{
  "name": "Final Exam API",
  "version": "1.0",
  "time": "2026-03-09T14:22:00",
  "endpoints": [
    "players",
    "matches",
    "teams"
  ]
}
```

---

# Players

### Get all players

```
GET /players
```

---

### Get player by ID

```
GET /players/{id}
```

---

### Search players by name

```
GET /players/search?name=ronaldo
```

---

# Matches

### Get all matches

```
GET /matches
```

---

### Get match by ID

```
GET /matches/{id}
```

---

### Get matches that went to penalties

```
GET /matches/penalties
```

---

# Teams

### Get all teams

```
GET /teams
```

---

### Get team by ID

```
GET /teams/{id}
```

---

### Get teams by country

```
GET /teams/country?name=Spain
```

---

### Get teams by group

```
GET /teams/group?letter=A
```

---

# Player Pair Statistics

Calculates how many **minutes two players have played together**.

### Endpoint

```
GET /pairs
```

Query parameters:

| Parameter | Description        | Default  |
| --------- | ------------------ | -------- |
| teamType  | same / different   | same     |
| sort      | longest / shortest | longest  |
| limit     | number of results  | optional |

---

### Examples

Longest playing pairs on the same team

```
/pairs?teamType=same&sort=longest
```

Top 5 longest playing pairs

```
/pairs?teamType=same&sort=longest&limit=5
```

Shortest playing pairs from different teams

```
/pairs?teamType=different&sort=shortest
```

---

# Database

The application stores data in **PostgreSQL**.

Tables include:

```
teams
players
matches
player_records
```

---

# Running the Project

### 1 Clone the repository

```
git clone https://github.com/victormanin151/Final-Exam.git
```

---

### 2 Navigate to the project

```
cd final-exam
```

---

### 3 Configure PostgreSQL

Update your `application.properties` with your database credentials.

Example:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/footballdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
spring.jpa.show-sql=true
```

---

### 4 Run the application

```
mvn spring-boot:run
```

or run the **Spring Boot main class**.

---

# Server

The API will start on:

```
http://localhost:8080
```

---

# Future Improvements

* Pagination
* Global exception handling
* Swagger / OpenAPI documentation
* Unit tests
* Docker support
* API authentication

---

# Author

Victor Manin
