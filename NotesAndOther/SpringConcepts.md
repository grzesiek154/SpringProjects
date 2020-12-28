# 1. Spring IoC Container and Beans

## @SpringBootApplication annotation clearly signifies that this is a Spring Boot appli-cation. But there’s more to @SpringBootApplication than meets the eye.@SpringBootApplication  is  a  composite  application  that  combines  three  otherannotations:

- @SpringBootConfiguration—Designates  this  class  as  a  configuration  class.Although  there’s  not  much  configuration  in  the  class  yet,  you  can  add  Java-based Spring Framework configuration to this class if you need to. This annota-tion is, in fact, a specialized form of the @Configuration annotation.
- @EnableAutoConfiguration—Enables  Spring  Boot  automatic  configuration.We’ll talk more about autoconfiguration later. For now, know that this annota-tion tells Spring Boot to automatically configure any components that it thinksyou’ll need.
- @ComponentScan—Enables  component  scanning.  This  lets  you  declare  otherclasses  with  annotations  like  @Component, @Controller, @Service,  and  others,to have Spring automatically discover them and register them as components inthe Spring application context.
- `@EnableWebMvc`: Flags the application as a web application and activates key behaviors, such as setting up a `DispatcherServlet`. Spring Boot adds it automatically when it sees `spring-webmvc` on the classpath.

# 2. Spring Container and Beans

At its core, Spring offers a container, often referred to as the Spring application con-
text, that creates and manages application components. These components, or beans,
are wired together inside the Spring application context to make a complete applica-
tion, much like bricks, mortar, timber, nails, plumbing, and wiring are bound together
to make a house.
The act of wiring beans together is based on a pattern known as dependency injection
(DI). Rather than have components create and maintain the lifecycle of other beans
that they depend on, a dependency-injected application relies on a separate entity
(the container) to create and maintain all components and inject those into the beans
that need them. This is done typically through constructor arguments or property
accessor methods.

```java
@Configuration
public class ServiceConfiguration {
@Bean
public InventoryService inventoryService() {
return new InventoryService();
}
@Bean
public ProductService productService() {
return new ProductService(inventoryService());
}
}
```

The @Configuration annotation indicates to Spring that this is a configuration classthat will provide beans to the Spring application context. The configuration’s class meth-ods are annotated with @Bean, indicating that the objects they return should be addedas beans in the application context (where, by default, their respective bean IDs willbe the same as the names of the methods that define them).

## Automatic configuration

Automatic configuration has its roots in the Spring techniques known as autowiring and component scanning. With component scanning, Spring can automatically discover components from an application’s classpath and create them as beans in the Spring application context. With autowiring, Spring automatically injects the components with the other beans that they depend on.