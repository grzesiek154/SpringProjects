# 1. Spring IoC Container and Beans

## @SpringBootApplication annotation clearly signifies that this is a Spring Boot appli-cation. But there’s more to @SpringBootApplication than meets the eye.@SpringBootApplication  is  a  composite  application  that  combines  three  otherannotations:

- @SpringBootConfiguration—Designates  this  class  as  a  configuration  class.Although  there’s  not  much  configuration  in  the  class  yet,  you  can  add  Java-based Spring Framework configuration to this class if you need to. This annota-tion is, in fact, a specialized form of the @Configuration annotation.
- @EnableAutoConfiguration—Enables  Spring  Boot  automatic  configuration.We’ll talk more about autoconfiguration later. For now, know that this annota-tion tells Spring Boot to automatically configure any components that it thinksyou’ll need.
- @ComponentScan—Enables  component  scanning.  This  lets  you  declare  otherclasses  with  annotations  like  @Component, @Controller, @Service,  and  others,to have Spring automatically discover them and register them as components inthe Spring application context.
- `@EnableWebMvc`: Flags the application as a web application and activates key behaviors, such as setting up a `DispatcherServlet`. Spring Boot adds it automatically when it sees `spring-webmvc` on the classpath.