```java
@Autowired
public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
}
```

The @autowired method you have provided does not need to be invoked from within your own code. It will be invoked by the Spring framework.  Imagine a setter method in a class that is declared as @autowired. The  setters parameter is provided by Spring. So before using the class  object instance you do not have to write code to call the setter method, just like you don't need to provide the parameters of an @autowired  constructor. There are lots of things you can do with autowired.

One of the advantages of @autowired is that you do not have to  instantiate the object, as Spring Framework will do that for you. But  not only that, an @autowired object is managed by the Spring Framework,  so you don't have to worry about handling of object instances, cause  Spring does it for you.

