

## Clone project

```bash
git clone https://github.com/tsadimasteaching/ds-lab-2024.git
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

# Metrics

* add 
```xml
<dependency>
    <groupId>io.micrometer</groupId>
		<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>
```

metrics are available in http://localhost:8080/actuator/metrics

* kubernetes

add these annotations to deployment
```yaml
  annotations:
    prometheus.io/path: /actuator/prometheus
    prometheus.io/port: "8080"
    prometheus.io/scrape: "true"
```

if required, add this servicemonitor

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: spring-boot-monitor
  labels:
    release: kube-prom-stack
spec:
  selector:
    matchLabels:
      app: spring
  endpoints:
  - port: spring
    path: /actuator/prometheus
    interval: 15s
```

add custom dashboard

https://grafana.com/grafana/dashboards/19004-spring-boot-statistics/
