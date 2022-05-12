# TaskService


## Start Up
### Utilize Docker Compose to spin up Postgres DB
`docker-compose up`
avaiable on port `5432`
local container db connection located in application.yml

*TODO* create application local profile

### Gradle 
To start api `./gradlew bootRun`

### SQL Export
located in `src/main/resources/data.sql` initial data. Ready to copy into Sql script runner.

### API
Api is available on `localhost:8088`. Can alter port in the `src/main/resources/application.yml` file

### Postman collection
Importing `task_service_postman_collection` contains all requests available Task Service and extra requests displaying 
possible query params that could be utilize.