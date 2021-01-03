# 1. Spring IoC Container and Beans

## @SpringBootApplication annotation clearly signifies that this is a Spring Boot appli-cation. But there’s more to @SpringBootApplication than meets the eye.@SpringBootApplication  is  a  composite  application  that  combines  three  otherannotations:

- @SpringBootConfiguration—Designates  this  class  as  a  configuration  class. Although  there’s  not  much  configuration  in  the  class  yet,  you  can  add  Java-based Spring Framework configuration to this class if you need to. This annota-tion is, in fact, a specialized form of the @Configuration annotation.
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

# 3. @Configuration annotation

Spring @Configuration annotation allows us to use annotations for [dependency injection](https://www.journaldev.com/2410/spring-dependency-injection). Let’s understand how to create Spring Configuration classes.

  

Let’s create a simple java bean class.

```java
package com.journaldev.spring;

public class MyBean {

	public MyBean() {
		System.out.println("MyBean instance created");
	}
	
}
```

Before we use any of the Spring framework classes, we will have to add it’s dependencies to the maven project.

```java
<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>5.0.6.RELEASE</version>
</dependency>
```

Now let’s create the Spring Configuration class.

```java
package com.journaldev.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyBean myBean() {
		return new MyBean();
	}
	
}
```

Let’s write a simple class and configure our simple Spring configuration class.

et’s write a simple class and configure our simple Spring configuration class.

```
package com.journaldev.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MySpringApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MyConfiguration.class);
		ctx.refresh();

		// MyBean mb1 = ctx.getBean(MyBean.class);

		// MyBean mb2 = ctx.getBean(MyBean.class);

		ctx.close();
	}

}
```

If you run above application, it will produce output like this:

```
May 23, 2018 12:34:54 PM org.springframework.context.support.AbstractApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@ff5b51f: startup date [Wed May 23 12:34:54 IST 2018]; root of context hierarchy
MyBean instance created
May 23, 2018 12:34:54 PM org.springframework.context.support.AbstractApplicationContext doClose
INFO: Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@ff5b51f: startup date [Wed May 23 12:34:54 IST 2018]; root of context hierarchy
```

Notice that Spring loads beans into it’s context before  we have even requested it. This is to make sure all the beans are  properly configured and application fail-fast if something goes wrong.

Also `ctx.refresh()` must be called, otherwise we will get following error when we will try to get any bean from the context.

```
Exception in thread "main" java.lang.IllegalStateException: org.springframework.context.annotation.AnnotationConfigApplicationContext@f0f2775 has not been refreshed yet
	at org.springframework.context.support.AbstractApplicationContext.assertBeanFactoryActive(AbstractApplicationContext.java:1076)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1106)
	at com.journaldev.spring.MySpringApp.main(MySpringApp.java:11)
```

If you uncomment the statements where I am getting  MyBean instances, you will notice that it’s not calling the constructor  of MyBean. It’s because the default scope of [spring beans](https://www.journaldev.com/2461/spring-ioc-bean-example-tutorial) is Singleton. We can change it using `@Scope` annotation.

## What if we remove @Configuration annotation?

What will happen if we remove the @Configuration annotation from  MyConfiguration class. You will notice that it still works as expected  and spring beans are registered and retrieved as singleton classes. But  in this case, if we make a call to `myBean()` method then it  will be a plain java method call and we will get a new instance of  MyBean and it won’t remain singleton. To prove this point, let’s define  another bean that will be using MyBean instance.

```java
package com.journaldev.spring;

public class MyBeanConsumer {

	public MyBeanConsumer(MyBean myBean) {
		System.out.println("MyBeanConsumer created");
		System.out.println("myBean hashcode = "+myBean.hashCode());
	}

}
```

Our updated Spring Configuration class is:

```java
package com.journaldev.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyConfiguration {

	@Bean
    public MyBean myBean() {
		return new MyBean();
	}
	
	@Bean
    public MyBeanConsumer myBeanConsumer() {
		return new MyBeanConsumer(myBean());
	}
}
```

Now when we run the `MySpringApp` class, it generates following output.

```java
MyBean instance created
MyBean instance created
MyBeanConsumer created
myBean hashcode = 1647766367
```

So MyBean is not singleton anymore, now let’s annotate `MyConfiguration` with `@Configuration` annotation again and run the `MySpringApp` class. This time output will be like below.

```java
MyBean instance created
MyBeanConsumer created
myBean hashcode = 1095088856
```

So it’s better to use `@Configuration` annotation with configuration classes to make sure our spring container is behaving like the way we want it to.

If you don’t want to use @Configuration annotation for some weird reasons, we can still create our configuration class by not calling the `myBean()` method and rather using an instance variable of MyBean configured  through @Autowired annotation. Something like below code will work as  well.

```java
package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyConfiguration {

	@Autowired
	MyBean myBean;
	
	@Bean
    public MyBean myBean() {
		return new MyBean();
	}
	
	@Bean
    public MyBeanConsumer myBeanConsumer() {
		return new MyBeanConsumer(myBean);
	}
}
```