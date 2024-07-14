# Food Ordering System

Thia project demonstrates the implementation of a food ordering system using microservuces with 
Clean and Hexagonal architectures, DDD, SAGA, Outbox, CQRS, Kafka, Kubernetes & Google Kubernetes
Engine (GKE).

The system is composed of the following microservices:

- Order Service
- Customer Service Payment Service
- Payment Service
- Restaurant Service

## Setting up the environment

- Java SDK 21
- Apache Maven 3.9.0
- JetBrains IntelliJ
- Docker Desktop
- Postman
- kcat (formerly known as kafkacat)
- PostgreSQL

## Module dependencies of the Order Service

Image below shows the module dependencies of the Order Service.

![Module depencies.](./images/dependency-graph.png)

### Visualizing module dependencies with Graphviz

```
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=space.springbok.ordering.system*:*"
```
