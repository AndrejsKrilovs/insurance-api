# Getting Started
**Things you may use to achieve the needed result:**

* Java 8+
* jUnit 4+
* Any source on the internet that may help you

**Expected result:**
* Java 8+ project
* Described functionality implementation
* Unit tests for the implementation

#Task description:
Insurance company wants to start issuing private property policies to their customers.

System analysts found out that there will be a policy which will have objects (e.g. a flat) and that objects will have sub-
objects (e.g. eletronic devices such as TV).

One policy can contain multiple objects. 

One object can contain multiple sub-objects.

In this iteration, customer needs a functionality that calculates premium (a price that will be paid by each client that will
buy this insurance) for the policy.

Premium is calculated by a formula defined in "Needed functionality" section.

In short - formula groups all sub-objects by their type, sums their sum-insured and applies coefficient to the sum. Then all
group sums are summed up which gets us a premium that must be paid by the client.

No GUI is needed, policy data will be sent through the API directly to the methods that will be created.
No database is needed, functionality should not store any data. It should receive policy object, calculate premium and
return result.

### Premium calculation formula:
* **PREMIUM** = **PREMIUM_FIRE** + **PREMIUM_WATER**
* **PREMIUM_FIRE** = **SUM_INSURED_FIRE** * **COEFFICIENT_FIRE**
**SUM_INSURED_FIRE** - total sum insured of all policy's sub-objects with type "Fire"<br>
**COEFFICIENT_FIRE** - by default **0.013** but if SUM_INSURED_FIRE is over **100** then **0.023**

* **PREMIUM_WATER** = **SUM_INSURED_WATER** * **COEFFICIENT_WATER**
**SUM_INSURED_WATER** - total sum insured of all policy's sub-objects with type "Water"<br>
**COEFFICIENT_WATER** - by default 0.1 but if equal or greater than 10 then 0.05
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Jersey](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jersey)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

