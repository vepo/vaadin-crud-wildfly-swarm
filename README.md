# Vaadin CRUD With Wildfly Swarm

## Frameworks

1. Wildfly Swarm
2. Vaadin 8
3. JNoSQL

## Prerequisite

### Database

Use a mongo instance:

```
docker run -d --name mongodb-instance -p 27017:27017 mongo
```

# Run

To run:

```
mvn clean wildfly-swarm:run
```
