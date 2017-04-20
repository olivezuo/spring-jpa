# spring-jpa
Spring JPA project that support read/write split.

## JPA Data Source
### AbstractRoutingDataSource
Abstract class AbstractRoutingDataSource give us the capability to manage multiple datasources in the same spring project. It will do the dynamic routing for the application to use different datasource at runtime.

### Read / Write split 
Multiple datasources are actully the idea behind our read/write split implementation. What we actually need to do is to enable the switch of read/write datasource at runtime.

### Connection Pool
We use the [HikariCP](https://github.com/brettwooldridge/HikariCP) connection pool. 

## Annotation implementation using AOP
We use the annotation at the method level to decide the read(slave) or write(master) datasource. We use AOP to enabled he annotation.


## Docker Support
To create the Docker image and container, run the following maven command and docker command:

```
$ mvn clean install docker:removeImage docker:build -Denv=dev -Dmaven.test.skip=true 
$ docker run --name spring-jpa --link mysql-master:mysql-master --link mysql-slave:mysql-slave -p 8080:8080 jin/spring-jpa

```