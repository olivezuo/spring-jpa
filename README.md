# spring-jpa
Spring JPA project that support read/write split.

## JPA Data Source
### AbstractRoutingDataSource
Abstract class AbstractRoutingDataSource give us the capability to manage multiple datasources in the same spring project. It will do the dynamic routing for the application to use different datasource at runtime.

### Read / Write split 
Multiple datasources are actully the idea behind our read/write split implementation. What we actually need to do is to enable the switch of read/write datasource at runtime.

## Annotation implementation using AOP
We use the annotation at the method level to decide the read(slave) or write(master) datasource. We use AOP to enabled he annotation.