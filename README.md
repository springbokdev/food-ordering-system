# food-ordering-system
Food Ordering System project

## Setting up the environment

- Java SDK 21
- Apache Maven 3.9.0
- JetBrains IntelliJ
- Docker Desktop
- Postman
- kcat (formerly known as kafkacat)
- PostgreSQL
- 

## Visualizing module dependencies with Graphviz

```
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=space.springbok.ordering.system*:*"
```
