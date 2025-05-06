

## Clone project

```bash
git clone https://github.com/tsadimasteaching/ds-lab-2023.git
```
## Database
* you can create a free postgres database on [https://render.com/](https://render.com/)
* you can run a postgres with docker
  ```bash
  docker run --name ds-lab-pg --rm \
    -e POSTGRES_PASSWORD=pass123 \
    -e POSTGRES_USER=dbuser \
    -e POSTGRES_DB=students \
    -d --net=host \
    -v pgdata:/var/lib/postgresql/data \
    postgres:16
  ```
  
## Fix database connection in application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/students
spring.datasource.username=dbuser
spring.datasource.password=pass123
```
## Run the project
make sure you have set correctly ``JAVA_HOME`` and ``M2_HOME`` enviromental variables
```bash
mvn spring-boot:run
```

## Swagger
* visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to see the api documentation
## FrontEnd
This project can use repository [https://github.com/gkoulis/ds-lab-2023-frontend](https://github.com/gkoulis/ds-lab-2023-frontend) as frontend

# test poll
